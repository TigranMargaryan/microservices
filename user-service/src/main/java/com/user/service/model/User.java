package com.user.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    private String email;

    @Size(min = 8)
    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "created", nullable = false)
    protected long created;

    @Column(name = "updated", nullable = false)
    protected long updated;
}
