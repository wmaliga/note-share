package com.wojtek.noteshare.service;

import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;

import java.util.List;

public interface NoteService {

    List<Note> findPublicNotes();

    NoteType getNoteType(long id);

    Note getNote(long id, String password);

    void saveNote(Note note);
}
