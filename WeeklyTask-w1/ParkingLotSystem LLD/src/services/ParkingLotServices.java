package services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.ParkingFloor;
import models.ParkingSlot;
import models.Ticket;
import models.Vehicle;

public class ParkingLotServices 
{
    private List<ParkingFloor> floors;
    private int ticketCounter = 1;
    public ParkingLotServices() {

        floors = new ArrayList<>();
        floors.add(new ParkingFloor(1));
        floors.add(new ParkingFloor(2));
        floors.add(new ParkingFloor(3));
    }
    public Ticket parkVehicle(Vehicle vehicle)
    {
        for(ParkingFloor f : floors)
        {
            ParkingSlot slot = f.getAvailableSlot(vehicle);
            if(slot!=null)
            {
                slot.setOccupied(true);
                slot.setV(vehicle);
                Ticket t = new Ticket(ticketCounter++, vehicle, f,slot);
                System.out.println(vehicle.getNumber()+" is parked in Floor "+f.getFloorNumber()+" and slot is "+slot.getSlotNumber());
                return t;
            }
        }
        return null;
    }

    public void displayStatus()
    {
        System.out.println("****************************************************");
        for(ParkingFloor f : floors)
        {
            System.out.println("Floor "+f.getFloorNumber()+" details:");
            System.out.println("****************************************************");
            for(ParkingSlot s : f.getSlots())
            {
                System.out.println("Slot "+s.getSlotNumber()+" : "+(s.isOccupied()?s.getV().getNumber()+" is parked":"Available"));
            }
            System.out.println("****************************************************");
        }
    }

    public void exitVehicle(Ticket t)
    {
        LocalDateTime exitTime = LocalDateTime.now();
        long hours = Duration.between(t.getEntryTime(),exitTime).toSeconds();
        double fee = calculateFee(t.getVehicle().getVehicleType(),hours);
        t.getSlot().removeVehicle();
        System.out.println(t.getVehicle().getNumber()+ " exited from Floor "+ t.getFloor().getFloorNumber()+ ", Slot "+ t.getSlot().getSlotNumber());
        System.out.println("Parking Fee: Rs." + fee);
    }

    private double calculateFee(String type,long hours) 
    {
        switch (type) 
        {
            case "BIKE":
                return hours * 20;
            case "CAR":
                return hours * 40;
            case "TRUCK":
                return hours * 60;
            default:
                return 0;
        }
    }
}
