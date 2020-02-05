import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTester {

    public static void main(String[] args) {


        // First Lambda Test Block
//        int[] a1 = new int[]{5, 12, 78, -2, -9, 48, -92, 74};
//        int[] a2 = new int[]{17, -6, 2, 1, 93, -28, 49, 287};
//
//        int[] result = Zipper.zip(a1, a2, (i, j) -> {
//            return i + j;
//        });
//        System.out.print("a1: ");
//        System.out.println(Arrays.toString(a1));
//
//        System.out.print("a2: ");
//        System.out.println(Arrays.toString(a2));
//
//        System.out.print("result: ");
//        System.out.println(Arrays.toString(result));

        // Second Lambda Test Block
        List<String> myList = Arrays.asList("Es", "war", "einmal");
        List<String> result = myList.stream().map(String::toUpperCase).collect(Collectors.toList());

        System.out.println(myList);
        System.out.println(result);

    }

}
