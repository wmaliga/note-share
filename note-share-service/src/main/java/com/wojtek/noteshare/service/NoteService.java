package com.wojtek.noteshare.service;

import com.wojtek.noteshare.repository.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAllNotes();

    Note findNoteById(long id);
}
