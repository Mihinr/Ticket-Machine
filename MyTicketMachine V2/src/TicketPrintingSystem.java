import java.io.*;

public class TicketPrintingSystem {



    public static void main(String[] args) {

        File f=new File("output.txt");
        System.out.println("file exist = "+f.exists());
        FileOutputStream io= null;
        try {
            io = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.setOut(new PrintStream (io));




        PassengerInfo[] passengerInfos = {
                new PassengerInfo("Tim", "0778549622", "Tim@gmail.com"),
                new PassengerInfo("Martin", "1254789630", "Martin@gmail.com"),
                new PassengerInfo("Kane", "5478963201", "Kane@gmail.com"),
                new PassengerInfo("Patt", "1254789630", "Patt@gmail.com"),
                new PassengerInfo("Starc", "1201457896", "Starc@gmail.com"),
                new PassengerInfo("Marsh", "3698745210", "Marsh@gmail.com"),
                new PassengerInfo("Travis", "0125478963", "Travis@gmail.com"),
                new PassengerInfo("Adam", "2547896301", "Adam@gmail.com"),
                new PassengerInfo("Warner", "7852140236", "Warner@gmail.com"),
                new PassengerInfo("Marcus", "1203210145", "Marcus@gmail.com"),
                new PassengerInfo("Maxwell", "0011225544", "Maxwell@gmail.com"),
                new PassengerInfo("Smith", "0012547896", "Smith@gmail.com")

        };

        Ticket[] tickets = {
                new Ticket(1, 1000, passengerInfos[0]),
                new Ticket(2, 2000, passengerInfos[1]),
                new Ticket(3, 3000, passengerInfos[2]),
                new Ticket(4, 4000, passengerInfos[3]),
                new Ticket(5, 5000, passengerInfos[4]),
                new Ticket(6, 6000, passengerInfos[5]),
                new Ticket(7, 7000, passengerInfos[6]),
                new Ticket(8, 8000, passengerInfos[7]),
                new Ticket(9, 9000, passengerInfos[8]),
                new Ticket(10, 1010, passengerInfos[9]),
                new Ticket(11, 4040, passengerInfos[10]),
                new Ticket(12, 8070, passengerInfos[11])

        };


        int numberOfPassengers = passengerInfos.length; // Set the number of passengers

        //creating the shared object, which is the ticket machine
        TicketMachine ticketMachine = new TicketMachine(0, 0, numberOfPassengers);

        System.out.println("-------------------------Printer status-------------------------");
        System.out.println("Paper level: " + ticketMachine.getPaperLevel());
        System.out.println("Toner level: " + ticketMachine.getTonerLevel());
        System.out.println();
        System.out.println("--------------------------------------------------------------------");


        ThreadGroup passengers = new ThreadGroup("Passengers");
        ThreadGroup technicians = new ThreadGroup("Technicians");


        // creating the passengers with the tickets and the passenger names
        Passenger[] passengersArray = new Passenger[numberOfPassengers];
        for (int i = 0; i < numberOfPassengers; i++) {
            passengersArray[i] = new Passenger(ticketMachine, tickets[i]);
        }


        //creating the passenger threads
        Thread[] passengerThreads = new Thread[numberOfPassengers];
        for (int i = 0; i < numberOfPassengers; i++) {
            passengerThreads[i] = new Thread(passengers, passengersArray[i], "PassengerThread" + (i + 1));
        }

        // creating the two techs
        TicketTonerTechnician ticketTonerTechnician = new TicketTonerTechnician("Toner Technician", ticketMachine);
        TicketPaperTechnician ticketPaperTechnician = new TicketPaperTechnician( "Paper Technician", ticketMachine);

        // creating the techs threads
        Thread tonerTechnicianThread = new Thread(technicians, ticketTonerTechnician, ticketTonerTechnician.getName());
        Thread paperTechnicianThread = new Thread(technicians, ticketPaperTechnician, ticketPaperTechnician.getName());

        // starting the threads
        for (Thread passengerThread : passengerThreads) {
            passengerThread.start();
        }

        tonerTechnicianThread.start();
        paperTechnicianThread.start();



        // Waiting until threads complete their task fully
        for (Thread thread : passengerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try{
            paperTechnicianThread.join();
            tonerTechnicianThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println();
        System.out.println("***** All the printing processes are done.*****");
        System.out.println();
        System.out.println("--------------------------Printer status---------------------------");
        System.out.println("Paper level: " + ticketMachine.getPaperLevel());
        System.out.println("Toner level: " + ticketMachine.getTonerLevel());
        System.out.println("--------------------------------------------------------------------");



    }


}
