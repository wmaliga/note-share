package com.wojtek.noteshare.service.impl;

import com.wojtek.noteshare.repository.NoteRepository;
import com.wojtek.noteshare.repository.model.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepositoryMock;

    @Test
    public void findAllNotesTest() {
        this.noteService.findAllNotes();

        verify(this.noteRepositoryMock).findAll();
    }

    @Test
    public void findNoteByIdTest() {
        Note note = mock(Note.class);
        when(this.noteRepositoryMock.findById(1L)).thenReturn(Optional.of(note));

        Note result = this.noteService.findNoteById(1L);

        assertThat(result).isEqualTo(note);
    }
}
