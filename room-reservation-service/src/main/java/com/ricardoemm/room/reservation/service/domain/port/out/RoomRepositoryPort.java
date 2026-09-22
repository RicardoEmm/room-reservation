package com.ricardoemm.room.reservation.service.domain.port.out;

import com.ricardoemm.room.reservation.service.domain.model.Room;

public interface RoomRepositoryPort {
    Room save(Room room);
}
