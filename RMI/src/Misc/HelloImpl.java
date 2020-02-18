package Misc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

    protected HelloImpl() throws RemoteException {
    }

    public String hello (String name) throws RemoteException {

        return "hello" + name;

    }
}
