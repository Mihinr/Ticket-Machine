import java.util.Random;

public class Passenger implements Runnable{
    private final TicketMachine ticketMachine;
    private final Ticket ticket;

    public Passenger(TicketMachine ticketMachine, Ticket ticket) {

        this.ticketMachine = ticketMachine;
        this.ticket = ticket;
    }
    @Override
    public void run() {

        Random random = new Random();

        try{

            ticketMachine.printTicket(ticket);

            Thread.sleep(random.nextInt(2000) + 1000);



        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
