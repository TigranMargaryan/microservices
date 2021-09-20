package com.notes.noteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Note {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Size(max = 50)
    @Column(name = "title", nullable = false)
    @NotBlank
    private String title;

    @Size(max = 1000)
    @Column(name = "note", nullable = false)
    @NotBlank
    private String note;

    @Column(name = "created", nullable = false)
    protected long created;

    @Column(name = "updated", nullable = false)
    protected long updated;
}
