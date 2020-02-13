public class Boxes {

    private int[] gewicht;

    private boolean[] wirdBenutzt;

    public static void main(String[] args) {


    }


    public synchronized void use() {

        if (wirdBenutzt[]){

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public synchronized void dontuse() {


    }
}
