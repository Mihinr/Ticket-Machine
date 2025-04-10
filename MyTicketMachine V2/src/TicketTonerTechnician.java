import java.util.Random;

public class TicketTonerTechnician implements Runnable{
    public static final int NUMBER_OF_RETRIES = 3;
    private final TicketMachine ticketMachine;
    private final String name;

    public TicketTonerTechnician( String name, TicketMachine ticketMachine) {
        this.name = name;
        this.ticketMachine = ticketMachine;

    }

    @Override
    public void run() {

        for (int i = 0; i < NUMBER_OF_RETRIES; i++) {

            try{

                if(ticketMachine.getPrintingComplete()){
                    break;
                }

                ticketMachine.replaceTonerCartridge(5,(i+1) );

                Thread.sleep(new Random().nextInt(2000) + 1000);


            }catch (InterruptedException e){
                e.printStackTrace();
            }

            if(ticketMachine.getPrintingComplete()){
                break;
            }


            if((i+1) >= NUMBER_OF_RETRIES){
                System.out.println("-------------- Reached the maximum number of tries("+NUMBER_OF_RETRIES+") for the Toner Technician --------------");


                break;



            }


        }



    }

    public String getName() {
        return name;
    }
}
