package bank;

public class Clerk extends Thread {

    private Bank bank;

    public Clerk(String name, Bank bank) {

        super(name);
        this.bank = bank;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            // Kontonummer des Kunden eingeben
            int accountNumber = (int) (Math.random() * 100);

            // Betrag eingeben
            float amount = (float) (Math.random() * 1000) - 500;

            bank.transaction(accountNumber, amount);
        }
    }
}
