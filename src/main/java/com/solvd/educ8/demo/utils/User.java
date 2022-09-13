package com.solvd.educ8.demo.utils;

import com.qaprosoft.carina.core.foundation.utils.R;

import java.util.Random;

public class User {

    private String firstName;

    private String lastName;

    private String cardNumber;

    private String expirationDate;

    private String address;

    private String cvv;

    private String zip;

    private String birthDay;

    private String mobileNumber;

    private String email;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getAddress() {
        return address;
    }

    public String getCvv() {
        return cvv;
    }

    public String getZip() {
        return zip;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void generateUserData() {
        firstName = R.TESTDATA.get("first_name");
        lastName = R.TESTDATA.get("last_name");
        cardNumber = R.TESTDATA.get("debit_card");
        expirationDate = R.TESTDATA.get("expiration_date");
        address = R.TESTDATA.get("address");
        cvv = R.TESTDATA.get("cvv");
        zip = R.TESTDATA.get("zip_code");
        birthDay = R.TESTDATA.get("birthday");
        Random random = new Random();
        String rand = String.format("%04d", random.ints(20, 200).findFirst().getAsInt());
        String mail = R.TESTDATA.get("email");
        email = mail.substring(0, mail.indexOf("@")) + "+" + rand + mail.substring(mail.indexOf("@"));
        mobileNumber = R.TESTDATA.get("mobile_number") + rand;
    }
}
