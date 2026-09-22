package com.ricardoemm.room.reservation.service.domain.port.in.command;

import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.model.UserRole;

import java.util.UUID;

public record CreateUserCommand(
        String name,
        String surname,
        String email,
        UserRole role
) {
    public User toEntity() {
        return User.createNew(this.name, this.surname, this.email, this.role);
    }
}
