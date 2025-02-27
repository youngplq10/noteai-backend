package dev.starzynski.noteAI_backend.Controller;

import dev.starzynski.noteAI_backend.Model.Tag;
import dev.starzynski.noteAI_backend.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/auth/tag")
    public ResponseEntity<Tag> createTag(@RequestPart String username, @RequestPart String name) {
        return new ResponseEntity<Tag> (tagService.createTag(username, name), HttpStatus.CREATED);
    }

    @GetMapping("/auth/tags/{username}")
    public ResponseEntity<List<Tag>> getAllTagsByUsername(@PathVariable String username) {
        return new ResponseEntity<List<Tag>> (tagService.getAllTagsByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/auth/tag/{username}/{name}")
    public ResponseEntity<Boolean> removeTagByName(@PathVariable String username, @PathVariable String name) {
        return new ResponseEntity<Boolean> (tagService.removeTagByName(username, name), HttpStatus.OK);
    }

    @GetMapping("/auth/tag/{name}/{username}")
    public ResponseEntity<Tag> getTagByName(@PathVariable String name, @PathVariable String username) {
        return new ResponseEntity<Tag> (tagService.getTagByName(name, username), HttpStatus.OK);
    }
}
