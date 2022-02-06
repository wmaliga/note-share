package com.wojtek.noteshare.repository;

import com.wojtek.noteshare.repository.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}
