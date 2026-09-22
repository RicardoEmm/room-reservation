package com.ricardoemm.room.reservation.service.application.service;

import com.ricardoemm.room.reservation.service.domain.exception.RoomNotFoundException;
import com.ricardoemm.room.reservation.service.domain.model.Room;
import com.ricardoemm.room.reservation.service.domain.model.TimeSlot;
import com.ricardoemm.room.reservation.service.domain.port.in.CheckRoomAvailabilityUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CheckRoomAvailabilityQuery;
import com.ricardoemm.room.reservation.service.domain.port.out.ReservationRepositoryPort;
import com.ricardoemm.room.reservation.service.domain.port.out.RoomRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckRoomAvailabilityService implements CheckRoomAvailabilityUseCase {

    private final RoomRepositoryPort roomRepositoryPort;
    private final ReservationRepositoryPort reservationRepositoryPort;

    @Override
    public boolean checkAvailability(CheckRoomAvailabilityQuery query) {
        Room room = roomRepositoryPort.findById(query.roomId()).orElseThrow(
                () -> new RoomNotFoundException("room with ID: " + query.roomId() + " not found")
        );

        if (!room.canHost(query.attendees()))
            return false;

        TimeSlot timeSlot = new TimeSlot(query.startAt(), query.endAt());

        boolean hasOverLap = reservationRepositoryPort.findByRoomId(query.roomId())
                .stream()
                .anyMatch(existing -> existing.overlapsWith(timeSlot));

        return !hasOverLap;
    }
}
