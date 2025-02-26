package dev.starzynski.noteAI_backend.Service;

import dev.starzynski.noteAI_backend.Model.Note;
import dev.starzynski.noteAI_backend.Model.Tag;
import dev.starzynski.noteAI_backend.Model.User;
import dev.starzynski.noteAI_backend.Repository.NoteRepository;
import dev.starzynski.noteAI_backend.Repository.TagRepository;
import dev.starzynski.noteAI_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    public String createNote(String content, String username, List<String> tagNames) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Note note = new Note();
            note.setContent(content);
            note.setUser(user);

            List<Tag> tagList = tagNames.stream()
                    .map(tagName -> {
                        Tag tag = tagRepository.findByNameAndUser(tagName, user);
                        if (tag == null) {
                            tag = new Tag();
                            tag.setName(tagName);
                            tag.setUser(user);
                        }
                        tag.getNotes().add(note);
                        return tag;
                    })
                    .collect(Collectors.toList());

            note.setTags(tagList);

            noteRepository.insert(note);

            tagRepository.saveAll(tagList);

            user.getNotes().add(note);
            userRepository.save(user);

            return note.getLink();

        } catch (Exception e) {
            throw new RuntimeException("Error creating note", e);
        }
    }

    public Note getNoteByLink(String link) {
        return noteRepository.findByLink(link);
    }

    public Note setSummary(String summary, String link) {
        try {
            System.out.println(link);
            Note note = noteRepository.findByLink(link);

            note.setSummary(summary);

            noteRepository.save(note);

            return note;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String copyNoteByCode(String link, String username) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow();

            Note note = noteRepository.findByLink(link);

            Note newNote = new Note();
            newNote.setUser(user);
            newNote.setContent(note.getContent());

            if (note.getSummary() != null) {
                newNote.setSummary(note.getSummary());
            }

            noteRepository.insert(newNote);

            return newNote.getLink();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
