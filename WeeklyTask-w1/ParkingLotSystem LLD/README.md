# Parking Lot System

A small Java command-line parking lot application demonstrating object-oriented design and a simple service-oriented domain model.

## Features

- Park bikes, cars, and trucks.
- Allocate a compatible available slot.
- Generate a ticket for each parked vehicle.
- Display the status of every floor and slot.
- Exit a vehicle using its ticket ID.
- Calculate the parking fee from vehicle type and parking duration.

## Project Structure

```text
src/
  Main.java                         CLI menu and user interaction
  models/
    Vehicle.java                    Base vehicle type
    Bike.java, Car.java, Truck.java Vehicle specializations
    ParkingFloor.java               Floor and slot collection
    ParkingSlot.java                Slot state and parked vehicle
    Ticket.java                     Parking transaction details
  services/
    ParkingLotServices.java         Parking, status, exit, and fee logic
bin/                                Compiled classes
lib/                                Optional external dependencies
```

## Design Decisions

### 1. Separate models from service logic

The classes in `models` represent domain data and state. `ParkingLotServices` owns operations that change parking state, such as parking and exiting a vehicle. This keeps the command-line UI focused on reading input and displaying results.

### 2. Vehicle inheritance

`Bike`, `Car`, and `Truck` extend `Vehicle`. The common vehicle number and type are stored once in the base class, while the subclasses make supported vehicle types explicit at the call site.

### 3. Fixed slot layout

Each floor contains six slots: two for bikes, two for cars, and two for trucks. This is intentionally simple for the LLD example and makes slot compatibility easy to inspect. A future version could load floors and slot capacities from configuration.

### 4. First-fit allocation

When a vehicle arrives, the service checks floors in order and selects the first free slot matching the vehicle type. This provides deterministic behavior and avoids introducing a more complex allocation strategy before it is needed.

### 5. Tickets identify active parking sessions

Each successful parking operation creates a ticket containing the ticket ID, vehicle, floor, slot, and entry time. The CLI stores active tickets in a `Map<Integer, Ticket>` so an exit operation can find the associated slot directly.

### 6. Slot state belongs to the slot

`ParkingSlot` stores whether it is occupied and which vehicle is parked there. Calling `removeVehicle()` clears both pieces of state together, reducing the chance of a slot being marked available while still referencing a vehicle.

### 7. Fee calculation is service-owned

The service calculates fees using the vehicle type and elapsed duration. Current rates are Rs.20 per second for bikes, Rs.40 for cars, and Rs.60 for trucks. The use of seconds keeps the demo quick to exercise; a production system would normally use a documented hourly or interval-based pricing policy.

## Running the Application

From the project root, compile the source files:

```powershell
javac -d bin src\Main.java src\models\*.java src\services\*.java
```

Start the CLI:

```powershell
java -cp bin Main
```

The menu provides options to park a vehicle, exit a vehicle, display parking status, or quit.

## Testing

The application can be smoke-tested by piping menu input. For example, this parks a bike, displays the lot, and exits:

```powershell
"1`n1`nTN-01-AA-1234`n3`n0" | java -cp bin Main
```

## Current Tradeoffs and Limitations

- Data is held in memory and is lost when the application exits.
- Vehicle numbers are not currently checked for duplicates.
- The CLI expects numeric input for numeric choices.
- Parking fees are printed rather than returned as a value or persisted.
- There is no authentication, reservation support, payment integration, or concurrent access handling.

These limitations keep the example focused on the core parking lot object model and state transitions.
