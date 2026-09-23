package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.rest;

import com.ricardoemm.room.reservation.service.domain.model.Room;
import com.ricardoemm.room.reservation.service.domain.port.in.CheckRoomAvailabilityUseCase;
import com.ricardoemm.room.reservation.service.domain.port.in.CreateRoomUseCase;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.CheckRoomAvailabilityRequest;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.RoomRequest;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.RoomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final CheckRoomAvailabilityUseCase checkRoomAvailabilityUseCase;
    private final CreateRoomUseCase createRoomUseCase;

    @GetMapping("/{roomId}/availability")
    public ResponseEntity<Boolean> checkAvailability(
            @PathVariable("roomId") final UUID roomId,
            @ModelAttribute final CheckRoomAvailabilityRequest request
    ) {
        boolean isAvailable = checkRoomAvailabilityUseCase.checkAvailability(request.toQuery(roomId));
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody final RoomRequest request) {
        Room room = createRoomUseCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(RoomResponse.from(room));
    }
}
