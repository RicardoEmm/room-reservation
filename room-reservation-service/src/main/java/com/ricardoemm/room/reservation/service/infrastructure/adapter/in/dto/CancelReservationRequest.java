package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.port.in.command.CancelReservationCommand;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CancelReservationRequest(
        @NotNull(message = "reservationId is required")
        UUID reservationId,
        @NotNull(message = "actorId is required")
        UUID actorId
) {
    public CancelReservationCommand toCommand() {
        return new CancelReservationCommand(reservationId, actorId);
    }
}
