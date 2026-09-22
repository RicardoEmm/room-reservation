package com.ricardoemm.room.reservation.service.domain.port.in;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateReservationCommand;

public interface CreateReservationUseCase {
    Reservation create(CreateReservationCommand command);
}
