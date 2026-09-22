package com.ricardoemm.room.reservation.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private UserRole role;
}
