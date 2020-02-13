package RMI_CounterBookGUI;

import RMI_CounterBookSimple.Counter;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.util.function.Consumer;

public class ClientGuiPresenter {

    private Counter model;
    private ClientGuiView view;

    public void setModelAndView(Counter model, ClientGuiView view) {

        this.model = model;
        this.view = view;

    }

    public void increment() {

        // Funktion: Hole neuen Wert vom Server und gib ihn an die View zurück.

        asyncCall(() -> model.increment(), (c) -> view.showCounter(c));

        /* Das Model ist hier der Supplier und die View der Consumer.
        Was passiert hier?:
        Funktion wird in neue Methode ausgelagert. (hier asyncCall) Übergib der Methode so viele Lambda-Ausdrücke,
        wie Du willst. Dabei muss beachtet werden, welche Parameter durchgeschleift werden müssen.)
         */

    }

    public void reset() {

        asyncCall(() -> model.reset(), (c) -> view.showCounter(c)); // Was genau passiert hier? Wir hier eine Referenz auf eine Methode zurückgegeben.
    }

    public <T> void asyncCall(RMISupplier<T> rmiCall, Consumer<T> fxCall) {

        // Hier werden jetzt Producer / Supplier und Consumer übergeben.
        // Dann werden diese hier in einem neuen Thread gepackt.
        // Die eigentliche MAgic passiert aber wieder ausgelagert.
        new Thread(() -> doInThread(rmiCall, fxCall)).start();

    }

    private <T> void doInThread(RMISupplier<T> rmiCall, Consumer<T> fxCall) {

        // Nochmals ausgelagert ist die eigentliche runlater()

        // Egal ob increment oder reset aufegrufen werden sollen. "t" führt einfach die Methode aus, die
        // übergeben wurde - egal welche es ist.
        try {
            T t = rmiCall.execute();
            Platform.runLater(() -> fxCall.accept(t));
        } catch (RemoteException e) {
            System.out.println("RMI Error");
        }
    }
}
