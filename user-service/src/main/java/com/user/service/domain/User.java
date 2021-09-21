package com.user.service.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "email", nullable = false)
    @NotBlank
    private String email;

    @Size(min = 8)
    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "created", nullable = false)
    public Date created;

    @Column(name = "updated", nullable = false)
    public Date updated;

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString().replace("-", "");;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated() {
        this.created =  new Date();;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated() {
        this.updated =  new Date();;
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
