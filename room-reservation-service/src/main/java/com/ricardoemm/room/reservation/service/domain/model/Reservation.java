package com.ricardoemm.room.reservation.service.domain.model;

import com.ricardoemm.room.reservation.service.domain.exception.InvalidReservationStateException;
import com.ricardoemm.room.reservation.service.domain.exception.UnauthorizedCancellationException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private Room room;
    private User requester;
    private ReservationStatus status;
    private TimeSlot timeSlot;
    private LocalDateTime createdAt;
    private LocalDateTime cancelledAt;
    private User cancelledBy;

    private Reservation(
            UUID id,
            Room room,
            User requester,
            TimeSlot timeSlot,
            ReservationStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.room = room;
        this.requester = requester;
        this.timeSlot = timeSlot;
        this.status = status;
        this.createdAt = createdAt;
    }

    private Reservation(
            UUID id,
            Room room,
            User requester,
            ReservationStatus status,
            TimeSlot timeSlot,
            LocalDateTime createdAt,
            LocalDateTime cancelledAt,
            User cancelledBy
    ) {
        this.id = id;
        this.room = room;
        this.requester = requester;
        this.status = status;
        this.timeSlot = timeSlot;
        this.createdAt = createdAt;
        this.cancelledAt = cancelledAt;
        this.cancelledBy = cancelledBy;
    }

    public static Reservation requestedBy(User requester, Room room, TimeSlot timeSlot, Clock clock) {
        if (!room.isActive())
            throw new InvalidReservationStateException("room is not available");
        return new Reservation(
                UUID.randomUUID(),
                room,
                requester,
                timeSlot,
                ReservationStatus.CONFIRMED,
                LocalDateTime.now(clock)
        );
    }

    public static Reservation reconstitute(
            UUID id,
            Room room,
            User requester,
            ReservationStatus status,
            TimeSlot timeSlot,
            LocalDateTime createdAt,
            LocalDateTime cancelledAt,
            User cancelledBy
    ) {
        return new Reservation(
                id,
                room,
                requester,
                status,
                timeSlot,
                createdAt,
                cancelledAt,
                cancelledBy
        );
    }

    public void cancel(User actor, Clock clock) {
        if (this.status == ReservationStatus.CANCELLED)
            throw new InvalidReservationStateException("reservation is already cancelled");

        boolean isOwner = actor.getId().equals(this.requester.getId());
        boolean isAdmin = actor.getRole() == UserRole.ADMIN;

        if (!isOwner && !isAdmin)
            throw new UnauthorizedCancellationException("cannot cancel other people's reservations");

        if (isAdmin) {
            applyCancellation(actor, clock);
            return;
        }

        if (isWithinCancellationPeriodGrace(clock))
            throw new UnauthorizedCancellationException("outside the cancellation window");

        applyCancellation(actor, clock);
    }

    public boolean overlapsWith(TimeSlot other) {
        return this.status == ReservationStatus.CONFIRMED
                && this.timeSlot.startAt().isBefore(other.endAt())
                && other.startAt().isBefore(this.timeSlot.endAt());
    }

    public boolean isConfirmed() {
        return this.status == ReservationStatus.CONFIRMED;
    }

    private boolean isWithinCancellationPeriodGrace(Clock clock) {
        LocalDateTime now = LocalDateTime.now(clock);
        return now.isAfter(timeSlot.startAt().minusHours(1));
    }

    private void applyCancellation(User actor, Clock clock) {
        this.status = ReservationStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now(clock);
        this.cancelledBy = actor;
    }

    public UUID getId() { return id; }

    public Room getRoom() { return room; }

    public User getRequester() { return requester; }

    public ReservationStatus getStatus() { return status; }

    public TimeSlot getTimeSlot() { return timeSlot; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getCancelledAt() { return cancelledAt; }

    public User getCancelledBy() { return cancelledBy; }
}
