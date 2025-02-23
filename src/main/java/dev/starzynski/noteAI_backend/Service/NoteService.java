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

    public Note createNote(String content, String username, List<String> tagNames) {
        try {
            // Fetch user
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create a new note
            Note note = new Note();
            note.setContent(content);
            note.setUser(user);

            // Fetch and update tags
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

            // Set tags in note
            note.setTags(tagList);

            // Save note first
            noteRepository.insert(note);

            // Save updated tags
            tagRepository.saveAll(tagList);

            // Update user and save
            user.getNotes().add(note);
            userRepository.save(user);

            return note;

        } catch (Exception e) {
            throw new RuntimeException("Error creating note", e);
        }
    }
}
