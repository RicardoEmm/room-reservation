package com.ricardoemm.room.reservation.service.domain.port.in;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ListReservationsByUserUseCase {
    List<Reservation> listByRequesterId(UUID requesterId);
}
