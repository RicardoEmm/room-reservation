package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.model.Room;

import java.util.UUID;

public record RoomResponse(
        UUID id,
        String name,
        String location,
        int capacity
) {
    public static RoomResponse from(Room room) {
        return new RoomResponse(room.getId(), room.getName(), room.getLocation(), room.getCapacity());
    }
}
