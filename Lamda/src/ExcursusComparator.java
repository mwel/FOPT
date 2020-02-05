import java.util.Arrays;
import java.util.Comparator;

public class ExcursusComparator {

    public static void main(final String[] args) {
        // Anaonyme Klasse zum Sortieren eines Arrays.
        Arrays.sort(args, new Comparator<String>() {
            @Override
            public int compare(final String o1, final String o2) {
                return o1.length() - o2.length();
            }
        });


        // Der Gleiche jetzt als Lambda-Ausdruck
        Arrays.sort(args, (final String o1, final String o2) -> o1.length() - o2.length());

        // Kürzer
        Arrays.sort(args, Comparator.comparingInt(String::length));

        // Noch kürzer
        Arrays.sort(args, (o1, o2) -> o1.length() - o2.length();

        // Mit externer Methode
        Arrays.sort(args, (o1, o2) -> ExcursusComparator.compareStringLength(o1, o2));

        // Noch kürzer
        Arrays.sort(args, ExcursusComparator::compareStringLength);


        ExcursusComparator.runConcurrently(new Runnable() {
            @Override
            public void run() {
                System.out.println("Concurrently running.");
            }
        }
    }

    private static int compareStringLength(final String s1, final String s2) {
        return s1.length() - s2.length();
    }




    private static void toBeExecutedConcurrently() {
        System.out.println("Concurrently running.");
    }

    static void runConcurrently(final Runnable runnable) {
        new Thread(runnable).start();
    }
}
