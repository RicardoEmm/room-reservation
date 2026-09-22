package com.ricardoemm.room.reservation.service.domain.port.in.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckRoomAvailabilityQuery(
        UUID roomId,
        LocalDateTime startAt,
        LocalDateTime endAt,
        int attendees
) {
}
