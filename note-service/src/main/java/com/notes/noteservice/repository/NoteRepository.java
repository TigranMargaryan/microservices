package com.notes.noteservice.repository;

import com.notes.noteservice.domain.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {
    Note findById(String id);
}
