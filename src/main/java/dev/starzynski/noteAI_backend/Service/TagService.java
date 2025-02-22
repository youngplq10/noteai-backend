package dev.starzynski.noteAI_backend.Service;

import dev.starzynski.noteAI_backend.Model.Tag;
import dev.starzynski.noteAI_backend.Model.User;
import dev.starzynski.noteAI_backend.Repository.TagRepository;
import dev.starzynski.noteAI_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Tag> getAllTagsByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow();

            return tagRepository.findAllByUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Tag createTag(String username, String name) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow();

            Tag tag = new Tag();
            tag.setUser(user);
            tag.setName(name);

            tagRepository.insert(tag);

            return tag;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean removeTagByName(String username, String name) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow();

            Tag tag = tagRepository.findByNameAndUser(name, user);

            tagRepository.delete(tag);

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
