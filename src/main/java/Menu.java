import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Plain airplane = new Plain();

    public void starts() {
        startsMenu();

//        if (airplane.businessClassSeats.isEmpty() && airplane.economyClassSeats.isEmpty()) {
//            airplane.setSeats();
//        }

        airplane.syncBusinessClassSeats();
        airplane.syncEconomyClassSeats();
        airplane.syncBookedSeats();

        int userChoice = Integer.parseInt(scanner.nextLine());
        showMenu(userChoice);
    }

    private void startsMenu() {
        System.out.println("\nPlease choose an action: ");
        System.out.println("\t 1 - Book Seats");
        System.out.println("\t 2 - Book Seats Automatically");
        System.out.println("\t 3 - View Available Seats");
        System.out.println("\t 4 - Exit");
    }

    public void welcomeMessage() {
        System.out.println("Welcome to the Flight Ticket Booking");
    }

    private void showMenu(int userChoice) {
        switch (userChoice) {
            case 1:
                airplane.bookSeats();
                break;
            case 2:
                airplane.setSeatAutomatically();
                break;
            case 3:
                airplane.viewAvailableSeats();
                break;
            case 4:
                System.out.println("Thank you for visiting Flight Ticket Booking!");
                System.exit(0);
                break;
            default:
                starts();
                break;
        }
        starts();
    }
}
