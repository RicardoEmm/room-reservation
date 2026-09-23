package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.model.UserRole;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String surname,
        String email,
        UserRole role
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );
    }
}
