import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Plain {
    StorageManager storageManager = new StorageManager();
    Scanner scanner = new Scanner(System.in);
    ArrayList<BusinessClassSeats> businessClassSeats = new ArrayList<>();
    ArrayList<EconomyClassSeats> economyClassSeats = new ArrayList<>();
    ArrayList<Passenger> bookedSeats = new ArrayList<>();

    Plain() {
    }

    public void setSeats() {
        placeBusinessClassSeats();
        placeEconomyClassSeats();
    }


    public void placeBusinessClassSeats() {
        for (int i = 1; i < 5; i++) {
            businessClassSeats.add(new BusinessClassSeats(i));
        }
        collectBusinessClassSeats();
    }

    public void collectBusinessClassSeats() {
        for (BusinessClassSeats seats : businessClassSeats) {
            try {
                storageManager.addToBusinessClassSeats(new BusinessClassSeats(seats.getSeatRowNumber(), seats.getSeatA(),
                        seats.getSeatC()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void placeEconomyClassSeats() {
        for (int i = 5; i < 21; i++) {
            economyClassSeats.add(new EconomyClassSeats(i));
        }
        collectEconomyClassSeats();
    }

    public void collectEconomyClassSeats() {
        for (EconomyClassSeats seats : economyClassSeats) {
            try {
                storageManager.addToEconomyClassSeats(new EconomyClassSeats(seats.getSeatRowNumber(), seats.getSeatA(),
                        seats.getSeatB(), seats.getSeatC(), seats.getSeatD()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewAvailableSeats() {
        displayAvailableBusinessClassSeats();
        displayAvailableEconomicClassSeats();
    }

    public void displayAvailableBusinessClassSeats() {
        System.out.println("Available seats in the Business Class:");
        for (BusinessClassSeats seats : businessClassSeats) {
            System.out.println(String.format("%-5s%-5s%-5s", seats.getSeatRowNumber(), seats.getSeatA(),
                    seats.getSeatC()));
        }
    }

    private void displayAvailableEconomicClassSeats() {
        System.out.println("Available seats in the Economic Class:");
        for (EconomyClassSeats seats : economyClassSeats) {
            System.out.println(String.format("%-5s%-5s%-5s%-5s%-5s", seats.getSeatRowNumber(), seats.getSeatA(),
                    seats.getSeatB(), seats.getSeatC(), seats.getSeatD()));
        }
    }

    public void bookSeats() {
        viewAvailableSeats();
        try {
            System.out.print("Please enter your name: ");
            String passengerName = scanner.nextLine();
            System.out.print("Please enter your ticket type - ECONOMY, BUSINESS: ");
            String passengerTicketType = scanner.nextLine().toUpperCase();
            System.out.print("Please enter seat number: ");
            int seatNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Please enter seat letter: ");
            String seatLetter = scanner.nextLine();

            reserveSeatByTicketType(passengerName, TicketType.valueOf(passengerTicketType),
                    seatNumber, seatLetter);
        } catch (Exception e) {
            System.out.println("Something went wrong! Please try again!");
        }
    }

    private void reserveSeatByTicketType(String passengerName, TicketType passengerTicketType, int seatNumber, String seatLetter) {
        String passengerSeat;
        if (passengerTicketType.equals(TicketType.BUSINESS)) {
            for (BusinessClassSeats seats : businessClassSeats) {
                if (seatNumber == seats.getSeatRowNumber()) {
                    if (seatLetter.equals(seats.getSeatA())) {
                        seats.setSeatA("X");
                        passengerSeat = Integer.toString(seatNumber).concat(seatLetter);
                        bookedSeats.add(new Passenger(passengerName, passengerTicketType, passengerSeat));
                        reservedMessage(seatNumber, seatLetter);
                    } else if (seatLetter.equals(seats.getSeatC())) {
                        seats.setSeatC("X");
                        passengerSeat = Integer.toString(seatNumber).concat(seatLetter);
                        bookedSeats.add(new Passenger(passengerName, passengerTicketType, passengerSeat));
                        reservedMessage(seatNumber, seatLetter);
                    } else {
                        System.out.println("Seat is already taken!");
                    }
                }
            }
        } else if (passengerTicketType.equals(TicketType.ECONOMY)) {
            for (EconomyClassSeats seats : economyClassSeats) {
                if (seatNumber == seats.getSeatRowNumber()) {
                    if (seatLetter.equals(seats.getSeatA())) {
                        seats.setSeatA("X");
                        passengerSeat = Integer.toString(seatNumber).concat(seatLetter);
                        bookedSeats.add(new Passenger(passengerName, passengerTicketType, passengerSeat));
                        reservedMessage(seatNumber, seatLetter);
                    } else if (seatLetter.equals(seats.getSeatB())) {
                        seats.setSeatB("X");
                        passengerSeat = Integer.toString(seatNumber).concat(seatLetter);
                        bookedSeats.add(new Passenger(passengerName, passengerTicketType, passengerSeat));
                        reservedMessage(seatNumber, seatLetter);
                    } else if (seatLetter.equals(seats.getSeatC())) {
                        seats.setSeatC("X");
                        passengerSeat = Integer.toString(seatNumber).concat(seatLetter);
                        bookedSeats.add(new Passenger(passengerName, passengerTicketType, passengerSeat));
                        reservedMessage(seatNumber, seatLetter);
                    } else if (seatLetter.equals(seats.getSeatD())) {
                        seats.setSeatD("X");
                        passengerSeat = Integer.toString(seatNumber).concat(seatLetter);
                        bookedSeats.add(new Passenger(passengerName, passengerTicketType, passengerSeat));
                        reservedMessage(seatNumber, seatLetter);
                    } else {
                        System.out.println("Seat is already taken!");
                    }
                }
            }
        } else {
            System.out.println("Ticket Type invalid! Please try again!");
        }
        try {
            storageManager.overwriteBusinessClassSeats(businessClassSeats);
            storageManager.overwriteEconomyClassSeats(economyClassSeats);
            storageManager.overwritePassenger(bookedSeats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reservedMessage(int seatNUmber, String seatLetter) {
        System.out.println("Seat " +
                seatNUmber + seatLetter + " successfully reserved");
    }


    public void syncBookedSeats() {
        try {
            this.bookedSeats = storageManager.getPassengerBookedSeats();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void syncBusinessClassSeats() {
        try {
            this.businessClassSeats = storageManager.getBusinessClassSeats();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void syncEconomyClassSeats() {
        try {
            this.economyClassSeats = storageManager.getEconomyClassSeats();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setSeatAutomatically() {
        try {
            System.out.print("Please enter your name: ");
            String passengerName = scanner.nextLine();
            System.out.print("Please enter your ticket type - ECONOMY, BUSINESS: ");
            String passengerTicketType = scanner.nextLine().toUpperCase();

            if (passengerTicketType.equals("BUSINESS")) {
                for (BusinessClassSeats seats : businessClassSeats) {
                    if (seats.getSeatA().equals("X") && seats.getSeatC().equals("X")) {
                    } else if (!seats.getSeatA().equals("X")) {
                        seats.setSeatA("X");
                        String passengerSeat = Integer.toString(seats.getSeatRowNumber()).concat("A");
                        bookedSeats.add(new Passenger(passengerName, TicketType.valueOf(passengerTicketType), passengerSeat));
                        reservedMessage(seats.getSeatRowNumber(), "A");
                        break;
                    } else if (!seats.getSeatC().equals("X")) {
                        seats.setSeatC("X");
                        String passengerSeat = Integer.toString(seats.getSeatRowNumber()).concat("C");
                        bookedSeats.add(new Passenger(passengerName, TicketType.valueOf(passengerTicketType), passengerSeat));
                        reservedMessage(seats.getSeatRowNumber(), "C");
                        break;
                    } else {
                        System.out.println("All Business class seats are full!");
                    }
                }
            } else if (passengerTicketType.equals("ECONOMY")) {
                for (EconomyClassSeats seats : economyClassSeats) {
                    if (seats.getSeatA().equals("X") && seats.getSeatB().equals("X") && seats.getSeatC().equals("X") && seats.getSeatD().equals("X")) {
                        System.out.println("All Business class seats are full!");
                    } else if (!seats.getSeatA().equals("X")) {
                        seats.setSeatA("X");
                        String passengerSeat = Integer.toString(seats.getSeatRowNumber()).concat("A");
                        bookedSeats.add(new Passenger(passengerName, TicketType.valueOf(passengerTicketType), passengerSeat));
                        reservedMessage(seats.getSeatRowNumber(), "A");
                        break;
                    } else if (!seats.getSeatB().equals("X")) {
                        seats.setSeatB("X");
                        String passengerSeat = Integer.toString(seats.getSeatRowNumber()).concat("B");
                        bookedSeats.add(new Passenger(passengerName, TicketType.valueOf(passengerTicketType), passengerSeat));
                        reservedMessage(seats.getSeatRowNumber(), "B");
                        break;
                    } else if (!seats.getSeatC().equals("X")) {
                        seats.setSeatC("X");
                        String passengerSeat = Integer.toString(seats.getSeatRowNumber()).concat("C");
                        bookedSeats.add(new Passenger(passengerName, TicketType.valueOf(passengerTicketType), passengerSeat));
                        reservedMessage(seats.getSeatRowNumber(), "C");
                        break;
                    } else if (!seats.getSeatD().equals("X")) {
                        seats.setSeatD("X");
                        String passengerSeat = Integer.toString(seats.getSeatRowNumber()).concat("D");
                        bookedSeats.add(new Passenger(passengerName, TicketType.valueOf(passengerTicketType), passengerSeat));
                        reservedMessage(seats.getSeatRowNumber(), "D");
                        break;
                    } else {
                        System.out.println("All Economy class seats are full!");
                    }
                }
            } else {
                System.out.println("Invalid Ticket type! PLease try again!");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong! Please try again!");
        }

        try {
            storageManager.overwriteBusinessClassSeats(businessClassSeats);
            storageManager.overwriteEconomyClassSeats(economyClassSeats);
            storageManager.overwritePassenger(bookedSeats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}