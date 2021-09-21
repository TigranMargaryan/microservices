package com.notes.noteservice.service;

import com.notes.noteservice.context.UserDetailService;
import com.notes.noteservice.domain.model.Note;
import com.notes.noteservice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService implements INoteService {

    private final NoteRepository noteRepository;

    private final UserDetailService userDetailService;

    private final ObjectAccessService accessService;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserDetailService userDetailService,
                       ObjectAccessService accessService) {
        this.noteRepository = noteRepository;
        this.userDetailService = userDetailService;
        this.accessService = accessService;
    }

    @Override
    public Note getById(String id) throws AccessDeniedException {
        Note note = noteRepository.findById(id);
        accessService.check(note, userDetailService);
        return note;
    }

    @Override
    public List<Note> getAllByUserEmail() {
        List<Note> notes = noteRepository.findAllByUserEmail(userDetailService.getEmail());

        if (notes == null || notes.isEmpty()) {
            return new ArrayList<>();
        }

        return notes;
    }

    @Override
    public void create(Note note) {

        if (note == null) {
            throw new NullPointerException("note.null");
        }

        if (userDetailService == null) {
            throw new NullPointerException("user.null");
        }

        note.setUserEmail(userDetailService.getEmail());
        noteRepository.save(note);
    }

    @Override
    public void update(Note note) throws AccessDeniedException {
        accessService.check(note, userDetailService);
        noteRepository.save(note);
    }

    @Override
    public void delete(String id) throws AccessDeniedException {
        accessService.check(id, userDetailService);
        noteRepository.delete(noteRepository.findById(id));
    }
}
