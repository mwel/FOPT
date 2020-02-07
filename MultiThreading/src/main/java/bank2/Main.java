package bank2;

public class Main {

    public static void main(String[] args) {

        Bank UBS = new Bank();

        for (int i = 0; i < 3; i++) {

            new Clerk("Clerk [" + i + "]", UBS);

        }

    }
}
