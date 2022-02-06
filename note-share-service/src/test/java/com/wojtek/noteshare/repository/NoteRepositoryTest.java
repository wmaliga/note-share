package com.wojtek.noteshare.repository;

import com.wojtek.noteshare.repository.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void validNoteTest() {
        assertThatNoException().isThrownBy(() -> this.noteRepository.save(createNote()));
    }

    @Test
    public void constraintValidationTest() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.noteRepository.save(new Note()));
    }

    private Note createNote() {
        return Note.builder().title("title").data("data").build();
    }
}
