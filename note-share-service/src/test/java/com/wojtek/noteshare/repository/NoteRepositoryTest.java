package com.wojtek.noteshare.repository;

import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.util.NoteTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

import static java.time.LocalDate.now;
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
    public void findPublicNotesTest() {
        Note publicNote = NoteTestBuilder.publicNoteBuilder()
                .title("public").build();
        Note publicNoExpirationNote = NoteTestBuilder.publicNoteBuilder()
                .title("public-no-expiration").expirationDate(null).build();
        Note publicExpiredNote = NoteTestBuilder.publicNoteBuilder()
                .title("public-expired").expirationDate(now().minusDays(1)).build();
        Note privateNote = NoteTestBuilder.privateNoteBuilder()
                .title("private").build();

        this.noteRepository.save(publicNote);
        this.noteRepository.save(publicNoExpirationNote);
        this.noteRepository.save(publicExpiredNote);
        this.noteRepository.save(privateNote);

        assertThat(this.noteRepository.findPublicNotes())
                .extracting(Note::getTitle).containsOnly("public", "public-no-expiration");
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
