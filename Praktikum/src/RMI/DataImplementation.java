package RMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DataImplementation extends UnicastRemoteObject implements Serializable, Data {

    public DataImplementation() throws RemoteException {
    }

    private int value = 0;

    public int getValue() throws RemoteException {

        System.out.println("Value is " + value);
        return value;
    }

    public void setValue(int value) throws RemoteException {

        this.value += value;
        System.out.println("Set value to" + getValue());
    }
}
