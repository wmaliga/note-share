package com.wojtek.noteshare.service.impl;

import com.wojtek.noteshare.exception.InvalidDataException;
import com.wojtek.noteshare.exception.NoteAuthorizationException;
import com.wojtek.noteshare.exception.NoteExpiredException;
import com.wojtek.noteshare.repository.NoteRepository;
import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;
import com.wojtek.noteshare.util.NoteTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.time.LocalDate.now;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {

    private static final long ID = 1L;

    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepositoryMock;

    @Test
    public void findPublicNotesTest() {
        this.noteService.findPublicNotes();

        verify(this.noteRepositoryMock).findPublicNotes();
    }

    @Test
    public void getNoteTypeTest() {
        Note note = NoteTestBuilder.publicNote();
        when(this.noteRepositoryMock.findById(ID)).thenReturn(of(note));

        NoteType result = this.noteService.getNoteType(ID);

        assertThat(result).isEqualTo(NoteType.PUBLIC);
    }

    @Test
    public void getPublicNoteTest() {
        Note note = NoteTestBuilder.publicNote();
        when(this.noteRepositoryMock.findById(ID)).thenReturn(of(note));

        Note result = this.noteService.getNote(ID, null);

        assertThat(result).isEqualTo(note);
    }

    @Test
    public void getExpiredNoteTest() {
        Note note = NoteTestBuilder.publicNoteBuilder()
                .expirationDate(now().minusDays(1)).build();
        when(this.noteRepositoryMock.findById(ID)).thenReturn(of(note));

        assertThatExceptionOfType(NoteExpiredException.class)
                .isThrownBy(() -> this.noteService.getNote(ID, null));
    }

    @Test
    public void getPrivateNoteWithPasswordTest() {
        Note note = NoteTestBuilder.privateNote();
        when(this.noteRepositoryMock.findById(ID)).thenReturn(of(note));

        Note result = this.noteService.getNote(ID, NoteTestBuilder.PASSWORD);

        assertThat(result).isEqualTo(note);
    }

    @Test
    public void getPrivateNoteWithoutPasswordTest() {
        Note note = NoteTestBuilder.privateNote();
        when(this.noteRepositoryMock.findById(ID)).thenReturn(of(note));

        assertThatExceptionOfType(NoteAuthorizationException.class)
                .isThrownBy(() -> this.noteService.getNote(ID, ""));
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
    public void saveNoteWithPastDateTest() {
        Note note = NoteTestBuilder.publicNoteBuilder()
                .expirationDate(now().minusDays(1)).build();

        assertThatExceptionOfType(InvalidDataException.class)
                .isThrownBy(() -> this.noteService.saveNote(note));

        verify(this.noteRepositoryMock, never()).save(note);
    }

    @Test
    public void savePrivateNoteWithoutPasswordTest() {
        Note note = NoteTestBuilder.privateNoteBuilder()
                .password("").build();

        assertThatExceptionOfType(InvalidDataException.class)
                .isThrownBy(() -> this.noteService.saveNote(note));

        verify(this.noteRepositoryMock, never()).save(note);
    }
}
