/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Bank;

/**
 *
 * @author Sahru
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program_Bank {
    
    private static int numAccounts = 0;
    private static Account[] accounts = new Account[100];

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println("+===================================================+");
        System.out.println("+===================================================+");
        System.out.println("+                                                   +");
        System.out.println("+===== Selamat Datang di Aplikasi Bank SPWS 26 =====+");
        System.out.println("+                                                   +");
        System.out.println("+===================================================+");
        System.out.println("+===================================================+");
        enter();
        do {
            System.out.println("+======   Bank Menu   ======+");
            System.out.println("1. Registrasi Akun");
            System.out.println("2. Kirim Uang ke Akun Lain");
            System.out.println("3. Deposit Uang");
            System.out.println("4. Tarik Uang");
            System.out.println("5. Cek Saldo");
            System.out.println("6. Bayar Tagihan");
            System.out.println("7. Keluar");
            System.out.print("Cik Nak Ape: ");
            choice = input.nextInt();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    transferMoney();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    payBill();
                    break;
                case 7:
                    System.out.println("Terima kasih telah menggunakan layanan Bank kami.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih menu lain.");
            }
            
            System.out.println();
        } while (choice != 7);
    }
    
    public static void enter(){
        System.out.println();
    }
    
    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    
    
    public static void createAccount() {
        Scanner input = new Scanner(System.in);
        enter();
        enter();
        System.out.println("+======   Menu Registrasi Akun   ======+\n");
        System.out.print("Masukkan nama pengguna: ");
        String name = input.nextLine();
        System.out.print("Masukkan saldo awal: ");
        double balance = input.nextDouble();
        
        Account account = new Account(name, balance);
        accounts[numAccounts] = account;
        numAccounts++;
        
        LocalDateTime registrationTime = account.getRegistrationDate();
        System.out.println("Akun berhasil dibuat dengan nomor: " + account.getAccountNumber());
        System.out.println("Waktu registrasi: " + formatDateTime(registrationTime));
    }
    
    public static void transferMoney() {
        Scanner input = new Scanner(System.in);
        enter();
        enter();
        System.out.println("+======   Menu Transfer   ======+\n");
        System.out.print("Masukkan nomor akun pengirim: ");
        int fromAccount = input.nextInt();
        System.out.print("Masukkan nomor akun penerima: ");
        int toAccount = input.nextInt();
        System.out.print("Masukkan jumlah yang akan dikirim: ");
        double amount = input.nextDouble();
        
        Account sender = getAccount(fromAccount);
        Account receiver = getAccount(toAccount);
        
        if (sender == null) {
            System.out.println("Nomor akun pengirim tidak ditemukan.");
            return;
        }
        
        if (receiver == null) {
            System.out.println("Nomor akun penerima tidak ditemukan.");
            return;
        }
        
        if (sender.getBalance() < amount) {
            System.out.println("Saldo tidak mencukupi.");
            return;
        }
        
        sender.withdraw(amount);
        receiver.deposit(amount);
        
        System.out.println("Transaksi berhasil.");
    }
    
    public static void deposit() {
        Scanner input = new Scanner(System.in);
        enter();
        enter();
        System.out.println("+======   Menu Deposit   ======+\n");
        System.out.print("Masukkan nomor akun: ");
        int accountNumber = input.nextInt();
        System.out.print("Masukkan jumlah yang akan disimpan: ");
        double amount = input.nextDouble();
        
        Account account = getAccount(accountNumber);
        if (account == null) {
            System.out.println("Nomor akun tidak ditemukan.");
            return;
        }
        
        account.deposit(amount);
        
        System.out.println("Penyetoran berhasil.");
    }
    
    public static void withdraw() {
    Scanner input = new Scanner(System.in);
    enter();
    enter();
    System.out.println("+======   Menu Tarik Uang   ======+\n");
    System.out.print("Masukkan nomor akun: ");
    int accountNumber = input.nextInt();
    System.out.print("Masukkan jumlah yang akan ditarik: ");
    double amount = input.nextDouble();
    
        Account account = getAccount(accountNumber);
    if (account == null) {
        System.out.println("Nomor akun tidak ditemukan.");
        return;
    }
    
    if (account.getBalance() < amount) {
        System.out.println("Saldo tidak mencukupi.");
        return;
    }
    
    account.withdraw(amount);
    
    System.out.println("Penarikan berhasil.");
}

    public static void checkBalance() {
        Scanner input = new Scanner(System.in);
        enter();
        enter();
        System.out.println("+======   Menu Cek Saldo   ======+\n");
        System.out.print("Masukkan nomor akun: ");
        int accountNumber = input.nextInt();

        Account account = getAccount(accountNumber);
        if (account == null) {
            System.out.println("Nomor akun tidak ditemukan.");
            return;
        }

        System.out.println("Saldo saat ini: " + account.getBalance());
    }
    
    public static void payBill() {
    Scanner input = new Scanner(System.in);
    enter();
    enter();
    System.out.println("+======   Menu Pembayaran Tagihan   ======+\n");
    System.out.print("Masukkan nomor akun: ");
    int accountNumber = input.nextInt();
    input.nextLine(); 

    Account account = getAccount(accountNumber);
    if (account == null) {
        System.out.println("Nomor akun tidak ditemukan.");
        return;
    }

    System.out.print("Masukkan jumlah tagihan: ");
    double billAmount = input.nextDouble();
    input.nextLine();

    if (account.getBalance() < billAmount) {
        System.out.println("Saldo tidak mencukupi untuk membayar tagihan.");
        return;
    }

    System.out.print("Masukkan jenis tagihan: ");
    String billDescription = input.nextLine();

    account.withdraw(billAmount);
    System.out.println("Tagihan berhasil dibayar.");
    System.out.println("Jenis tagihan: " + billDescription);
    System.out.println("Jumlah tagihan: " + billAmount);
}


    public static Account getAccount(int accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }
}