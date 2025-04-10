public class TicketMachine implements ServiceTicketMachine {

    private int paperLevel;
    private int tonerLevel;

    private int totalTicketPrintedCount;
    private boolean printingComplete = false;
    private final int totalTicketsToPrint;
    private int ticketsPrinted = 0;

    public TicketMachine(int paperLevel, int tonerLevel, int totalTicketsToPrint){
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
        this.totalTicketsToPrint = totalTicketsToPrint;
    }

    public synchronized void setPrintingComplete() {
            printingComplete = true;
            notifyAll(); // Notify waiting threads that printing is complete

    }


    public synchronized boolean getPrintingComplete(){
        return printingComplete;
    }

    public synchronized int getPaperLevel() {
        return paperLevel;
    }

    public synchronized int getTonerLevel() {
        return tonerLevel;
    }

    @Override // toner tec use this method
    public synchronized void replaceTonerCartridge(int newTonerLevel, int tonerRefillCount) {
        if (ticketsPrinted >= totalTicketsToPrint) {
            setPrintingComplete();
        }


        if (printingComplete) {
            System.out.println("Printing is complete. No more tickets to print.");
        }
        else {
            while (tonerLevel > 0 && !printingComplete){
                try {
                wait(); // toner technician waits until toner is over


                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            if(!printingComplete){
                System.out.println();
                System.out.println("Toner Technician is replacing the toner....");
                System.out.println("Current toner level: "+ this.tonerLevel);                // Updates the Toner level
                System.out.println(Thread.currentThread().getName()+" Replaced the Toner.");

                this.tonerLevel = newTonerLevel;

                System.out.println("New toner level: "+ this.tonerLevel);
                System.out.println("Number of Toner refill times of the Toner Technician: "+ tonerRefillCount );
                System.out.println();
            }
            notifyAll();


        }
    }

    @Override // paper tec use this method
    public synchronized void refillTicketPaper(int newPaperLevel, int paperRefillCount) {
        if (ticketsPrinted >= totalTicketsToPrint) {
            setPrintingComplete();
        }

        if (printingComplete) {
            System.out.println("Printing is complete. No more tickets to print.");
        }
        else {
            while (paperLevel > 0 && !printingComplete){
                try {
                    wait();// toner tec waits until toner is over


                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            if(!printingComplete){
                System.out.println();
                System.out.println("Paper Technician is replacing the paper....");
                System.out.println("Current paper level: "+ this.paperLevel);
                System.out.println(Thread.currentThread().getName() + " Replaced the Paper.");

                this.paperLevel += newPaperLevel;

                System.out.println("New paper level: "+this.paperLevel );
                System.out.println("Number of paper refill times of the Paper Technician: "+ paperRefillCount);
                System.out.println();
            }
            notifyAll();

        }

    }

    @Override //passengers use this method
    public synchronized void printTicket(Ticket ticket) {

        if (ticketsPrinted >= totalTicketsToPrint) {
            setPrintingComplete();
        }

        while ((paperLevel <= 0 || tonerLevel <= 0)){
            System.out.println("Not enough resources. Waiting for refill.");
            try {
                wait();// Passengers waiting till the resources are available

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("Ticket printed: " + ticket.toString() );

        totalTicketPrintedCount += 1;
        System.out.println("Total Tickets Printed: "+ totalTicketPrintedCount);


        paperLevel --;
        tonerLevel --;

        ticketsPrinted++;

        // Check if all tickets are printed
        if (ticketsPrinted >= totalTicketsToPrint) {
            setPrintingComplete();
        }


        notifyAll();




    }

}
