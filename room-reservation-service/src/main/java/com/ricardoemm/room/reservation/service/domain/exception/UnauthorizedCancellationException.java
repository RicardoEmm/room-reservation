package com.ricardoemm.room.reservation.service.domain.exception;

public final class UnauthorizedCancellationException extends DomainException {
    public UnauthorizedCancellationException(String message) {
        super(message);
    }
}
