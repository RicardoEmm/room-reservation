package com.ricardoemm.room.reservation.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Room {
    private UUID id;
    private String name;
    private String location;
    private int capacity;
    private boolean active;

    public boolean canHost(int attendees) {
        return active && attendees <= capacity;
    }
}
