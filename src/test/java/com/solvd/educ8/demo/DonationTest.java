package com.solvd.educ8.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.educ8.demo.gui.pages.Educ8PopUp;
import com.solvd.educ8.demo.gui.pages.SamplePage;
import com.solvd.educ8.demo.utils.User;
import com.zebrunner.agent.core.annotation.TestRailCaseId;
import com.zebrunner.agent.core.registrar.TestRail;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class DonationTest implements IAbstractTest {

    @BeforeSuite
    public void setUp() {
        TestRail.setSuiteId("21");
    }

    @Test
    @TestRailCaseId("67")
    public void makeDonationTest() {
        User userData = User.generateUserData();
        SamplePage samplePage = new SamplePage(getDriver());
        samplePage.open();
        Educ8PopUp educ8PopUp = samplePage.clickDonateButton();
        educ8PopUp.clickStartDonationButton();
        educ8PopUp.submitHowMuchToDonateForm();
        educ8PopUp.submitSetUpForm(userData);
        educ8PopUp.submitVerificationForm();
        Assert.assertTrue(educ8PopUp.isAptTextBoxPresent(),
                "AptField is not present");
//        Next validation steps with donation
//        educ8PopUp.submitReadyToDonateForm(userData);
//        educ8PopUp.submitDonateForm();
//        educ8PopUp.submitAddACreditCardForm(userData);
//        Assert.assertTrue(educ8PopUp.isSuccessTitlePresent(), "Success Title is not present");
    }
}
