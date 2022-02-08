package com.wojtek.noteshare.service.impl;

import com.wojtek.noteshare.exception.InvalidDataException;
import com.wojtek.noteshare.repository.NoteRepository;
import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;
import com.wojtek.noteshare.util.NoteTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepositoryMock;

    @Test
    public void findPublicNotesTest() {
        this.noteService.findPublicNotes();

        verify(this.noteRepositoryMock).findByTypeEquals(NoteType.PUBLIC);
    }

    @Test
    public void findNoteByIdTest() {
        Note note = new Note();
        when(this.noteRepositoryMock.findById(1L)).thenReturn(Optional.of(note));

        Note result = this.noteService.findNoteById(1L);

        assertThat(result).isEqualTo(note);
    }

    @Test
    public void savePublicNoteTest() {
        Note note = NoteTestBuilder.publicNote();

        this.noteService.saveNote(note);

        verify(this.noteRepositoryMock).save(note);
    }

    @Test
    public void savePrivateNoteTest() {
        Note note = NoteTestBuilder.privateNote();

        this.noteService.saveNote(note);

        verify(this.noteRepositoryMock).save(note);
    }

    @Test
    public void savePrivateNoteWithoutPasswordTest() {
        Note note = NoteTestBuilder.privateNote();NoteTestBuilder.privateNote();
        note.setPassword("");

        assertThatExceptionOfType(InvalidDataException.class)
                .isThrownBy(() -> this.noteService.saveNote(note));

        verify(this.noteRepositoryMock, never()).save(note);
    }
}
