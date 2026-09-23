package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.repository;

import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.entity.ReservationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataReservationRepository extends JpaRepository<ReservationJpaEntity, UUID> {

    List<ReservationJpaEntity> findAllByRequesterId(UUID requesterId);
    List<ReservationJpaEntity> findAllByRoomId(UUID roomId);
}
