package com.ricardoemm.room.reservation.service.domain.port.in.command;

public record CreateRoomCommand(
        String name,
        String location,
        int capacity
) {
}
