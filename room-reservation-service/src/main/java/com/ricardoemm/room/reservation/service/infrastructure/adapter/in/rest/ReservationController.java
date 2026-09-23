package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.rest;

import com.ricardoemm.room.reservation.service.domain.model.Reservation;
import com.ricardoemm.room.reservation.service.domain.port.in.CancelReservationUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.CreateReservationUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.ListReservationsByUserUseCase;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.CancelReservationRequest;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.RequesterReservationsGroupResponse;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.ReservationRequest;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.ReservationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final ListReservationsByUserUseCase listReservationsByUserUseCase;

    @GetMapping("/requester/{requesterId}")
    public ResponseEntity<List<RequesterReservationsGroupResponse>> getReservationByRequesterId(
            @PathVariable("requesterId") final UUID requesterId
    ) {
        List<Reservation> reservation = listReservationsByUserUseCase.listByUserId(requesterId);
        return ResponseEntity.ok(RequesterReservationsGroupResponse.fromList(reservation));
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@Valid @RequestBody final ReservationRequest request) {
        Reservation reservation = createReservationUseCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservationResponse.from(reservation));
    }

    @PutMapping
    public ResponseEntity<String> cancel(@Valid @RequestBody final CancelReservationRequest request) {
        cancelReservationUseCase.cancel(request.toCommand());
        return ResponseEntity.status(HttpStatus.OK).body("reservation was cancelled successfully");
    }
}
