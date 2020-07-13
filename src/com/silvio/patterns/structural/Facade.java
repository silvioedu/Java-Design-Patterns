package com.silvio.patterns.structural;

import java.awt.*;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

//design the interface
abstract interface IAccount {
    abstract int getAccountNumber();
    abstract BigDecimal getAccountBalance();
    abstract void transfer(IAccount dest, BigDecimal amount);
    abstract void deposit(BigDecimal amount);
}

//implement the interface with one or more classes
class Investment implements IAccount {

    private int accountNumber;
    private BigDecimal amount;

    public Investment(BigDecimal amount){
        this.createNewAccount(amount);
        System.out.println("InvestimentAccound created: "+ this.getAccountNumber());
    }

    private int createNewAccount(BigDecimal amount) {
        this.accountNumber = new Random().nextInt(Short.MAX_VALUE);
        this.amount = amount;
        return accountNumber;
    }

    @Override
    public void transfer(IAccount from, BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
        from.deposit(amount);
    }

    @Override
    public void deposit(BigDecimal amount) { this.amount = this.amount.add(amount); }

    @Override
    public int getAccountNumber() { return this.accountNumber; }

    @Override
    public BigDecimal getAccountBalance() { return this.amount; }

    @Override
    public String toString() {
        return "Investment{" +
                "accountNumber=" + accountNumber +
                ", amount=" + amount +
                '}';
    }
}

//create the facade class and wrap the classes that implement the interface
class BankService {

    private Hashtable<Integer, IAccount> bankAccounts;

    public BankService() {
        this.bankAccounts = new Hashtable<Integer, IAccount>();
    }

    public int createNewAccount(String type, BigDecimal initAmount) {
        IAccount newAccount = type.equalsIgnoreCase("investment")
                ? new Investment(initAmount)
                : null;

        if (newAccount != null) {
            this.bankAccounts.put(newAccount.getAccountNumber(), newAccount);
            return newAccount.getAccountNumber();
        }
        return -1;
    }

    public void transferMoney(int to, int from, BigDecimal amount){
        IAccount toAccount = this.bankAccounts.get(to);
        IAccount fromAccount = this.bankAccounts.get(from);
        fromAccount.transfer(toAccount, amount);
        System.out.println("Transfer completed");
        System.out.println("FROM:");
        System.out.println(fromAccount.toString());
        System.out.println("TO:");
        System.out.println(toAccount.toString());
    }
}

//use the facade class to access the subsystem
class FacadeExample {
    public static void main(String[] args) {

        final String accountType = "investment";

        BankService myBankService = new BankService();

        System.out.println("-----");
        int johnAccount = myBankService.createNewAccount(accountType, new BigDecimal(500.00));
        int maryAccount = myBankService.createNewAccount(accountType, new BigDecimal(2300.00));
        System.out.println("-----");

        myBankService.transferMoney(johnAccount,maryAccount,new BigDecimal(750.00));
    }
}

