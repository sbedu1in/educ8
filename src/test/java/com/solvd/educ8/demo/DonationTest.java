package com.solvd.educ8.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.educ8.demo.gui.pages.Educ8Frame;
import com.solvd.educ8.demo.gui.pages.SamplePage;
import com.solvd.educ8.demo.utils.User;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DonationTest implements IAbstractTest {

    @Test
    public void makeDonationTest() {
        User user = new User();
        user.generateUserData();
        SamplePage samplePage = new SamplePage(getDriver());
        samplePage.open();
        Educ8Frame educ8Frame = samplePage.clickDonateButton();
        educ8Frame.clickStartDonationButton();
        educ8Frame.submitHowMuchToDonateForm();
        educ8Frame.submitSetUpForm(user.getFirstName(), user.getLastName(), user.getEmail(), user.getMobileNumber());
        educ8Frame.submitVerificationForm();
        educ8Frame.submitReadyToDonateForm(user.getAddress(), user.getBirthDay());
        educ8Frame.submitDonateForm();
        educ8Frame.submitAddACreditCardForm(user.getCardNumber(), user.getExpirationDate(), user.getCvv(), user.getZip());
        Assert.assertTrue(educ8Frame.isSuccessTitlePresent(), "Success Title is not present");
    }
}
