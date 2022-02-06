package com.wojtek.noteshare.repository;

import com.wojtek.noteshare.repository.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void repositorySaveTest() {
        this.noteRepository.save(new Note());

        assertThat(this.noteRepository.count()).isEqualTo(1);
    }
}
