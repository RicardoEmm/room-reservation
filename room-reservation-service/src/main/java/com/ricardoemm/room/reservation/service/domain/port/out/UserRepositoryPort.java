package com.ricardoemm.room.reservation.service.domain.port.out;

import com.ricardoemm.room.reservation.service.domain.model.User;

public interface UserRepositoryPort {
    User save(User user);
}
