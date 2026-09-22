package com.ricardoemm.room.reservation.service.domain.port.in;

import com.ricardoemm.room.reservation.service.domain.model.Room;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateRoomCommand;

public interface CreateRoomUseCase {
    Room create(CreateRoomCommand command);
}
