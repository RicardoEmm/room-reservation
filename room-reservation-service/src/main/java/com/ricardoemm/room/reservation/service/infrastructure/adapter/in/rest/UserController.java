package com.ricardoemm.room.reservation.service.infrastructure.adapter.in.rest;

import com.ricardoemm.room.reservation.service.domain.model.User;
import com.ricardoemm.room.reservation.service.domain.port.in.CreateUserUseCase;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.UserRequest;
import com.ricardoemm.room.reservation.service.infrastructure.adapter.in.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody final UserRequest request) {
        User user = createUserUseCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(user));
    }
}
