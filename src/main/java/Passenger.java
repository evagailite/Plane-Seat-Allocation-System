public class Passenger {

    private String name;
    private TicketType ticketType;
    private String seat;

    public Passenger(String name, TicketType ticketType, String seat) {
        this.name = name;
        this.ticketType = ticketType;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return name + "," + ticketType + "," + seat + "\n";
    }
}
