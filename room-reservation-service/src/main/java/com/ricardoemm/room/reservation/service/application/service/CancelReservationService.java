package com.ricardoemm.room.reservation.service.application.service;

import com.ricardoemm.room.reservation.service.domain.exception.ReservationNotFoundException;
import com.ricardoemm.room.reservation.service.domain.exception.UserNotFoundException;
import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.port.in.CancelReservationUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.command.CancelReservationCommand;
import com.ricardoemm.room.reservation.service.domain.port.out.ReservationRepositoryPort;
import com.ricardoemm.room.reservation.service.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
@RequiredArgsConstructor
public class CancelReservationService implements CancelReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final Clock clock;

    @Override
    @Transactional
    public void cancel(CancelReservationCommand command) {
        Reservation reservation = reservationRepositoryPort.findById(command.reservationId()).orElseThrow(
                () -> new ReservationNotFoundException("reservation with ID: " + command.reservationId() + " not found")
        );

        User actor = userRepositoryPort.findById(command.actorId()).orElseThrow(
                () -> new UserNotFoundException("user with ID: " + command.actorId() + " not found")
        );

        reservation.cancel(actor, clock);

        reservationRepositoryPort.save(reservation);
    }
}
