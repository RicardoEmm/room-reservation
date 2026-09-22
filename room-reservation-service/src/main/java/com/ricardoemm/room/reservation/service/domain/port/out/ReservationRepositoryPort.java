package com.ricardoemm.room.reservation.service.domain.port.out;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationRepositoryPort {
    Reservation save(Reservation reservation);
    List<Reservation> findByUserId(UUID userId);
}
