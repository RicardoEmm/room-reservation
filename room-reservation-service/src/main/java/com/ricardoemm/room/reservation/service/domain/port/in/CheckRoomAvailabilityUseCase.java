package com.ricardoemm.room.reservation.service.domain.port.in;

import com.ricardoemm.room.reservation.service.domain.port.in.command.CheckRoomAvailabilityQuery;

public interface CheckRoomAvailabilityUseCase {
    boolean checkAvailability(CheckRoomAvailabilityQuery query);
}
