package com.ricardoemm.room.reservation.service.domain.port.out;

import com.ricardoemm.room.reservation.service.domain.model.Room;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepositoryPort {
    Optional<Room> findById(UUID id);
    Room save(Room room);
}
