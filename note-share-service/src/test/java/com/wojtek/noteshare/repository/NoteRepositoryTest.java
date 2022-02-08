package com.wojtek.noteshare.repository;

import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;
import com.wojtek.noteshare.util.NoteTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @BeforeEach
    public void before() {
        this.noteRepository.deleteAll();
    }

    @Test
    public void findNotesByTypeTest() {
        Note publicNote = NoteTestBuilder.publicNote();
        Note privateNote = NoteTestBuilder.privateNote();

        this.noteRepository.save(publicNote);
        this.noteRepository.save(privateNote);

        assertThat(this.noteRepository.findByTypeEquals(NoteType.PUBLIC)).hasSize(1);
        assertThat(this.noteRepository.findByTypeEquals(NoteType.PRIVATE)).hasSize(1);
    }

    @Test
    public void validNoteSaveTest() {
        Note note = NoteTestBuilder.publicNote();
        assertThatNoException().isThrownBy(() -> this.noteRepository.save(note));
    }

    @Test
    public void constraintValidationTest() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.noteRepository.save(new Note()));
    }
}
