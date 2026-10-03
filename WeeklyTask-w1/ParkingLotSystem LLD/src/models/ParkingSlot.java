package models;

public class ParkingSlot 
{
    private int slotNumber;
    private String supportedType;
    private boolean occupied;
    private Vehicle parkedVehicle;
    public ParkingSlot(int slotNumber, String supportedType) {
        this.slotNumber = slotNumber;
        this.supportedType = supportedType;
        this.occupied = false;
    }
    public int getSlotNumber() {
        return slotNumber;
    }
    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    public String getSupportedType() {
        return supportedType;
    }
    public void setSupportedType(String vehicleType) {
        this.supportedType = vehicleType;
    }
    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    public Vehicle getV() {
        return parkedVehicle;
    }
    public void setV(Vehicle v) {
        this.parkedVehicle = v;
    }  

     public void removeVehicle() {
        this.parkedVehicle = null;
        this.occupied = false;
    }
    
}
