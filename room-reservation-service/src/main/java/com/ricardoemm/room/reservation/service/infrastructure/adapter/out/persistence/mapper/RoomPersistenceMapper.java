package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.mapper;

import com.ricardoemm.room.reservation.service.domain.model.Room;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.entity.RoomJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomPersistenceMapper {

    public RoomJpaEntity toEntity(Room room) {
        if (room == null)
            return null;

        return RoomJpaEntity.builder()
                .id(room.getId())
                .name(room.getName())
                .location(room.getLocation())
                .capacity(room.getCapacity())
                .active(room.isActive())
                .build();
    }

    public Room toDomain(RoomJpaEntity entity) {
        if (entity == null)
            return null;

        return Room.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getLocation(),
                entity.getCapacity(),
                entity.isActive()
        );
    }
}
