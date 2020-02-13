package RMI_CounterBookSimple;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) {

        if (args.length != 2) {

            System.out.println("Arguments? ServerName and CounterAmount");
            return;
        }

        try {

            Counter counter = (Counter) Naming.lookup("rmi://" + args[0] + "/Counter");

            // Ab hier kann die entfernte Methode wie eine lokale verwendet werden:
            counter.reset();

            int count = new Integer(args[1]).intValue();
            int result = 0;

            for (int i = 0; i < count; i++) {
                result = counter.increment();
            }


        } catch (Exception e) {
            System.out.println("Exception :" + e.getMessage());
            e.printStackTrace();
        }

    }
}
