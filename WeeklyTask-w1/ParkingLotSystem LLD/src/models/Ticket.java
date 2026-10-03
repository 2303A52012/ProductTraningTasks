package models;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private Vehicle vehicle;
    private ParkingFloor floor;
    private ParkingSlot slot;
    private LocalDateTime entryTime;
    
    public Ticket(int ticketId,Vehicle vehicle,ParkingFloor floor,ParkingSlot slot) 
    {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = LocalDateTime.now();
    }

    public int getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingFloor getFloor() {
        return floor;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
