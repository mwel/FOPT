package Misc;

public class X extends Thread {

    private int value;

    public void setValue(int newValue) {

        this.value = newValue;

    }

    public int getValue() {

        return value;
    }

    public void waitFor(int min, int max) {

        if (max < min) {
            throw new IllegalArgumentException();
        }

        while (!(value >= min && value <= max)) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}