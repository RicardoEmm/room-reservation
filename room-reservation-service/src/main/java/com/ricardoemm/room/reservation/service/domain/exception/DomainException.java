package com.ricardoemm.room.reservation.service.domain.exception;

public sealed abstract class DomainException extends RuntimeException
    permits RoomNotAvailableException,
            InvalidReservationTimeException,
            UnauthorizedCancellationException,
            InvalidReservationStateException {

    protected DomainException(String message) {
        super(message);
    }
}
