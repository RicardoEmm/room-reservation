package com.ricardoemm.room.reservation.service.domain.port.in.command;

import com.ricardoemm.room.reservation.service.domain.model.User;

import java.time.Clock;

public record CancelReservationCommand(
        User actor,
        Clock clock
) {
}
