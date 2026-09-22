package com.ricardoemm.room.reservation.service.domain.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private String surname;
    private String email;
    private UserRole role;

    private User(UUID id, String name, String surname, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public static User createNew(String name, String surname, String email, UserRole role) {
        return new User(null, name, surname, email, role);
    }

    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public UserRole getRole() { return role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname)
                && Objects.equals(email, user.email)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, role);
    }
}