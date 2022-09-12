package com.solvd.educ8.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class Educ8Frame extends AbstractPage {

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
    private ExtendedWebElement letsContinueButton;

    @FindBy(xpath = ".//a[contains(text(), 'code via')]")
    private ExtendedWebElement getCodeViaEmailLink;

    @FindBy(xpath = ".//input[@id='first-digit']")
    private ExtendedWebElement codeDigitField;

    public Educ8Frame(WebDriver driver) {
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

    public void clickLetsContinueButton() {
        letsContinueButton.click();
    }

    public void clickGetCodeViaEmailLink() {
        getCodeViaEmailLink.click();
    }

    public void typeCode(String code) {
        codeDigitField.click();
        codeDigitField.type(code);
    }
}
