package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.model.ReservationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationResponse(
        UUID id,
        UUID roomId,
        String roomName,
        UUID requesterId,
        String requesterFullName,
        String requesterEmail,
        LocalDateTime startAt,
        LocalDateTime endAt,
        ReservationStatus status,
        LocalDateTime createdAt,
        LocalDateTime cancelledAt

) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getRoom().getId(),
                reservation.getRoom().getName(),
                reservation.getRequester().getId(),
                reservation.getRequester().getName(),
                reservation.getRequester().getEmail(),
                reservation.getTimeSlot().startAt(),
                reservation.getTimeSlot().endAt(),
                reservation.getStatus(),
                reservation.getCreatedAt(),
                reservation.getCancelledAt()
        );
    }
}
