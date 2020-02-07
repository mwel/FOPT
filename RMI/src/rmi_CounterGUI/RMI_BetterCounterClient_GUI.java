package rmi_CounterGUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class RMI_BetterCounterClient_GUI extends RMI_FaultyCounterClient_GUI {

    private final Label counterLabel;

    public RMI_BetterCounterClient_GUI(RMI_SyncCounter counter, Label counterLabel) {
        super(counter);
        this.counterLabel = counterLabel;
    }

    @Override
    protected void clickedIncrementButton(final ActionEvent event) {
        // Hier wird jetzt bei jedem Button-Click ein neuer Thread erzeugt und diesem die gewünschte Methode gefüttert.
        new Thread(this::doIncrementAndAutoUpdateUi).start();
    }

    private void doIncrementAndAutoUpdateUi() {
        final int counterValueAfterIncrement = this.counter.increment();
        this.counterLabel.setText(String.valueOf(counterValueAfterIncrement));

    }

}
