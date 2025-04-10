public class Ticket {
    private final int ticketNumber;

    private final int ticketPrice;

    private final PassengerInfo passengerInfo;



    public Ticket(int ticketNumber, int ticketPrice, PassengerInfo passengerInfo) {
        this.ticketNumber = ticketNumber;
        this.ticketPrice = ticketPrice;
        this.passengerInfo = passengerInfo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber=" + ticketNumber + ", ticketPrice=" + ticketPrice + ", passengerInfo=" + passengerInfo +'}';
    }
}
