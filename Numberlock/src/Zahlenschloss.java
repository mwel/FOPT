public class Zahlenschloss {

    public static Zahlenschloss Zahlenschloss;
    private boolean istrue = false;
    private boolean tempBool = true;

    private int[] zahl;
    private int[] zahlKorrekt;

    // Konstruktor
    public Zahlenschloss(int[] zahl, int[] zahlKorrekt) {

        this.zahl = zahl;
        this.zahlKorrekt = zahlKorrekt;

        for (int i = 0; i < zahl.length; i++) {
            this.zahl[i] = zahl[i];
            this.zahlKorrekt[i] = zahl[i];
        }
    }

    public synchronized void drehen(int radNummer, int zahl) {

        this.zahl[radNummer] = zahl;
        tempBool = true;
        for (int i = 0; i < anzahlRaedchen(); i++) {

            if (this.zahl[i] != zahlKorrekt[i]) {
                tempBool = false;
            }
        }

        if (tempBool) {
            istrue = true;
            System.out.println("Schloss geöffnet.");
            notifyAll();
        }
    }

    public synchronized void warten() {
        while (!istrue) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int lesen(int radNummer) {
        return zahl[radNummer];
    }

    public int anzahlRaedchen() {
        return zahl.length;
    }

}


class Main {

    private static int zahl;
    private static int[] Kombination;

    public static void main(String[] args) {

        Zahlenschloss schloss1 = new Zahlenschloss(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4});
        schloss1.drehen(4, 1234);

//        // taking String array input from user
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter length of String array");
//        int length = sc.nextInt();
//
//        // create a String array to save user input
//        String[] input = new String[length];
//
//        // loop over array to save user input
//        System.out.println("Please enter array elements");
//        for (int i = 0; i < length; i++) {
//            String userInput = sc.next();
//            input[i] = userInput;
//        }
//
//        System.out.println("The String array input from user is : ");
//        System.out.println(Arrays.toString(input));
//
//        sc.close();
//
//        Zahlenschloss.setZahl();

    }

}

