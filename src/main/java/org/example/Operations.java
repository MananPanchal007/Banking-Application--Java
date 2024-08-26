package org.example;
import java.util.Scanner;

public class Operations {
    private static Scanner scan = new Scanner(System.in);

    public static void bankInfo() {
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" ***Banking System Application***");
        System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println(" 1. Create a new account  \n 2. Check Balance\n 3. Deposit the amount \n 4. Withdraw the amount  \n 5. Watch demo account \n 6. Exit  \n\nENTER YOUR CHOICE :: ");

        if (scan.hasNextInt()) {
            int key = scan.nextInt();
            if (key >= 1 && key <= 6) {
                Operations operation = new Operations();
                operation.operation(key);
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                bankInfo();
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scan.next(); // Clear the invalid input
            bankInfo();
        }
    }

    public void operation(int key) {
        BankInfo bank = new BankInfo();
        Processes bankProcess = new Processes();

        switch (key) {
            case 1:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                bankProcess.openAccount();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                returnToMainPage();
                break;
            case 2:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                bankProcess.checkBalance();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                returnToMainPage();
                break;
            case 3:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.println("---------WELCOME TO DEPOSIT PAGE---------------- ");
                bankProcess.deposit();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                returnToMainPage();
                break;
            case 4:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                bankProcess.withdraw();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                returnToMainPage();
                break;
            case 5:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                bankProcess.demoAccount();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                returnToMainPage();
                break;
            case 6:
                System.out.println("THANKS FOR USING OUR BANK APPLICATION");
                break;
            default:
                System.out.println("Invalid choice. Returning to main page.");
                returnToMainPage();
                break;
        }
    }

    private void returnToMainPage() {
        System.out.println("MAIN PAGE_:: PRESS 1 ::");
        if (scan.nextInt() == 1) {
            bankInfo();
        }
    }
}
