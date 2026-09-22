package com.ricardoemm.room.reservation.service.domain.exception;

public final class RoomNotAvailableException extends DomainException {
    public RoomNotAvailableException(String message) {
        super(message);
    }
}
