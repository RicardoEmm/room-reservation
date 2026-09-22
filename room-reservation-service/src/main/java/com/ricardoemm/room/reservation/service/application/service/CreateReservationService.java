package com.ricardoemm.room.reservation.service.application.service;

import com.ricardoemm.room.reservation.service.domain.exception.RoomHasNotCapacityException;
import com.ricardoemm.room.reservation.service.domain.exception.RoomNotAvailableException;
import com.ricardoemm.room.reservation.service.domain.exception.RoomNotFoundException;
import com.ricardoemm.room.reservation.service.domain.exception.UserNotFoundException;
import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.model.Room;
import com.ricardoemm.room.reservation.service.domain.model.TimeSlot;
import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.port.in.CreateReservationUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CreateReservationCommand;
import com.ricardoemm.room.reservation.service.domain.port.out.ReservationRepositoryPort;
import com.ricardoemm.room.reservation.service.domain.port.out.RoomRepositoryPort;
import com.ricardoemm.room.reservation.service.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.Clock;

@Service
@RequiredArgsConstructor
public class CreateReservationService implements CreateReservationUseCase {

    private final RoomRepositoryPort roomRepositoryPort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final Clock clock;

    @Override
    @Transactional
    public Reservation create(CreateReservationCommand command) {
        Room room = roomRepositoryPort.findById(command.roomId()).orElseThrow(
                 () -> new RoomNotFoundException("room with ID: " + command.roomId() + " not found"));

        User requester = userRepositoryPort.findById(command.requesterId()).orElseThrow(
                () -> new UserNotFoundException("user with ID: " + command.requesterId() + " not found"));

        if (!room.canHost(command.attendees()))
            throw new RoomHasNotCapacityException("room with ID: " + command.roomId() + "has not capacity");

        TimeSlot timeSlot = new TimeSlot(command.timeSlot().startAt(), command.timeSlot().endAt());

        boolean hasOverlaps = reservationRepositoryPort.findByRoomId(command.roomId())
                .stream()
                .anyMatch(existing -> existing.overlapsWith(timeSlot));

        if (hasOverlaps)
            throw new RoomNotAvailableException("room with ID: " + command.roomId() + " is already booked for that time slot");

        Reservation reservation = Reservation.requestedBy(requester, room, timeSlot, clock);

        return reservationRepositoryPort.save(reservation);
    }
}
