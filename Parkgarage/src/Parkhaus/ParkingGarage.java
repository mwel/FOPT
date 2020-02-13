package Parkhaus;

public class ParkingGarage {


    private int freeSlots;
    private int nextTicketNumber;
    private int nextEnteringNumber;

    public ParkingGarage(int freeSlots, int nextWaitingNumber, int nextEnteringNumber) {
        if (freeSlots < 0)
            throw new IllegalArgumentException("There is no parking garage with less than 0 parking slots.");
        this.freeSlots = freeSlots;
        this.nextTicketNumber = 0;
        this.nextEnteringNumber = 0;
    }

    public synchronized void enter() {

        int myTicketNumber = nextTicketNumber++;
        while (myTicketNumber != nextEnteringNumber || freeSlots == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        freeSlots--;
        nextEnteringNumber++;
        notifyAll();
    }

    public synchronized void exit() {
        freeSlots++;
        notifyAll();
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }
}
