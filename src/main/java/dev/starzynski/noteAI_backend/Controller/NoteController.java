package dev.starzynski.noteAI_backend.Controller;

import dev.starzynski.noteAI_backend.Model.Note;
import dev.starzynski.noteAI_backend.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/auth/note")
    public ResponseEntity<Note> createNote(@RequestBody NoteRequestDTO requestDTO) {
        return new ResponseEntity<Note> (noteService.createNote(requestDTO.getContent(), requestDTO.getUsername(), requestDTO.getTags()),HttpStatus.CREATED);
    }
}
