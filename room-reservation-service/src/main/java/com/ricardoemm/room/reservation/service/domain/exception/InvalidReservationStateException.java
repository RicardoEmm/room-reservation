package com.ricardoemm.room.reservation.service.domain.exception;

public final class InvalidReservationStateException extends DomainException {
    public InvalidReservationStateException(String message) {
        super(message);
    }
}
