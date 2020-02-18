package Ampel;

import java.util.ArrayList;

public class AmpelSteuerung {

    Ampel[] ampeln = new Ampel[20];
    ArrayList<Auto> autos = new ArrayList<Auto>();

    public AmpelSteuerung(Ampel[] ampeln, ArrayList<Auto> autos) {

        this.ampeln = ampeln;
        this.autos = autos;
    }


    public static void main(String[] args) {


    }
}
