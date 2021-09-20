package com.notes.noteservice.service;

import com.notes.noteservice.domain.model.Note;

public interface INoteService {

    Note getById(String id);

    void create(Note note);

    void update(Note note);

    void delete(String note);
}
