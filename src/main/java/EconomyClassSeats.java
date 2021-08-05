public class EconomyClassSeats {

    private int seatRowNumber;
    private String seatA;
    private String seatB;
    private String seatC;
    private String seatD;

    public EconomyClassSeats(int seatRowNumber) {
        this.seatRowNumber = seatRowNumber;
        this.seatA = "A";
        this.seatB = "B";
        this.seatC = "C";
        this.seatD = "D";
    }

    public EconomyClassSeats(int seatRowNumber, String seatA, String seatB,
                             String seatC, String seatD) {
        this.seatRowNumber = seatRowNumber;
        this.seatA = seatA;
        this.seatB = seatB;
        this.seatC = seatC;
        this.seatD = seatD;
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

    public String getSeatB() {
        return seatB;
    }

    public void setSeatB(String seatB) {
        this.seatB = seatB;
    }

    public String getSeatC() {
        return seatC;
    }

    public void setSeatC(String seatC) {
        this.seatC = seatC;
    }

    public String getSeatD() {
        return seatD;
    }

    public void setSeatD(String seatD) {
        this.seatD = seatD;
    }

    @Override
    public String toString() {
        return seatRowNumber + "," + seatA + "," + seatB + "," + seatC + "," + seatD + "\n";
    }
}
