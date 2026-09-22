package com.ricardoemm.room.reservation.service.domain.port.in.command;

import com.ricardoemm.room.reservation.service.domain.model.TimeSlot;

import java.util.UUID;

public record CreateReservationCommand(
        UUID roomId,
        UUID requesterId,
        TimeSlot timeSlot,
        int attendees
) {
}
