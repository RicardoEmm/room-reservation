package com.ricardoemm.room.reservation.service.domain.model;

import com.ricardoemm.room.reservation.service.domain.exception.InvalidReservationTimeException;

import java.time.Duration;
import java.time.LocalDateTime;

public record TimeSlot(LocalDateTime startAt, LocalDateTime endAt) {
    public TimeSlot {
        if (!endAt.isAfter(startAt))
            throw new InvalidReservationTimeException("");
        var minutes = Duration.between(startAt, endAt).toMinutes();
        if (minutes < 15 || minutes > 240)
            throw new InvalidReservationTimeException("");
    }
}
