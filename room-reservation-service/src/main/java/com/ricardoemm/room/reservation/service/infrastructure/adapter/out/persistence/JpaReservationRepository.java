package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.port.out.ReservationRepositoryPort;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.mapper.ReservationPersistenceMapper;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.repository.SpringDataReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaReservationRepository implements ReservationRepositoryPort {

    private final SpringDataReservationRepository reservationRepository;
    private final ReservationPersistenceMapper mapper;

    @Override
    public Optional<Reservation> findById(UUID id) {
        return reservationRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Reservation save(Reservation reservation) {
        var entityToCreate = reservationRepository.save(mapper.toEntity(reservation));
        return mapper.toDomain(entityToCreate);
    }

    @Override
    public List<Reservation> findByRequesterId(UUID requesterId) {
        return reservationRepository.findAllByRequesterId(requesterId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Reservation> findByRoomId(UUID roomId) {
        return reservationRepository.findAllByRoomId(roomId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
