package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence;

import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.port.out.UserRepositoryPort;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.repository.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepositoryPort {

    private final SpringDataUserRepository userRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        var entityToCreate = userRepository.save(mapper.toEntity(user));
        return mapper.toDomain(entityToCreate);
    }
}
