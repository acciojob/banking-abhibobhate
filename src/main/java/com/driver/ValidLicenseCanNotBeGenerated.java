package com.driver;

public class ValidLicenseCanNotBeGenerated extends RuntimeException{
    ValidLicenseCanNotBeGenerated(String s){
        super(s);
    }
}
