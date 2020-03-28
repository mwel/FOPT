import java.util.Arrays;

public class Zahlenschloss2 extends Thread {

    private final int[] kombi;
    private int[] currentNumberSet;
    private boolean correct = false;

    public Zahlenschloss2(int[] kombi) {

        this.kombi = kombi;
        this.currentNumberSet = new int[anzahlRaedchen()];

        for (int i = 0; i < anzahlRaedchen(); i++) {
            currentNumberSet[i] = 0;
        }
    }

    public synchronized void drehen(int radnummer, int zahl) {

        currentNumberSet[radnummer] = zahl;

        if (Arrays.equals(currentNumberSet, kombi)) {
            correct = true;
            System.out.println("Schloss geöffnet.");
            notifyAll();
        }
    }

    public synchronized void warten() {
        if (correct) {
            return;
        }

        while (!correct) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int lesen(int radnummer) {

        return currentNumberSet[radnummer];

    }

    public int anzahlRaedchen() {

        return kombi.length;
    }


}

/*Aufgabe e)

synchronized (this) {
  	for ( int i = 0;  i < zk.anzahlRaedchen(); i++ ) {
    	System.out.print("" + zk.lesen());
  	}
	System.out.println();
}*/