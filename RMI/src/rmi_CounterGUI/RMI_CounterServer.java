package rmi_CounterGUI;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;

public class RMI_CounterServer {

    public static void main(String[] args) throws IOException, AlreadyBoundException {

        final RMI_SyncCounter counter = new RMI_SyncCounter();

        Naming.bind(RMI_Counter_Interface.DEFAULT_RMI_OBJECT_NAME, counter); // Anmelden des Stubs an der lokalen Registry auf dem Server (Name für die Registrierung und Objekt dazu)
        System.out.println("Counter server ready.");

        // Automatisch wird ein Skeleton erzeugt und in einem eignen Thread ausgeführt.

        // RMI ist nichts anderes, als ein dynamisch parallel umgesetzter TCP Server.
    }
}

