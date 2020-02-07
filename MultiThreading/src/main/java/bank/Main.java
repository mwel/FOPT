package bank;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();
        for (int i = 1; i <= 7; i++) {

            new Clerk("Clerk #" + i, bank);
        }
    }
}
