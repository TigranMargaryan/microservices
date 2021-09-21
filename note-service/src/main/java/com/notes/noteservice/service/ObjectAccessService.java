package com.notes.noteservice.service;

import com.notes.noteservice.context.UserDetailService;
import com.notes.noteservice.domain.model.Note;
import com.notes.noteservice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.AccessDeniedException;

@Service
public class ObjectAccessService {

    private final NoteRepository noteRepository;

    @Autowired
    public ObjectAccessService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void check(Note note, UserDetailService detailService) throws AccessDeniedException {
        accessChecker(note, detailService);
    }

    public void check(String id, UserDetailService detailService) throws AccessDeniedException {
        Note note = noteRepository.findById(id);
        accessChecker(note, detailService);
    }

    private void accessChecker(Note note, UserDetailService detailService) throws AccessDeniedException {

        if (detailService == null) {
            throw new AccessDeniedException("access.denied");
        }

        if (note == null) {
            throw new NullPointerException("note.null");
        }

        if (!note.getUserEmail().equals(detailService.getEmail())) {
            throw new AccessDeniedException("access.denied");
        }
    }
}
