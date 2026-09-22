package com.ricardoemm.room.reservation.service.domain.port.in;

import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateUserCommand;

public interface CreateUserUseCase {
    User create(CreateUserCommand command);
}
