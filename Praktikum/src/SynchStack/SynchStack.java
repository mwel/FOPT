package SynchStack;

import java.util.ArrayList;
import java.util.List;

public class SynchStack extends Thread {

    private List<Object> stack;
    private int stackSize = 0;

    public SynchStack() {

        stack = new ArrayList<>();
    }

    public synchronized void push(Object element) {

        stack.add(element);
        stackSize++;
        System.out.println("Pushed: " + element.toString());
        System.out.println("Stack Size: " + stackSize);
        notifyAll();
    }

    public synchronized Object pop() {

        while (stack.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stackSize--;
        Object topElement = stack.get(stackSize);

        stack.remove(topElement);

        System.out.println("Popped: " + topElement.toString());
        System.out.println("Stack Size: " + stackSize);

        return topElement;
    }

}
