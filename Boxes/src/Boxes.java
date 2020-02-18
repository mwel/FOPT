/* Angabe

Gegeben sind zwei gleich lange Felder. Int und Boolean. Im Konstruktor wird das int-Feld befüllt und eine Variable maxWeight deklariert.
Mehrere Threads sollen auf die int / boolean Felder zugreifen können. Wenn im boolean Feld false steht, darf auf das zugehörige int Feld zugegriffen werden. Bei true / inUse nicht.
Insgesamt darf nur auf so viele Felder zugegriffen werden, bis die Summe der int-Felder <= maxWeight.

*/
public class Boxes {

    private static int[] gewicht = new int[100];
    private static boolean[] inUse = new boolean[100];

    private int maxGewicht;
    private int currentWeight;

    public Boxes(int maxWeight) {

        for (int i = 0; i < 100; i++) {

            gewicht[i] = (int) (Math.random() * 100);
            inUse[i] = false;
            this.maxGewicht = maxWeight;
        }
    }

    public synchronized void use(int box) { // ich will die Box benutzen. Quasi wie lock.

        if (inUse[box] || overWeight(box)) {
            try {
                System.out.println("Box [" + box + "] in use.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            inUse[box] = true;
            currentWeight += gewicht[box];
        }
        notifyAll();
    }

    public synchronized void dontUse(int box) { // ich will die Box nicht mehr benutzen. Quasi wie unlock.
        if (inUse[box]) {
            inUse[box] = false;
            currentWeight -= gewicht[box];
            notifyAll();
        } else {
            System.out.println("Box [" + box + "] not in use.");
        }
    }

    public synchronized boolean overWeight(int box) {

        int checkWeight = 0;

        for (int i = 0; i < 100; i++) {

            if (inUse[i]) {
                checkWeight += gewicht[i];
            }
        }

        return checkWeight + box >= maxGewicht;
    }

    public static void main(String[] args) {

        Boxes boxes = new Boxes(300);

        for (int i = 0; i < gewicht.length; i++) {

            System.out.print("InUse: " + inUse[i] + " | ");
            System.out.println(gewicht[i]);

        }
    }
}
