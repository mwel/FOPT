package PremiumParkhaus;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PremiumParkhausLock extends Thread {

    private final int plaetze;
    private int freiePlaetze;
    private Lock lock = new ReentrantLock();
    private Condition normalUser = lock.newCondition();
    private Condition premiumUser = lock.newCondition();

    public PremiumParkhausLock(int plaetze) {

        this.plaetze = plaetze;
        this.freiePlaetze = plaetze;

    }

    public void enter() {

        lock.lock();

        try {
            while (!(freiePlaetze >= 0)) {

                try {
                    normalUser.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            freiePlaetze--;
            normalUser.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void enterPremium() {

        lock.lock();

        try {
            while (!(freiePlaetze >= 0)) {

                try {
                    premiumUser.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            freiePlaetze--;
            premiumUser.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void leave() {

        freiePlaetze++;
    }
}
