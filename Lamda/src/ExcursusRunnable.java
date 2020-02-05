public class ExcursusRunnable {

    private static void runConcurrently(final Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void main(final String[] args) {

        ExcursusComparator.runConcurrently(new Runnable() { // Mit jedem Aufruf würde hier ein neues Objekt erzeugt. Das ist teuer.
            @Override
            public void run() {
                System.out.println("Concurrently running.");
            }
        });


        // Als Lambda (Angabe der Parameterliste linke und dem Code rechts vom Pfeil)
        ExcursusRunnable.runConcurrently(() -> System.out.println("Concurrently running.")); // hier wird nur einmal ein Objekt gepuffert und dann immer wieder verwendet.

        // Kürzer mit Methodenreferenz
        ExcursusRunnable.runConcurrently(ExcursusRunnable::toBeExecutedConcurrently); // hier muss evtl. kein Objekt erzeugt werden.

        // Vorteile: Kürzer, simpler und optimiert die Laufzeit, weil billiger.

        // Kann nur bei einer Implementierung einer FUNKTIONALEN SCHNITTSTELLE benutzt werden. (Interface mit nur genau 1 Methode.

        // Bei mehreren Zeilen ist return dann wieder erforderlich.
    }

    private static void toBeExecutedConcurrently() {
        System.out.println("Concurrently running.");
    }


}
