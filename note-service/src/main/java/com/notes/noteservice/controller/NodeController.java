package com.notes.noteservice.controller;

import com.github.dozermapper.core.Mapper;
import com.notes.noteservice.config.Response;
import com.notes.noteservice.domain.model.Note;
import com.notes.noteservice.domain.data.NoteData;
import com.notes.noteservice.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/api/notes")
public class NodeController {

    private final Mapper dozerMapper;
    private final INoteService noteService;

    @Autowired
    public NodeController(Mapper dozerMapper, INoteService noteService) {
        this.dozerMapper = dozerMapper;
        this.noteService = noteService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createNote(@RequestBody NoteData noteData) {

        Note note = dozerMapper.map(noteData, Note.class);
        noteService.create(note);

        return new Response<>(new HashMap<String, NoteData>() {{
            put("note", dozerMapper.map(note, NoteData.class));
        }});
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getNote(@PathVariable("id") String id) {
        Note note = noteService.getById(id);

        return new Response<>(new HashMap<String, NoteData>() {{
            put("note", dozerMapper.map(note, NoteData.class));
        }});
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateNote(@PathVariable("id") String id, @RequestBody NoteData noteData) {

        Note note = noteService.getById(id);
        dozerMapper.map(noteData, note);
        note.setId(id);
        noteService.update(note);

        return new Response<>(new HashMap<String, NoteData>() {{
            put("note", dozerMapper.map(note, NoteData.class));
        }});
    }

    @DeleteMapping(value = "{id}")
    public Response deleteNote(@PathVariable("id") String id) {
        noteService.delete(id);
        return new Response("Deleted");
    }
}
