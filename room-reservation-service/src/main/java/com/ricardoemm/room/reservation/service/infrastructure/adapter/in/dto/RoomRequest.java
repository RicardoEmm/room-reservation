package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateRoomCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RoomRequest(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "location is required")
        String location,
        @Min(value = 1, message = "capacity must be greater or equal to 1")
        int capacity
) {
    public CreateRoomCommand toCommand() {
        return new CreateRoomCommand(name, location, capacity);
    }
}
