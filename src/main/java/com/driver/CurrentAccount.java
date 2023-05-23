package com.driver;

import java.util.Collections;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only


    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);
        if(balance < 5000){
            throw new InsufficientBalance();
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        char a[]=tradeLicenseId.toCharArray();
        boolean b=true;
        for(int i=1;i<a.length;i++){
            if(a[i-1]==a[i]){
                b=false;
                break;
            }
        }
        if(b)return;

        int f[]=new int[26];
        for(char c:a)f[c-'A']++;

        PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->{return y[1]-x[1];});
        for(int i=0;i<26;i++){
            if(f[i]!=0) {
                pq.add(new int[]{i + 'A', f[i]});
            }
        }

        int last[]={0,0};

        for(int i=a.length-1;i>=0;i--){
            if(pq.isEmpty()){
                new ValidLicenseCanNotBeGenerated("error");
                return;
            }
            int curr[]=pq.poll();
            a[i]=(char)(curr[0]);

            if(last[1]!=0){
                pq.add(last);
            }
            curr[1]--;
            last=curr;
        }

        this.tradeLicenseId = new String(a);
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }
}
