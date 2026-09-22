package com.ricardoemm.room.reservation.service.domain.port.in.command;

import com.ricardoemm.room.reservation.service.domain.model.Room;

public record CreateRoomCommand(
        String name,
        String location,
        int capacity
) {
    public Room toEntity() {
        return Room.createRoom(name, location, capacity);
    }
}
