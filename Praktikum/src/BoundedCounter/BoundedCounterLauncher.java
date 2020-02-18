package BoundedCounter;

public class BoundedCounterLauncher {

    private static int iterationUp = 0;
    private static int iterationDown = 0;


    public static void main(String[] args) {

        BoundedCounter boundedCounter = new BoundedCounter(0, 1000);
        for (int i = 0; i < 3; i++) {

            new Thread(() -> {

                // for (int j = 0; j < 2; j++) {
                while (iterationUp < 20) {
                    boundedCounter.up();
                    iterationUp++;
                    System.out.println("Counter at " + boundedCounter.getCount());

                }

                //for (int j = 0; j < 2; j++) {
                while (iterationDown < 20) {
                    boundedCounter.down();
                    iterationDown++;
                    System.out.println("Counter at " + boundedCounter.getCount());

                }


            }).start();


            System.out.println("Final count: " + boundedCounter.getCount());

        }
    }
}
