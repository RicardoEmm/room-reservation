package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.rest.exceptionhandler;

import com.ricardoemm.room.reservation.service.domain.exception.*;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        HttpStatus status = switch (ex) {
            case InvalidReservationStateException e -> HttpStatus.CONFLICT;
            case InvalidReservationTimeException e -> HttpStatus.BAD_REQUEST;
            case ReservationNotFoundException e -> HttpStatus.NOT_FOUND;
            case RoomHasNotCapacityException e -> HttpStatus.CONFLICT;
            case RoomNotAvailableException e -> HttpStatus.CONFLICT;
            case RoomNotFoundException e -> HttpStatus.NOT_FOUND;
            case UnauthorizedCancellationException e -> HttpStatus.FORBIDDEN;
            case UserNotFoundException e -> HttpStatus.NOT_FOUND;
        };
        return ResponseEntity.status(status).body(new ErrorResponse(ex.getMessage()));
    }
}
