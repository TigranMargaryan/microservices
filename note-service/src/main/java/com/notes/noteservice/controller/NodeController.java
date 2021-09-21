package com.notes.noteservice.controller;

import com.notes.noteservice.config.Response;
import com.notes.noteservice.domain.model.Note;
import com.notes.noteservice.domain.data.NoteData;
import com.notes.noteservice.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/notes")
public class NodeController {

    public static final String NOTE = "note";

    private final ModelMapper modelMapper;
    private final INoteService noteService;

    @Autowired
    public NodeController(ModelMapper modelMapper, INoteService noteService) {
        this.modelMapper = modelMapper;
        this.noteService = noteService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getNote(@PathVariable("id") String id) throws AccessDeniedException {
        Note note = noteService.getById(id);

        return new Response<>(new HashMap<String, NoteData>() {{
            put(NOTE, modelMapper.map(note, NoteData.class));
        }});
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllNotes() {
        List<Note> notes = noteService.getAllByUserEmail();

        List<NoteData> noteData = new ArrayList<>();

        for (Note note : notes) {
            NoteData data = modelMapper.map(note, NoteData.class);
            noteData.add(data);
        }
        return new Response<>(new HashMap<String, List<NoteData>>() {{
            put(NOTE, noteData);
        }});
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createNote(@RequestBody NoteData noteData) {
        Note note = modelMapper.map(noteData, Note.class);
        noteService.create(note);

        return new Response<>(new HashMap<String, NoteData>() {{
            put(NOTE, modelMapper.map(note, NoteData.class));
        }});
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateNote(@PathVariable("id") String id,
                               @RequestBody NoteData noteData) throws AccessDeniedException {

        Note note = noteService.getById(id);
        modelMapper.map(noteData, note);
        noteService.update(note);

        return new Response<>(new HashMap<String, NoteData>() {{
            put(NOTE, modelMapper.map(note, NoteData.class));
        }});
    }

    @DeleteMapping(value = "{id}")
    public Response deleteNote(@PathVariable("id") String id) throws AccessDeniedException {
        noteService.delete(id);
        return new Response("Deleted");
    }
}
