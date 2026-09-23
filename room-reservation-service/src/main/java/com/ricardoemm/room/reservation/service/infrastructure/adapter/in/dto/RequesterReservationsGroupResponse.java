package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record RequesterReservationsGroupResponse(
        UUID requesterId,
        List<ReservationItemResponse> reservations
) {
    public static List<RequesterReservationsGroupResponse> fromList(List<Reservation> reservations) {
        return reservations
                .stream()
                .collect(Collectors.groupingBy(reservation -> reservation.getRequester().getId()))
                .entrySet()
                .stream().map(entry -> new RequesterReservationsGroupResponse(
                        entry.getKey(),
                        entry.getValue().stream().map(ReservationItemResponse::from).toList()
                )).toList();
    }
}
