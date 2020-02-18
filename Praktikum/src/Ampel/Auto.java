package Ampel;

public class Auto extends Thread {

    Ampel[] ampeln;

    public Auto(Ampel[] ampeln) {

        this.ampeln = ampeln;
    }

    public void run() {

        while (true) {

            for (int i = 0; i < ampeln.length; i++) {

                ampeln[i].passieren();

            }

        }

    }

}
