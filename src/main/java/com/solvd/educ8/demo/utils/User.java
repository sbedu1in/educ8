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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public static User generateUserData() {
        User user = new User();
        user.setFirstName(R.TESTDATA.get("first_name"));
        user.setLastName(R.TESTDATA.get("last_name"));
        user.setCardNumber(R.TESTDATA.get("debit_card"));
        user.setExpirationDate(R.TESTDATA.get("expiration_date"));
        user.setAddress(R.TESTDATA.get("address"));
        user.setCvv(R.TESTDATA.get("cvv"));
        user.setZip(R.TESTDATA.get("zip_code"));
        user.setBirthDay(R.TESTDATA.get("birthday"));
        Random random = new Random();
        String rand = String.format("%04d", random.ints(400, 9000).findFirst().getAsInt());
        String mail = R.TESTDATA.get("email");
        user.setEmail(mail.substring(0, mail.indexOf("@")) + "+" + rand + mail.substring(mail.indexOf("@")));
        user.setMobileNumber(R.TESTDATA.get("mobile_number") + rand);
        return user;
    }
}
