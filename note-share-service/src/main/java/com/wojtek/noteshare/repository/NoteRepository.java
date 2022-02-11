package com.wojtek.noteshare.repository;

import com.wojtek.noteshare.repository.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE type = 'PUBLIC' AND expiration_date > current_date")
    List<Note> findPublicNotes();
}
