package com.ricardoemm.room.reservation.service.application.service;

import com.ricardoemm.room.reservation.service.domain.model.Room;
import com.ricardoemm.room.reservation.service.domain.port.in.CreateRoomUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateRoomCommand;
import com.ricardoemm.room.reservation.service.domain.port.out.RoomRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoomService implements CreateRoomUseCase {

    private final RoomRepositoryPort roomRepositoryPort;

    @Override
    public Room create(CreateRoomCommand command) {
        var mewRoom = command.toEntity();
        return roomRepositoryPort.save(mewRoom);
    }
}
