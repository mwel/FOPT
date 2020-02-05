package rmi_CounterGUI;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMI_CounterClient {

    public static void main(String[] args) throws IOException, NotBoundException {

        if (args.length != 2) {

            System.out.println("Could not find necessary arguments: <server name>, <increment number>");

            return;
        }

        // Der Client beantragt bei der Registry ein bestimmtes Stub. Diese Anfrage stellen wir aus den erforderlichen
        // Informationenkommt in Form des folgenden Strings zusammen:
        final String objURL = String.format("rmi://%s/%s", args[0], RMI_Counter_Interface.DEFAULT_RMI_OBJECT_NAME);

        // Jetzt beantragen wir den Stub bei der Registry über die Methode lookup() unter Angabe der soeben
        // erzeugten Anfrage-URL und schreiben das Objekt, das wir zurückbekommen in ein lokales Objekt der selben
        // Schnittstelle.
        final RMI_Counter_Interface counter = (RMI_Counter_Interface) Naming.lookup((objURL));
        // Wenn das nicht klappt => IOE / Falls in registry nicht vorhanden => NBE

        // Jetzt kann mit der entfernten Methode gespielt werden. Dazu holen wir uns die entsprechenden Infos (hier increment amount)
        final int incrementCount = Integer.parseInt(args[1]);

        // Was damit gemacht wird, lagern wir aus.
        RMI_CounterClient.playWithCounter(counter, incrementCount);

    }

    private static void playWithCounter(final RMI_Counter_Interface counter, int incrementCount) throws RemoteException {
        // In dieser Methode kann man jetzt komplett transparent mit dem Counter arbeiten - als wäre er lokal

        final int counterValueAfterReset = counter.reset();
        System.out.println("Answer: " + counterValueAfterReset);

        for (int i = 0; i < incrementCount; i++) {

            final int counterValueAfterIncrement = counter.increment();
            System.out.println("Answer: " + counterValueAfterIncrement);

        }
    }
}