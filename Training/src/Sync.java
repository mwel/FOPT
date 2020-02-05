import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Sync {

    public static void main(String[] args) {

        Thread threadTime = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    printTime();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadIrritate = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(getHtmlLength());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        System.out.println("Start threads: ");
        threadTime.start();
        threadIrritate.start();

    }

    private static void printTime() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));

    }

    private static int getHtmlLength() {

        try {
            URL url = new URL("https://sites.cs.ucsb.edu/~cappello/lectures/rmi/helloworld.shtml");

            Scanner scanner = new Scanner(url.openStream());
            int counter = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                counter++;

            }
            scanner.close();
            return counter;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No Internet Connection");
        }
        return 0;

    }
}
