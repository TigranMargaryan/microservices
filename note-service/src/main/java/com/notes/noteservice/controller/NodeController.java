package com.notes.noteservice.controller;

import com.notes.noteservice.config.Response;
import com.notes.noteservice.domain.model.Note;
import com.notes.noteservice.domain.data.NoteData;
import com.notes.noteservice.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/notes")
public class NodeController {

    private final ModelMapper modelMapper;
    private final INoteService noteService;

    @Autowired
    public NodeController(ModelMapper modelMapper, INoteService noteService) {
        this.modelMapper = modelMapper;
        this.noteService = noteService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createNote(@RequestBody NoteData noteData) {

        Note note = modelMapper.map(noteData, Note.class);
        noteService.create(note);

        return new Response<>(new HashMap<String, NoteData>() {{
            put("note", modelMapper.map(note, NoteData.class));
        }});
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getNote(@PathVariable("id") String id) {
        Note note = noteService.getById(id);

        return new Response<>(new HashMap<String, NoteData>() {{
            put("note", modelMapper.map(note, NoteData.class));
        }});
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateNote(@PathVariable("id") String id, @RequestBody NoteData noteData) {

        Note note = noteService.getById(id);
        modelMapper.map(noteData, note);
        noteService.update(note);

        return new Response<>(new HashMap<String, NoteData>() {{
            put("note", modelMapper.map(note, NoteData.class));
        }});
    }

    @DeleteMapping(value = "{id}")
    public Response deleteNote(@PathVariable("id") String id) {
        noteService.delete(id);
        return new Response("Deleted");
    }
}
