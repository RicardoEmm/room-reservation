package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.repository;

import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.entity.RoomJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataRoomRepository extends JpaRepository<RoomJpaEntity, UUID> {
}
