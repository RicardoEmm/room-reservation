package com.ricardoemm.room.reservation.service.domain.port.in;

import com.ricardoemm.room.reservation.service.domain.port.in.command.CancelReservationCommand;

public interface CancelReservationUseCase {
    void cancel(CancelReservationCommand command);
}
