package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.model.UserRole;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "surname is required")
        String surname,
        @NotBlank(message = "email is required")
        @Email(message = "email format is not valid")
        String email,
        @NotNull(message = "role is required")
        UserRole role
) {
    public CreateUserCommand toCommand() {
        return new CreateUserCommand(name, surname, email, role);
    }
}
