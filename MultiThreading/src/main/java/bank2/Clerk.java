package bank2;

public class Clerk extends Thread {

    private Bank bank;

    public Clerk(String name, Bank bank) {

        super(name);
        this.bank = bank;
        start();

    }

    public void run() {

        for (int i = 0; i < 10000; i++) {

            // choose sending Account
            int sendingAccountNum = (int) (Math.random() * 100);

            // choose receiving Account
            int receivingAccountNum = (int) (Math.random() * 100);

            // choose amount
            float amount = (float) (Math.random() * 1000) - 500;

            System.out.println("Clerk " + getName() + " making following transaction:");
            bank.transaction(sendingAccountNum, receivingAccountNum, amount);

        }

    }
}
