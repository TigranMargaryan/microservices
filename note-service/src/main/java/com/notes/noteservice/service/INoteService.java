package com.notes.noteservice.service;

import com.notes.noteservice.domain.model.Note;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface INoteService {

    Note getById(String id) throws AccessDeniedException;

    List<Note> getAllByUserEmail();

    void create(Note note);

    void update(Note note) throws AccessDeniedException;

    void delete(String note) throws AccessDeniedException;
}
