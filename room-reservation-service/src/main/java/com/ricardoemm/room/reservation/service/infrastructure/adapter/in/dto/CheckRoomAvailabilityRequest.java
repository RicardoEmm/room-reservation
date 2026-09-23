package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.port.in.command.CheckRoomAvailabilityQuery;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckRoomAvailabilityRequest(
        LocalDateTime starAt,
        LocalDateTime endAt,
        int attendees
) {
    public CheckRoomAvailabilityQuery toQuery(UUID roomId) {
        return new CheckRoomAvailabilityQuery(roomId, starAt, endAt, attendees);
    }
}
