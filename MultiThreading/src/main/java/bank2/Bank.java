package bank2;

public class Bank {

    private Account[] account;


    public Bank() {

        account = new Account[100];

        for (int i = 0; i < account.length; i++) {

            account[i] = new Account();

        }
    }

    public void transaction(int sendingAccountNum, int receivingAccountNum, float amount) {

        synchronized (account[sendingAccountNum]) {

            synchronized (account[receivingAccountNum]) {

                account[sendingAccountNum].debitOrCredit(-amount);
                account[receivingAccountNum].debitOrCredit((amount));

                System.out.println("Move EUR " + amount + " from Account #" + sendingAccountNum + " to #" + receivingAccountNum);

            }
        }
    }

}
