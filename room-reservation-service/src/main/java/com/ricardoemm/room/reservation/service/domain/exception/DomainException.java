package com.ricardoemm.room.reservation.service.domain.exception;

public sealed abstract class DomainException extends RuntimeException
        permits InvalidReservationStateException,
        InvalidReservationTimeException,
        ReservationNotFoundException,
        RoomHasNotCapacityException,
        RoomNotAvailableException,
        RoomNotFoundException,
        UnauthorizedCancellationException,
        UserNotFoundException {

    protected DomainException(String message) {
        super(message);
    }
}
