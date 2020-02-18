public class UndSchaltung {

    private boolean schalter1 = false;
    private boolean schalter2 = false;


    public synchronized void wartenAufStrom() {
        while (!(schalter1 & schalter2)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Jetzt fließt Strom!");
        }


        public synchronized void setzenSchalter1 ( boolean geschlossen){

            if (this.schalter1 == geschlossen) {
                return;
            }

        this.schalter1 = geschlossen;
        if (schalter1) {
            System.out.println("Schalter1 geschlossen");
        } else {
            System.out.println("Schalter1 geöffnet");
        }

        notifyAll();
    }


    public synchronized void setzenSchalter2(boolean geschlossen) {
        this.schalter2 = geschlossen;
        if (geschlossen)
            System.out.println("Schalter2 geschlossen");
        else
            System.out.println("Schalter2 geöffnet");
        notifyAll();

    }

}

    public static void main(String[] args) {

        boolean geschlossen = false;

        UndSchaltung undSchaltung = new UndSchaltung();
        undSchaltung.setzenSchalter1(true);
        undSchaltung.setzenSchalter2(false);


    }
}