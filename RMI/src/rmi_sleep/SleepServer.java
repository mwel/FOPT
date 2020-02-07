package rmi_sleep;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class SleepServer {

    public static void main(String[] args) {
        try {

            Sleeper sleepServer = new Sleeper();
            Naming.rebind("SleepServer 1", sleepServer);
            Naming.rebind("SleepServer 2", sleepServer);

        } catch (RemoteException | MalformedURLException rE) {
            System.out.println(rE);
            rE.printStackTrace();
        }
    }
}
