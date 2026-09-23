package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence;

import com.ricardoemm.room.reservation.service.domain.model.Room;
import com.ricardoemm.room.reservation.service.domain.port.out.RoomRepositoryPort;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.mapper.RoomPersistenceMapper;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.repository.SpringDataRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaRoomRepository implements RoomRepositoryPort {

    private final SpringDataRoomRepository roomRepository;
    private final RoomPersistenceMapper mapper;

    @Override
    public Optional<Room> findById(UUID id) {
        return roomRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Room save(Room room) {
        var entityToCreate = roomRepository.save(mapper.toEntity(room));
        return mapper.toDomain(entityToCreate);
    }
}
