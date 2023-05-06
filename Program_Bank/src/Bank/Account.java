/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bank;

import java.time.LocalDateTime;

/**
 *
 * @author TUF GAMING 15
 */
public class Account {
    private static int numAccounts = 0;
    private int accountNumber;
    private String name;
    private double balance;
    private LocalDateTime registrationDate;
    

    public Account(String name, double balance) {
        this.accountNumber = ++numAccounts;
        this.name = name;
        this.balance = balance;
        this.registrationDate = LocalDateTime.now();
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("Saldo tidak mencukupi.");
        } else {
            this.balance -= amount;
        }
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}    