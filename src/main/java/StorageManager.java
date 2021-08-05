import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StorageManager {
    private static final String businessClassSeatsFileName = "businessClassSeats.txt";
    private static final String economyClassSeatsFileName = "economyClassSeats.txt";
    private static final String bookedSeatsFileName = "bookedSeats.txt";

    public StorageManager() {
        try {
            createBusinessClassSeatsFile();
            createEconomyClassSeatsFile();
            createBookedSeatsFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void createBusinessClassSeatsFile() throws IOException {
        File businessClassSeatsFile = new File(businessClassSeatsFileName);
        businessClassSeatsFile.createNewFile();
    }

    private void createEconomyClassSeatsFile() throws IOException {
        File economyClassSeatsFile = new File(economyClassSeatsFileName);
        economyClassSeatsFile.createNewFile();
    }

    private void createBookedSeatsFile() throws IOException {
        File bookedSeatsFile = new File(bookedSeatsFileName);
        bookedSeatsFile.createNewFile();
    }

    public void addToPassengerBookedSeats(Passenger passenger) throws IOException {
        //add true to not overwrite files
        //if true missing - overwrite content
        FileWriter bookedSeatsFile = new FileWriter(bookedSeatsFileName, true);
        //content goes in the file
        //%n - new line
        bookedSeatsFile.write(passenger.toString());
        //after writing close it
        bookedSeatsFile.close();
    }

    public void addToPassengerBookedSeats(String bookedSeatsArray) throws IOException {
        //if true missing - overwrite content
        FileWriter bookedSeatsFile = new FileWriter(bookedSeatsFileName);
        bookedSeatsFile.write(bookedSeatsArray);
        bookedSeatsFile.close();
    }

    public void addToBusinessClassSeats(BusinessClassSeats businessClassSeats) throws IOException {
        //add true to not overwrite files
        //if true missing - overwrite content
        FileWriter businessClassSeatsFile = new FileWriter(businessClassSeatsFileName, true);
        //content goes in the file
        //%n - new line
        businessClassSeatsFile.write(businessClassSeats.toString());
        //after writing close it
        businessClassSeatsFile.close();
    }

    public void addToBusinessClassSeats(String businessClassArray) throws IOException {
        //if true missing - overwrite content
        FileWriter businessClassSeatsFile = new FileWriter(businessClassSeatsFileName);
        businessClassSeatsFile.write(businessClassArray);
        businessClassSeatsFile.close();
    }

    public void addToEconomyClassSeats(EconomyClassSeats economyClassSeats) throws IOException {
        //add true to not overwrite files
        //if true missing - overwrite content
        FileWriter economyClassSeatsFile = new FileWriter(economyClassSeatsFileName, true);
        //content goes in the file
        //%n - new line
        economyClassSeatsFile.write(economyClassSeats.toString());
        //after writing close it
        economyClassSeatsFile.close();
    }

    public void addToEconomyClassSeats(String economyClassSeats) throws IOException {
        //if true missing - overwrite content
        FileWriter economyClassSeatsFile = new FileWriter(economyClassSeatsFileName);
        economyClassSeatsFile.write(economyClassSeats);
        economyClassSeatsFile.close();
    }


    public ArrayList<BusinessClassSeats> getBusinessClassSeats() throws FileNotFoundException {
        File businessClassSeatsFile = new File(businessClassSeatsFileName);
        //getting info from file
        Scanner reader = new Scanner(businessClassSeatsFile);
        ArrayList<BusinessClassSeats> businessClassSeats = new ArrayList<>();
        while (reader.hasNextLine()) {
            String businessClassString = reader.nextLine();
            String[] businessClassSeatsDetails = businessClassString.split(",");
            if (businessClassSeatsDetails.length < 3) break;
            businessClassSeats.add(new BusinessClassSeats(
                    Integer.parseInt(businessClassSeatsDetails[0]),
                    businessClassSeatsDetails[1],
                    businessClassSeatsDetails[2]));
        }
        return businessClassSeats;
    }

    public ArrayList<EconomyClassSeats> getEconomyClassSeats() throws FileNotFoundException {
        File economyClassSeatsFile = new File(economyClassSeatsFileName);
        //getting info from file
        Scanner reader = new Scanner(economyClassSeatsFile);
        ArrayList<EconomyClassSeats> economyClassSeats = new ArrayList<>();
        while (reader.hasNextLine()) {
            String economyClassString = reader.nextLine();
            String[] economyClassSeatsDetails = economyClassString.split(",");
            if (economyClassSeatsDetails.length < 5) break;
            economyClassSeats.add(new EconomyClassSeats(
                    Integer.parseInt(economyClassSeatsDetails[0]),
                    economyClassSeatsDetails[1],
                    economyClassSeatsDetails[2],
                    economyClassSeatsDetails[3],
                    economyClassSeatsDetails[4]
            ));
        }
        return economyClassSeats;
    }


    public ArrayList<Passenger> getPassengerBookedSeats() throws FileNotFoundException {
        File bookedSeatsFile = new File(bookedSeatsFileName);
        //getting info from file
        Scanner reader = new Scanner(bookedSeatsFile);
        ArrayList<Passenger> bookedSeats = new ArrayList<>();
        while (reader.hasNextLine()) {
            String bookedSeatsString = reader.nextLine();
            String[] bookedSeatsDetails = bookedSeatsString.split(",");
            if (bookedSeatsDetails.length < 3) break;
            bookedSeats.add(new Passenger(
                    bookedSeatsDetails[0],
                    TicketType.valueOf(bookedSeatsDetails[1]),
                    bookedSeatsDetails[2]));
        }
        return bookedSeats;
    }

    public void overwritePassenger(ArrayList<Passenger> bookedSeats) throws IOException {
        //  System.out.println(String.join("", contacts));
        //stream() - the way how to represent data which already exists
        this.addToPassengerBookedSeats(bookedSeats.stream()
                .map(Passenger::toString)
                .collect(Collectors.joining(""))
        );
    }

    public void overwriteBusinessClassSeats(ArrayList<BusinessClassSeats> businessClassSeats) throws IOException {
        //  System.out.println(String.join("", contacts));
        //stream() - the way how to represent data which already exists
        this.addToBusinessClassSeats(businessClassSeats.stream()
                .map(BusinessClassSeats::toString)
                .collect(Collectors.joining(""))
        );
    }

    public void overwriteEconomyClassSeats(ArrayList<EconomyClassSeats> economyClassSeats) throws IOException {
        //  System.out.println(String.join("", contacts));
        //stream() - the way how to represent data which already exists
        this.addToEconomyClassSeats(economyClassSeats.stream()
                .map(EconomyClassSeats::toString)
                .collect(Collectors.joining(""))
        );
    }

}
