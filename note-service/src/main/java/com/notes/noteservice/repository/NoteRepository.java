package com.notes.noteservice.repository;

import com.notes.noteservice.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Integer> {
}
