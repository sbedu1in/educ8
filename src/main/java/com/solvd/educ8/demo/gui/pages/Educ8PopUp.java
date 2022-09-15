package com.solvd.educ8.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.educ8.demo.utils.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

public class Educ8PopUp extends AbstractPage {

    @FindBy(xpath = ".//button[@class='primary-button']")
    private ExtendedWebElement startDonationButton;

    @FindBy(xpath = ".//div[@class='font:primary most-popular-border']")
    private ExtendedWebElement mostPopularOption;

    @FindBy(xpath = ".//div[@class='font:primary donation-plan']/span[contains(text(), '6')]")
    private ExtendedWebElement sixMonthPlan;

    @FindBy(xpath = ".//button[contains(text(), 'Agree & Continue')]")
    private ExtendedWebElement agreeAndContinueButton;

    @FindBy(xpath = ".//input[@name='first-name']")
    private ExtendedWebElement firstNameTextBox;

    @FindBy(xpath = ".//input[@name='last-name']")
    private ExtendedWebElement lastNameTextBox;

    @FindBy(xpath = ".//input[@name='email-address']")
    private ExtendedWebElement emailTextBox;

    @FindBy(xpath = ".//input[@name='phone-number']")
    private ExtendedWebElement mobilePhoneTextBox;

    @FindBy(xpath = ".//button[@type='submit']")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = ".//a[contains(text(), 'code via')]")
    private ExtendedWebElement getCodeViaEmailLink;

    @FindBy(xpath = ".//input[@id='first-digit']")
    private ExtendedWebElement codeDigitField;

    @FindBy(xpath = ".//div[@class='pac-item']")
    private ExtendedWebElement addressDropDown;

    @FindBy(xpath = ".//input[@placeholder='Enter Your Address']")
    private ExtendedWebElement addressTextBox;

    @FindBy(xpath = ".//input[@placeholder='MM']")
    private ExtendedWebElement birthdayMonth;

    @FindBy(xpath = ".//input[@placeholder='DD']")
    private ExtendedWebElement birthdayDay;

    @FindBy(xpath = ".//input[@placeholder='YYYY']")
    private ExtendedWebElement birthdayYear;

    @FindBy(xpath = ".//input[@id='terms-acceptance']")
    private ExtendedWebElement termsAcceptanceCheckBox;

    @FindBy(xpath = ".//*[text()='Debit Card']")
    private ExtendedWebElement debitCardPayment;

    @FindBy(xpath = ".//input[@name='card-number']")
    private ExtendedWebElement cardNumberTextBox;

    @FindBy(xpath = ".//input[@name='expiration-date']")
    private ExtendedWebElement expirationDateTextBox;

    @FindBy(xpath = ".//input[@name='cvv']")
    private ExtendedWebElement cvvTextBox;

    @FindBy(xpath = ".//input[@name='card-postal-code']")
    private ExtendedWebElement zipTextBox;

    @FindBy(xpath = ".//h1[contains(text(), 'Congratulations ')]")
    private ExtendedWebElement successTitle;

    @FindBy(xpath = ".//input[@placeholder='Number']")
    private ExtendedWebElement aptUnitTextBox;

    public Educ8PopUp(WebDriver driver) {
        super(driver);
    }

    public void clickStartDonationButton() {
        startDonationButton.click();
    }

    public void clickMostPopularOption() {
        mostPopularOption.click();
    }

    public void clickSixMonthPlan() {
        sixMonthPlan.click();
    }

    public void clickAddressDropDown() {
        addressDropDown.click();
    }

    public void typeInAddressTextBox(String address) {
        addressTextBox.type(address);
    }

    public void clickAgreeAndContinue() {
        agreeAndContinueButton.click();
    }

    public void typeFirstName(String firstName) {
        firstNameTextBox.type(firstName);
    }

    public void typeLastName(String lastName) {
        lastNameTextBox.type(lastName);
    }

    public void typeEmail(String email) {
        emailTextBox.type(email);
    }

    public void typeMobileNumber(String mobileNumber) {
        mobilePhoneTextBox.click();
        mobilePhoneTextBox.getElement().sendKeys(mobileNumber);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickGetCodeViaEmailLink() {
        getCodeViaEmailLink.click();
    }

    public void typeCode(String code) {
        codeDigitField.click();
        codeDigitField.type(code);
    }

    public void typeBirthDay(String date) {
        String[] splitDate = date.split("\\/");
        birthdayYear.type(splitDate[0]);
        birthdayMonth.type(splitDate[1]);
        birthdayDay.type((splitDate[2]));
    }

    public void checkTermsAcceptanceCheckBox() {
        termsAcceptanceCheckBox.check();
    }

    public void typeCardNumber(String cardNumber) {
        cardNumberTextBox.type(cardNumber);
    }

    public void typeExpirationDate(String expirationDate) {
        expirationDateTextBox.type(expirationDate);
    }

    public void typeCVV(String cvv) {
        cvvTextBox.type(cvv);
    }

    public void typeZip(String zip) {
        zipTextBox.type(zip);
    }

    public void submitAddACreditCardForm(String cardNumber, String expirationDate, String cvv, String zip) {
        selectDebitCardPaymentMethod();
        typeCardNumber(cardNumber);
        typeExpirationDate(expirationDate);
        typeCVV(cvv);
        typeZip(zip);
        clickSubmitButton();
    }

    public void selectDebitCardPaymentMethod() {
        debitCardPayment.click();
    }

    public void submitHowMuchToDonateForm() {
        clickMostPopularOption();
        clickSixMonthPlan();
        clickAgreeAndContinue();
    }

    public void submitSetUpForm(User user) {
        typeFirstName(user.getFirstName());
        typeLastName(user.getLastName());
        typeEmail(user.getEmail());
        typeMobileNumber(user.getMobileNumber());
        clickSubmitButton();
    }

    public void submitVerificationForm() {
        Date date = new Date();
        date.setTime(date.getTime() - 5000);
        clickGetCodeViaEmailLink();
        UserData user = new UserData(R.TESTDATA.get("email"), CryptoUtil.decrypt(R.TESTDATA.get("password")));
        EmailMsg msg = EmailManager.readEmail(getDriver(), date, user, "Verify your e-mail address", "This is your verification code");
        String code = msg.getContent().strip().split("\\s")[0];
        typeCode(code);
    }

    public void submitReadyToDonateForm(String address, String birthDay) {
        typeInAddressTextBox(address);
        clickAddressDropDown();
        typeBirthDay(birthDay);
        clickSubmitButton();
    }

    public void submitDonateForm() {
        checkTermsAcceptanceCheckBox();
        clickSubmitButton();
    }

    public boolean isSuccessTitlePresent() {
        return successTitle.isElementPresent();
    }

    public boolean isAptTextBoxPresent() {
        return aptUnitTextBox.isElementPresent();
    }
}
