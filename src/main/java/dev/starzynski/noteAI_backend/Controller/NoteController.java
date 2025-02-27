package dev.starzynski.noteAI_backend.Controller;

import dev.starzynski.noteAI_backend.Model.Note;
import dev.starzynski.noteAI_backend.Model.NoteRequestDTO;
import dev.starzynski.noteAI_backend.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/auth/note")
    public ResponseEntity<String> createNote(@RequestBody NoteRequestDTO requestDTO) {
        return new ResponseEntity<String> (noteService.createNote(requestDTO.getContent(), requestDTO.getUsername(), requestDTO.getTags()),HttpStatus.CREATED);
    }

    @GetMapping("/auth/note/{link}")
    public ResponseEntity<Note> getNoteByLink(@PathVariable String link) {
        return new ResponseEntity<Note> (noteService.getNoteByLink(link), HttpStatus.OK);
    }

    @PostMapping("/auth/note/summary")
    public ResponseEntity<Note> setSummary(@RequestPart String summary, @RequestPart String link) {
        return new ResponseEntity<Note> (noteService.setSummary(summary, link), HttpStatus.OK);
    }

    @PostMapping("/auth/note/{link}")
    public ResponseEntity<String> copyNoteByCode(@PathVariable String link, @RequestPart String username) {
        return new ResponseEntity<String> (noteService.copyNoteByCode(link, username), HttpStatus.OK);
    }

    @DeleteMapping("/auth/note/{link}")
    public ResponseEntity<Boolean> deleteNote(@PathVariable String link) {
        return new ResponseEntity<Boolean> (noteService.deleteNote(link), HttpStatus.OK);
    }
}
