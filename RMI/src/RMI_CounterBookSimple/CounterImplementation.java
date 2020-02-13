package RMI_CounterBookSimple;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CounterImplementation extends UnicastRemoteObject implements Counter {

    private int counter;

    public CounterImplementation() throws RemoteException {
    }

    @Override
    public int reset() throws RemoteException {

        counter = 0;
        System.out.println("Counter has been reset.");
        return counter;
    }

    @Override
    public int increment() throws RemoteException {

        counter++;
        System.out.println("Counter = " + counter);
        return counter;
    }
}
