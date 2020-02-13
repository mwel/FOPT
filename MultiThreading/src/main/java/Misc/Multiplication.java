package Misc;

public class Multiplication {

    private int result;
    private int arguments = 0;

    public int multiply(int[] args) {

        while(arguments > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        arguments = args.length;
        result = 1;

        for (int arg : args) {
            result *= arg;
            arguments--;
        }
        notifyAll();
        return result;
    }
}

// Option 2

 class Multiplication2 {


    public int multiply(int[] args) {

        int result = 1;

        for (int arg : args) {
            result *= arg;
        }
        return result;
    }
}