public class Excursus {

//    public static void main(String[] args) {
//
//        // BEISPIEL 1
//        Object object = new Object();
//        System.out.println(object.toString());
//
//        // Erzeugen eines neuen Objekts mit RUmpföffnung anstatt Semi-Kolon
//        object = new Object() { // Hier kann ich jetzt alles machen, was ich in einer normalen Klasse auch machen könnte.
//            @Override public String toString () {
//                return "I am an object of an anonymous class.");
//            }
//        }; // hier kommt jetzt das Semikolon - eine anonyme Klasse wurde also einfach hier in das Objekt geschachtelt.

    private final String outerAttribute;

    public Excursus(String outerAttribute) {
        this.outerAttribute = "Attribut aus umschließenden Objekt.";
        final String localVariable = "Lokale Variable des Konstruktors";

        final Runnable runnable = new Runnable() { // Runnable ist eine Schnittstelle und kann daher eigentlich gar nicht
            // implementiert werden. Wenn wir aber alle Methoden aus der Schnittstelle implementieren, dann geht das schon.
            @Override // Runnable hat nur eine Methode => run()
            public void run() {
                System.out.println(localVariable);
                System.out.println(Excursus.this.outerAttribute);
                Excursus.this.outerMethod(localVariable);

            }
        };
        runnable.run();
    }

    private void outerMethod(final String parameter) {

    }

}

// Wenn ich an einer einzigen Stelle kurz eine zusätzliche Implementierung brauche, dann macht das Sinn.
// Falls ist die Funktion aber an mehreren Stellen benutzen will, geht das nicht, weil die Klasse ja keinen Namen hat,
// mit dem ich ein Objekt der Klasse erzeugen könnte.