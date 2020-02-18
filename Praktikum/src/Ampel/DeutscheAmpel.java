package Ampel;

import java.util.ArrayList;

public class DeutscheAmpel implements Ampel {

    private boolean GRUEN;
    private ArrayList<Thread> autos = new ArrayList<>();

    @Override
    public synchronized void schalteROT() {

        GRUEN = false;
    }

    @Override
    public synchronized void schalteGRUEN() {

        GRUEN = true;
        notifyAll();
    }

    @Override
    public synchronized void passieren() {

        autos.add(Thread.currentThread());
        while (!GRUEN || (autos.get(0) != Thread.currentThread())) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        autos.remove(0);
        notifyAll(); // Jetzt kommt zufällig das zweite Auto dran. Es ist zwar grün, aber ich bin nicht der Erste.
        // Ohne notifyAll() fährt der erste irgendwann los, aber der Rest bleibt stehen.
    }

    @Override
    public synchronized int wartendeFahrzeuge() {
        return autos.size();
    }
}
