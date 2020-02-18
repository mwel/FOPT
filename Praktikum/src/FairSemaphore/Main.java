package FairSemaphore;

public class Main {

    public static void main(String[] args) {

        CartManager cartManager = new CartManager(20);

        int shoppers = 40;
        System.out.println("Total shoppers: " + shoppers);

        for (int i = 0; i < shoppers; i++) {

            Shopper shopper = new Shopper(cartManager, i);
            shopper.start();
        }
    }
}
