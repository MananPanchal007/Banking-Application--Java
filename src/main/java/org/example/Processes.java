package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Processes {
    private Scanner sc = new Scanner(System.in);

    public void openAccount() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Account No: ");
            String accno = sc.next();
            System.out.print("Enter Account type: ");
            String acc_type = sc.next();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Balance: ");
            long balance = sc.nextLong();

            String query = "INSERT INTO BankInfo (accno, name, acc_type, balance) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, accno);
            pstmt.setString(2, name);
            pstmt.setString(3, acc_type);
            pstmt.setLong(4, balance);
            pstmt.executeUpdate();

            System.out.println("------YOUR ACCOUNT DETAILS ARE -------");
            System.out.println("Name of account holder :: " + name);
            System.out.println("Account no             :: " + accno);
            System.out.println("Account type           :: " + acc_type);
            System.out.println("Balance                :: " + balance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void demoAccount() {
        System.out.println("Name of account holder :: " + "Demo user");
        System.out.println("Account no             :: " + "8529637412");
        System.out.println("Account type           :: " + "demo");
        System.out.println("Balance                :: " + 50000);
    }

    public void deposit() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.println("Enter the Account No:");
            String accno = sc.next();
            System.out.println("Enter the Amount you want to deposit ::");
            long deposit = sc.nextLong();

            String query = "UPDATE BankInfo SET balance = balance + ? WHERE accno = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setLong(1, deposit);
            pstmt.setString(2, accno);
            pstmt.executeUpdate();

            System.out.println(" " + deposit + " is deposited into your Account");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void withdraw() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.println("Enter the Account No:");
            String accno = sc.next();
            System.out.println("Enter the Amount you want to withdraw:");
            long withdraw = sc.nextLong();

            String queryCheckBalance = "SELECT balance FROM BankInfo WHERE accno = ?";
            PreparedStatement pstmtCheck = con.prepareStatement(queryCheckBalance);
            pstmtCheck.setString(1, accno);
            ResultSet rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                long currentBalance = rs.getLong("balance");
                if (withdraw <= currentBalance) {
                    String queryWithdraw = "UPDATE BankInfo SET balance = balance - ? WHERE accno = ?";
                    PreparedStatement pstmtWithdraw = con.prepareStatement(queryWithdraw);
                    pstmtWithdraw.setLong(1, withdraw);
                    pstmtWithdraw.setString(2, accno);
                    pstmtWithdraw.executeUpdate();

                    System.out.println(" " + withdraw + " is withdrawn from your Account");
                    System.out.println("Current Available Balance is Rs  ::" + (currentBalance - withdraw));
                } else {
                    System.out.println("Low Balance");
                    System.out.println("Current Available Balance is Rs  ::" + currentBalance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkBalance() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.println("Enter the Account No:");
            String accno = sc.next();

            String query = "SELECT * FROM BankInfo WHERE accno = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, accno);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Your name is           :: " + rs.getString("name"));
                System.out.println("Account no             :: " + rs.getString("accno"));
                System.out.println("Account type           :: " + rs.getString("acc_type"));
                System.out.println("Balance                :: " + rs.getLong("balance"));
                System.out.println("THANKS FOR BALANCE CHECKING ");
            } else {
                System.out.println("Account not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
