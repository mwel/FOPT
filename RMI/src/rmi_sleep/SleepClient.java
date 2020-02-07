package rmi_sleep;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SleepClient {

    public static void main(String[] args) {

        if (args.length < 3) {

            System.out.println("Necessary arguments are: <server name>, <object name>, <seconds 1>, . . ., <seconds n>");
            return;

        }

        try {
            Sleep sleepServer = (Sleep) Naming.lookup("rmi://" + args[0] + "/" + args[1]);
            System.out.println("Connection established.");
            for (int i = 2; i < args.length; i++) {

                int secs = Integer.parseInt(args[i]);
                sleepServer.sleep(secs);
                System.out.println("Called sleep(" + secs + ")");
            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
