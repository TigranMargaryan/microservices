package com.notes.noteservice.service;

import com.notes.noteservice.domain.model.Note;
import com.notes.noteservice.repository.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService implements INoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note getById(String id) {
        Note note = noteRepository.findById(id);

        if (note == null) {
            throw new NullPointerException("Note with this id is null: " + id);
        }
        return note;
    }

    @Override
    public void create(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void update(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void delete(String id) {
        noteRepository.delete(noteRepository.findById(id));
    }
}
