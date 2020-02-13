package rmi_serializableList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Appender extends UnicastRemoteObject implements Append {

    public Appender() throws RemoteException {
    }

    @Override
    public List append(List list) throws RemoteException {
//        System.out.print("Received List: ");
//        list.print();
//
//        list.append(4711);
//        System.out.println("Manipulated List: ");
//        list.print();
        return list;

    }
}
