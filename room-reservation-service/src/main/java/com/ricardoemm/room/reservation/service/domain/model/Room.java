package com.ricardoemm.room.reservation.service.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Room {
    private final UUID id;
    private String name;
    private String location;
    private int capacity;
    private boolean active;

    private Room(UUID id, String name, String location, int capacity, boolean active) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.active = active;
    }

    public static Room createRoom(String name, String location, int capacity) {
        return new Room(null, name, location, capacity, true);
    }

    public boolean canHost(int attendees) {
        return active && attendees <= capacity;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return capacity == room.capacity
                && active == room.active
                && Objects.equals(id, room.id)
                && Objects.equals(name, room.name)
                && Objects.equals(location, room.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, capacity, active);
    }
}
