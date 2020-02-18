package FairSemaphore;

public class Shopper extends Thread {

    private CartManager cartManager;
    private int shopperNumber;

    public Shopper(CartManager cartManager, int shopperNumber) {

        this.cartManager = cartManager;
        this.shopperNumber = shopperNumber;
    }


    public void run() {
        requestCart();

    }

    public void requestCart() {

        cartManager.handOutCart(shopperNumber);
        goShopping();
    }

    public void returnCart() {

        cartManager.takeBackCart(shopperNumber);

    }

    public void goShopping() {

        System.out.println("Shopper " + shopperNumber + " going shopping.");

        try {
            Thread.sleep((int) (Math.random()*5000));
            returnCart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
