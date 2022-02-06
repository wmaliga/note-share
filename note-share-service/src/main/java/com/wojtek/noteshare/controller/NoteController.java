package com.wojtek.noteshare.controller;

import com.wojtek.noteshare.controller.to.NoteTo;
import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.service.NoteService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @Autowired
    public NoteController(NoteService noteService, Mapper mapper) {
        this.noteService = noteService;
        this.mapper = mapper;
    }

    private final NoteService noteService;

    private final Mapper mapper;

    @GetMapping("")
    public List<NoteTo> findAllNotes() {
        return this.noteService.findAllNotes().stream()
                .map(note -> this.mapper.map(note, NoteTo.class))
                .collect(toList());
    }

    @GetMapping("/{id}")
    public NoteTo findNoteById(@PathVariable Long id) {
        Note note = this.noteService.findNoteById(id);
        return this.mapper.map(note, NoteTo.class);
    }
}
