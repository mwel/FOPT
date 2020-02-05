public class Parking {

    public static void main(String[] args) {

        ParkingGarage garage = new ParkingGarage(10, 0, 0);
        System.out.println("New parking garage with " + garage.getFreeSlots() + " free slots has been built.");

        int i;
        for (i = 1; i <= 40; i++) {

            new Car("Car " + i, garage);
        }
        i--;
        System.out.println(i + " cars are currently waiting for a free slot.");
    }
}
