package FairSemaphore;

import java.util.ArrayList;

public class CartManager {

    private int cartsAvailable;
    private ArrayList<Thread> shopperQueue;


    public CartManager(int cartsAvailable) {

        this.cartsAvailable = cartsAvailable;
        shopperQueue = new ArrayList<>();
        System.out.println("Total carts available: " + cartsAvailable);

    }

    public synchronized void handOutCart(int shopperNumber) {

        shopperQueue.add(shopperQueue.size(), Thread.currentThread());
        while (cartsAvailable <= 0 || Thread.currentThread() != shopperQueue.get(0)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setCartsAvailable(-1);
        shopperQueue.remove(0);
        System.out.println("Cart handed out to shopper " + shopperNumber + " | " + getCartsAvailable());
    }

    public synchronized void takeBackCart(int shopperNumber) {

        setCartsAvailable(1);
        System.out.println("Shopper " + shopperNumber + " returned cart. | " + getCartsAvailable());
        notifyAll();
    }

    public synchronized int getCartsAvailable() {

        return cartsAvailable;

    }

    public synchronized void setCartsAvailable(int amount) {

        cartsAvailable = getCartsAvailable() + amount;

    }

}
