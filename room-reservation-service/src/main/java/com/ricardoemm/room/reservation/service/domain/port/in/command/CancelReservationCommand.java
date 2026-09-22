package com.ricardoemm.room.reservation.service.domain.port.in.command;

import com.ricardoemm.room.reservation.service.domain.model.User;

import java.time.Clock;
import java.util.UUID;

public record CancelReservationCommand(
        UUID reservationId,
        UUID actorId
) {
}
