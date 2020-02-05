public class Car extends Thread {

    private ParkingGarage garage;

    public Car(String name, ParkingGarage garage) { // wozu brauche ich hier im Konstruktor des Autos die Parkgarage? Die gehört hier doch gar nicht hin.
        super(name);
        this.garage = garage;
        start();
    }

    public void run() {

        while (true) {
            try {
                sleep((int) (Math.random() * 30000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            garage.enter();
            System.out.println(getName() + " IN");
            System.out.println(garage.getFreeSlots() + " FREE SLOTS");

            try {
                sleep((int) (Math.random() * 20000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " OUT");
            System.out.println(garage.getFreeSlots() + " FREE SLOTS");
            garage.exit();
        }
    }
}
