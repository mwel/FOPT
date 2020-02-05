import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebsiteAnalysis {

    private static String latestArticleHeadline;
    private int cycle = 0;

    public static void main(String[] args) throws MalformedURLException {

        while (true) {

            try {
                URL url = new URL("https://sites.cs.ucsb.edu/~cappello/lectures/rmi/helloworld.shtml");

                Scanner scanner = new Scanner(url.openStream(), "UTF-8");
                while (scanner.hasNextLine()) {

                    String line = scanner.nextLine();


                    if (line.contains("Hello world!")) {

                        if (line.equals(latestArticleHeadline)) {

                            // send email to admin


                            latestArticleHeadline = line;
                        }
                        break;
                    }
                }
                scanner.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000);
                System.out.println("Run successful");

            } catch (
                    InterruptedException e) {
            }
        }
    }

    private static void sendEmail(String lineInput) {

        // TODO System output for now - emailing should be implemented later.
        System.out.println("Page title has changed.");
        String output = lineInput.substring(lineInput.indexOf("<title>") + 1, lineInput.lastIndexOf("</title>"));
    }
}

