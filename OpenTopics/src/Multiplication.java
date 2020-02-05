public class Multiplication {
    private int result;

    public int multiply(int[] args) {
        result = 1;
        for (int arg : args) // how does this work?
        {
            result *= arg;
        }
        return result;
    }
}

class ThreaderMaker extends Thread {

    public void createThreads() {

        for (int i = 0; i < 10; i++) {
            new Thread();
            System.out.println("New Thread created #" + i);

        }

    }
}

class Tester {

    public static void main(String[] args) {

        ThreaderMaker threadMaker = new ThreaderMaker();
        threadMaker.createThreads();

        Multiplication mult = new Multiplication();



        //  System.out.println(mult.multiply(new int[]{2, 3, 4}));
    }
}