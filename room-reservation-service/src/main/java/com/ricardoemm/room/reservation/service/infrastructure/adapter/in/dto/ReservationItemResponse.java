package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.model.ReservationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationItemResponse(
        UUID id,
        UUID roomId,
        String roomName,
        LocalDateTime startAt,
        LocalDateTime endAt,
        ReservationStatus status,
        LocalDateTime createdAt,
        LocalDateTime cancelledAt
) {
    public static ReservationItemResponse from(Reservation r) {
        return new ReservationItemResponse(
                r.getId(),
                r.getRoom().getId(),
                r.getRoom().getName(),
                r.getTimeSlot().startAt(),
                r.getTimeSlot().endAt(),
                r.getStatus(),
                r.getCreatedAt(),
                r.getCancelledAt()
        );
    }
}
