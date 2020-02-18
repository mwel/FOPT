package Ampel;

public class ItalienischeAmpel implements Ampel {

    private boolean GRUEN;
    private int wartendeFahrzeuge = 0;

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

        wartendeFahrzeuge++;
        while (!GRUEN) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        wartendeFahrzeuge--;
    }

    @Override
    public synchronized int wartendeFahrzeuge() {

        return wartendeFahrzeuge;
    }
}
