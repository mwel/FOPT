public class Zipper {

    public static int[] zip(int[] array1, int[] array2, IntInt2Int ii2iInterface) {

        int[] result = new int[(array1.length < array2.length) ? array1.length : array2.length];

        for (int z = 0; z < result.length; z++) {
            result[z] = ii2iInterface.ii2i(array1[z], array2[z]);
        }

        return result;

    }

    public interface IntInt2Int {

        public int ii2i(int i, int j);
    }

}


