package com.ricardoemm.room.reservation.service.application.service;

import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.port.in.CreateUserUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateUserCommand;
import com.ricardoemm.room.reservation.service.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    @Override
    public User create(CreateUserCommand command) {
        var newUser = command.toEntity();
        return userRepositoryPort.save(newUser);
    }
}
