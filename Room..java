import java.util.*;

public class HotelManagement {

    // Define a room record
    record Room(int number, String type, boolean isBooked, String guestName) {}

    static Map<Integer, Room> rooms = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize rooms
        for (int i = 1; i <= 5; i++) {
            rooms.put(i, new Room(i, "Standard", false, ""));
        }

        while (true) {
            System.out.println("\n====== Hotel Management System ======");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Checkout Room");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom(scanner);
                case 3 -> checkoutRoom(scanner);
                case 4 -> {
                    System.out.println("Thank you! Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    static void viewRooms() {
        System.out.println("\nRoom Status:");
        for (Room room : rooms.values()) {
            String status = room.isBooked() ? "Booked by " + room.guestName() : "Available";
            System.out.printf("Room %d [%s] - %s%n", room.number(), room.type(), status);
        }
    }

    static void bookRoom(Scanner scanner) {
        System.out.print("Enter room number to book: ");
        int roomNum = scanner.nextInt();
        scanner.nextLine();

        Room room = rooms.get(roomNum);
        if (room == null) {
            System.out.println("Room not found!");
        } else if (room.isBooked()) {
            System.out.println("Room already booked!");
        } else {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();
            rooms.put(roomNum, new Room(roomNum, room.type(), true, guestName));
            System.out.println("Room booked successfully for " + guestName);
        }
    }

    static void checkoutRoom(Scanner scanner) {
        System.out.print("Enter room number to checkout: ");
        int roomNum = scanner.nextInt();

        Room room = rooms.get(roomNum);
        if (room == null) {
            System.out.println("Room not found!");
        } else if (!room.isBooked()) {
            System.out.println("Room is not currently booked.");
        } else {
            rooms.put(roomNum, new Room(roomNum, room.type(), false, ""));
            System.out.println("Room " + roomNum + " has been checked out.");
        }
    }
}