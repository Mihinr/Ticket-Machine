public interface ServiceTicketMachine {
    void replaceTonerCartridge(int newTonerLevel, int tonerRefillCount);
    void refillTicketPaper(int newPaperLevel, int paperRefillCount);

    void printTicket(Ticket ticket);
}
