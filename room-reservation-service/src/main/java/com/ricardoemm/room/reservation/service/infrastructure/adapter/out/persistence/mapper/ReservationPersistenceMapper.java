package com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.mapper;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.model.TimeSlot;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.out.persistence.entity.ReservationJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservationPersistenceMapper {

    private final RoomPersistenceMapper roomPersistenceMapper;
    private final UserPersistenceMapper userPersistenceMapper;

    private ReservationPersistenceMapper(
            RoomPersistenceMapper roomPersistenceMapper,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.roomPersistenceMapper = roomPersistenceMapper;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    public ReservationJpaEntity toEntity(Reservation reservation) {
        if (reservation == null)
            return null;

        return ReservationJpaEntity.builder()
                .id(reservation.getId())
                .room(roomPersistenceMapper.toEntity(reservation.getRoom()))
                .requester(userPersistenceMapper.toEntity(reservation.getRequester()))
                .status(reservation.getStatus())
                .startAt(reservation.getTimeSlot().startAt())
                .endAt(reservation.getTimeSlot().endAt())
                .createdAt(reservation.getCreatedAt())
                .cancelledAt(reservation.getCancelledAt())
                .cancelledBy(userPersistenceMapper.toEntity(reservation.getCancelledBy()))
                .build();
    }

    public Reservation toDomain(ReservationJpaEntity entity) {
        return Reservation.reconstitute(
                entity.getId(),
                roomPersistenceMapper.toDomain(entity.getRoom()),
                userPersistenceMapper.toDomain(entity.getRequester()),
                entity.getStatus(),
                new TimeSlot(entity.getStartAt(), entity.getEndAt()),
                entity.getCreatedAt(),
                entity.getCancelledAt(),
                userPersistenceMapper.toDomain(entity.getCancelledBy())
        );
    }
}
