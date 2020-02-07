package rmi_serializableList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Append extends Remote {

    public List append(List list) throws RemoteException;
}

