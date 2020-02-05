package rmi_counterSimple;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_SyncCounter extends UnicastRemoteObject implements RMI_Counter_Interface { // Hier wird die Schnittstelle jetzt implementiert.

    private int counter;

    // Konstruktor, den wir aus Syntaxgründen eine RE werfen lassen müssen - ansonsten keine Funktion
    public RMI_SyncCounter() throws RemoteException {
    }

    @Override
    public synchronized int reset() {

        System.out.println("Method reset has been invoked.");
        this.counter = 0;
        return counter;
    }

    @Override
    public synchronized int increment() {

        System.out.println("Method 'increment()' has been invoked.");
        System.out.println("Old count: " + this.counter);
        this.counter++;
        System.out.println("New count: " + this.counter);
        return this.counter;
    }
}
