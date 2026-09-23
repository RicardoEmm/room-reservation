package com.ricardoemm.room.reservation.service.application.service;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.port.in.ListReservationsByUserUseCase;
import com.ricardoemm.room.reservation.service.domain.port.out.ReservationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListReservationsByUserService implements ListReservationsByUserUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;
    @Override
    public List<Reservation> listByRequesterId(UUID requesterId) {
        return reservationRepositoryPort.findByRequesterId(requesterId);
    }
}
