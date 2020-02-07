package rmi_sleep;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Sleeper extends UnicastRemoteObject implements Sleep {

    public Sleeper() throws RemoteException {

    }

    @Override
    public synchronized void sleep(int secs) throws RemoteException {

        System.out.println("sleep(" + secs + ") - START");

        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException rE) {
            System.out.println(rE);
        }

        System.out.println("sleep(" + secs + ") - END");

    }

}
