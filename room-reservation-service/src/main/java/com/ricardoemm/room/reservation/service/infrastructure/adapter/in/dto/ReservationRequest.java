package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.model.TimeSlot;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateReservationCommand;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequest(
        @NotNull(message = "roomId is required")
        UUID roomId,
        @NotNull(message = "requesterId is required")
        UUID requesterId,
        @NotNull(message = "startAt is required")
        @Future(message = "startAt must be in future")
        LocalDateTime startAt,
        @NotNull(message = "endAt is required")
        LocalDateTime endAt,
        @Min(value = 1, message = "attendees must be greater or equal to 1")
        int attendees
) {
        public CreateReservationCommand toCommand() {
                return new CreateReservationCommand(roomId, requesterId, new TimeSlot(startAt, endAt), attendees);
        }
}
