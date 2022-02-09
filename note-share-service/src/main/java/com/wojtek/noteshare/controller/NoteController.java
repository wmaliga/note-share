package com.wojtek.noteshare.controller;

import com.wojtek.noteshare.controller.to.NoteShareTo;
import com.wojtek.noteshare.controller.to.NoteTo;
import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;
import com.wojtek.noteshare.service.NoteService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/notes")
@CrossOrigin
public class NoteController {

    @Autowired
    public NoteController(NoteService noteService, Mapper mapper) {
        this.noteService = noteService;
        this.mapper = mapper;
    }

    private final NoteService noteService;

    private final Mapper mapper;

    @GetMapping("")
    public List<NoteTo> findPublicNotes() {
        return this.noteService.findPublicNotes().stream()
                .map(note -> this.mapper.map(note, NoteTo.class))
                .collect(toList());
    }

    @GetMapping("/{id}/type")
    public NoteType getNoteType(@PathVariable Long id) {
        return this.noteService.getNoteType(id);
    }

    @GetMapping("/{id}")
    public NoteTo getNote(@PathVariable Long id,
                          @RequestHeader(name = "Authorization", required = false) String password) {
        Note note = this.noteService.getNote(id, password);
        return this.mapper.map(note, NoteTo.class);
    }

    @PostMapping("")
    public ResponseEntity<Void> saveNote(@Valid @RequestBody NoteShareTo noteShare) {
        Note note = this.mapper.map(noteShare, Note.class);
        this.noteService.saveNote(note);

        return ResponseEntity.created(URI.create(note.getId().toString())).build();
    }
}
