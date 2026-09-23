package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.mapper;

import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public UserJpaEntity toEntity(User user) {
        if (user == null)
            return null;

        return UserJpaEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User toDomain(UserJpaEntity entity) {
        if (entity == null)
            return null;

        return User.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getRole()
        );
    }
}
