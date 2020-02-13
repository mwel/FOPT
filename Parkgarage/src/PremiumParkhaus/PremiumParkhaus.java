package PremiumParkhaus;

import java.util.ArrayList;

public class PremiumParkhaus extends Thread {

    private final int plaetze;
    private int freiePlaetze;
    private ArrayList<Thread> premiumWarteschlange = new ArrayList<Thread>();

    public PremiumParkhaus(int plaetze) {

        this.plaetze = plaetze;
        this.freiePlaetze = plaetze;
        premiumWarteschlange.clear();

    }

    public synchronized void enter() {

        while (!(freiePlaetze >= 0) && (premiumWarteschlange.size() <= 0)) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        freiePlaetze--;
        notifyAll();
    }

    public synchronized void enterPremium() {

        premiumWarteschlange.add(currentThread());
        while (!(freiePlaetze > 0)) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        freiePlaetze--;
        premiumWarteschlange.remove(currentThread());

        notifyAll();
    }

    public synchronized void leave() {

        freiePlaetze++;
        notifyAll();
    }
}
