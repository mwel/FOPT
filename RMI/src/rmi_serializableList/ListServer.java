package rmi_serializableList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ListServer {

    public static void main(String[] args) {
        try {
            Appender appenderServer = new Appender();
            Naming.rebind("AppenderServer", appenderServer);

        } catch (RemoteException | MalformedURLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
