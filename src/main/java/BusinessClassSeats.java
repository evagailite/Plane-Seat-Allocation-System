public class BusinessClassSeats {
    private int seatRowNumber;
    private String seatA;
    private String seatC;

    public BusinessClassSeats(int seatRowNumber) {
        this.seatRowNumber = seatRowNumber;
        this.seatA = "A";
        this.seatC = "C";
    }

    public BusinessClassSeats(int seatRowNumber, String seatA, String seatC) {
        this.seatRowNumber = seatRowNumber;
        this.seatA = seatA;
        this.seatC = seatC;
    }

    public int getSeatRowNumber() {
        return seatRowNumber;
    }

    public void setSeatRowNumber(int seatRowNumber) {
        this.seatRowNumber = seatRowNumber;
    }

    public String getSeatA() {
        return seatA;
    }

    public void setSeatA(String seatA) {
        this.seatA = seatA;
    }

    public String getSeatC() {
        return seatC;
    }

    public void setSeatC(String seatC) {
        this.seatC = seatC;
    }

    @Override
    public String toString() {
        return seatRowNumber + "," + seatA + "," + seatC + "\n";
    }
}
