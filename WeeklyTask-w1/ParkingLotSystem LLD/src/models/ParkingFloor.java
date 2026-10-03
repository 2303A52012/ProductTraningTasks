package models;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSlot> slots;
    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();

        // 2 BIKE slots
        slots.add(new ParkingSlot(1, "BIKE"));
        slots.add(new ParkingSlot(2,"BIKE"));

        // 2 CAR slots
        slots.add(new ParkingSlot(3, "CAR"));
        slots.add(new ParkingSlot(4, "CAR"));

        // 2 TRUCK slots
        slots.add(new ParkingSlot(5, "TRUCK"));
        slots.add(new ParkingSlot(6, "TRUCK"));
    }

    public int getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    public List<ParkingSlot> getSlots() {
        return slots;
    }
    public void setSlots(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public ParkingSlot getAvailableSlot(Vehicle v)
    {
        for(ParkingSlot s : slots)
        {
            if(s.getSupportedType().equals(v.getVehicleType()) && !s.isOccupied())
                return s;
        }
        return null;
    }

    

}
