package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.repository;

import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, UUID> {
}
