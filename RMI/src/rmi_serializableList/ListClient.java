package rmi_serializableList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ListClient {

    public static void main(String[] args) {

        if (args.length < 2) {

            System.out.println("Necessary arguments not foung: <server name>, <number 1>, . . ., <number n>");
            return;
        }
        try {
            Append appenderServer = (Append) Naming.lookup("rmi://" + args[0] + "/" + args[1]);
            System.out.println("Server connection established.");
            List list = new List();
            for (int i = 1; i < args.length; i++) {

                int value = Integer.parseInt(args[i]);
                list.append(value);

            }

            List listReturned = appenderServer.append(list);

            System.out.print("List that has been given to the server as parameter:");
            list.print();
            appenderServer.append(list);
            System.out.println("RMI returned list:");
            listReturned.print();


        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
