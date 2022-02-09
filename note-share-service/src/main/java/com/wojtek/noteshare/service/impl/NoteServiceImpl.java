package com.wojtek.noteshare.service.impl;

import com.wojtek.noteshare.exception.InvalidDataException;
import com.wojtek.noteshare.exception.NoteAuthorizationException;
import com.wojtek.noteshare.exception.NoteNotFoundException;
import com.wojtek.noteshare.repository.NoteRepository;
import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;
import com.wojtek.noteshare.service.NoteService;
import com.wojtek.noteshare.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    private final NoteRepository noteRepository;

    @Override
    public List<Note> findPublicNotes() {
        return this.noteRepository.findByTypeEquals(NoteType.PUBLIC);
    }

    @Override
    public NoteType getNoteType(long id) {
        return this.noteRepository.findById(id)
                .map(Note::getType)
                .orElseThrow(NoteNotFoundException::new);
    }

    @Override
    public Note getNote(long id, String password) {
        Note note = this.noteRepository.findById(id)
                .orElseThrow(NoteNotFoundException::new);

        if (note.getType() == NoteType.PRIVATE &&
                !note.getPassword().equals(PasswordUtil.encryptPassword(password))) {
            throw new NoteAuthorizationException();
        }

        return note;
    }

    @Override
    public void saveNote(Note note) {
        if (note.getType() == NoteType.PRIVATE) {
            if (isEmpty(note.getPassword())) {
                throw new InvalidDataException("Missing password for private note");
            }

            String encryptedPassword = PasswordUtil.encryptPassword(note.getPassword());
            note.setPassword(encryptedPassword);
        } else {
            note.setPassword(null);
        }

        this.noteRepository.save(note);
    }
}
