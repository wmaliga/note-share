package com.wojtek.noteshare.service.impl;

import com.wojtek.noteshare.exception.NoteNotFoundException;
import com.wojtek.noteshare.repository.NoteRepository;
import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    private final NoteRepository noteRepository;

    @Override
    public List<Note> findAllNotes() {
        return this.noteRepository.findAll();
    }

    @Override
    public Note findNoteById(long id) {
        return this.noteRepository.findById(id)
                .orElseThrow(NoteNotFoundException::new);
    }
}
