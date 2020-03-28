package RMI;

import java.rmi.Naming;

public class RMI_Client {

    public static void main(String[] args) {

        try {
            Demo demo = (Demo) Naming.lookup("rmi://localhost:1099/Demo");

            DataImplementation data = new DataImplementation();
            //UnicastRemoteObject.exportObject(data,9998); // switch between CallbyValue and CallbyReference
            demo.increment(data);

            System.out.println("Data on Client after RMI " + data.getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
