package com.driver;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(9*digits < sum){
            throw new AccountNumberCanNotBeGenerated();
        }

        char c[] = new char[digits];
        Arrays.fill(c,'0');
        c[0]='1';
        sum--;

        for(int i=digits-1;i>=0;i--){
            if(i==0){
                sum++;
            }
            if(sum-9>=0){
                c[i]='9';
                sum-=9;
            }else{
                c[i]=(char)(sum+'0');
                sum=0;
                break;
            }
        }

        return new String(c);
    }

    public void deposit(double amount) {
        //add amount to balance
        balance += amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(balance-amount < minBalance){
            throw new InsufficientBalance();
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}