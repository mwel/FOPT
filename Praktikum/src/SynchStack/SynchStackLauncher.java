package SynchStack;

public class SynchStackLauncher {

    private SynchStack synchStack;

    public SynchStackLauncher(SynchStack synchStack) {

        this.synchStack = synchStack;

    }

    public static void main(String[] args) {

        SynchStack synchStack = new SynchStack();

        for (int i = 0; i < 30; i++) {

            new Thread(() -> {

                for (int j = 0; j < (int) ((Math.random() * 10)); j++) {

                    Object element = new Object();
                    synchStack.push(element);
                }
            }).start();

            new Thread(() -> {

                for (int k = 0; k < (int) ((Math.random() * 10)); k++) {

                    synchStack.pop();
                }
            }).start();

        }

    }
}
