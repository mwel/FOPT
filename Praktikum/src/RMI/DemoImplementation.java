package RMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DemoImplementation extends UnicastRemoteObject implements Serializable, Demo {

    public DemoImplementation() throws RemoteException {
    }

    @Override
    public void increment(Data data) throws RemoteException {

        data.getValue();
        data.setValue(1);
    }
}
