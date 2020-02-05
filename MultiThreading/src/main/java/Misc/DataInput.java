package Misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataInput extends Thread {

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.println("Please enter your data here as asked:");

            System.out.println("Enter name: ");
            String inputName = br.readLine();
            System.out.println("Enter surname: ");
            String inputSurName = br.readLine();
            System.out.println("Enter location: ");
            String inputLocation = br.readLine();
            System.out.println("Enter email address: ");
            String inputAddress = br.readLine();
            System.out.println("Enter phone number: ");
            String inputPhoneNumber = br.readLine();

            System.out.println("--- Your Summary ---");
            System.out.println("Name: " + inputName);
            System.out.println("Surname: " + inputSurName);
            System.out.println("Location: " + inputLocation);
            System.out.println("Email address: " + inputAddress);
            System.out.println("Phone number: " + inputPhoneNumber);

            System.out.println("Everything in order? (Y/N)");
            String checkCorrect = br.readLine();
            if (!checkCorrect.equals("Y")) {
                System.out.println("Sorry, your data could not be saved. Please run again. :( ");
            } else {
                System.out.println("Thank you. Your data has been saved successfully. ;)");
            }
        } catch (IOException e) {
            System.err.println("Data could not be entered.");
            e.printStackTrace();
        }
    }
}
