package com.notes.noteservice.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
public class Note {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    @NotBlank
    private String title;

    @Column(name = "user_email", nullable = false)
    @NotBlank
    private String userEmail;

    @Size(max = 1000)
    @Column(name = "note", nullable = false)
    @NotBlank
    private String note;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "updated", nullable = false)
    private Date updated;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = new Date();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated() {
        this.updated = new Date();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @PrePersist
    private void beforeSave() {
        setId();
        setCreated();
        setUpdated();
    }

    @PreUpdate
    private void beforeUpdate() {
        setUpdated();
    }
}
