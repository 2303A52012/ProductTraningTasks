import java.util.*;
import models.*;
import services.ParkingLotServices;

public class Main {
    public static void main(String[] args) {
        ParkingLotServices parkingLot = new ParkingLotServices();
        Map < Integer, Ticket > activeTickets = new HashMap < > ();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("**************Welcome to Parking Lot Application**************");
        do {
            printMenu();
            int choice = readInteger(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.println("\nVehicle type: 1. Bike  2. Car  3. Truck");
                    int typeChoice = readInteger(scanner, "Choose vehicle type: ");
                    String number = readText(scanner, "Enter vehicle number: ");
                    Vehicle vehicle = null;

                    switch (typeChoice) {
                        case 1:
                            vehicle = new Bike(number, "BIKE");
                            break;
                        case 2:
                            vehicle = new Car(number, "CAR");
                            break;
                        case 3:
                            vehicle = new Truck(number, "TRUCK");
                            break;
                        default:
                            System.out.println("Invalid vehicle type.");
                            break;
                    }

                    if (vehicle == null) {
                        break;
                    }

                    Ticket ticket = parkingLot.parkVehicle(vehicle);
                    if (ticket == null) {
                        System.out.println("No suitable slot is available.");
                        break;
                    }

                    activeTickets.put(ticket.getTicketId(), ticket);
                    System.out.println("Ticket ID: " + ticket.getTicketId());
                    break;
                case 2:
                    int ticketId = readInteger(scanner, "Enter ticket ID: ");
                    Ticket exitTicket = activeTickets.get(ticketId);

                    if (exitTicket == null) {
                        System.out.println("Active ticket not found.");
                        break;
                    }

                    parkingLot.exitVehicle(exitTicket);
                    activeTickets.remove(ticketId);
                    break;
                case 3:
                    parkingLot.displayStatus();
                    break;
                case 0:
                    running = false;
                    System.out.println("**************Thank you for using our service**************");
                    break;
                default:
                    System.out.println("**************Invalid Choice**************");
            }
        } while (running);
    }

    private static void printMenu() {
        System.out.println("\n===== Parking Lot =====");
        System.out.println("1. Park vehicle");
        System.out.println("2. Exit vehicle");
        System.out.println("3. Display parking status");
        System.out.println("0. Exit application");
    }



    private static int readInteger(Scanner scanner, String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static String readText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This value cannot be empty.");
        }
    }
}