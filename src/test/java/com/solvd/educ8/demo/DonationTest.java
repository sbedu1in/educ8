package com.solvd.educ8.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.solvd.educ8.demo.gui.pages.Educ8Frame;
import com.solvd.educ8.demo.gui.pages.SamplePage;
import com.solvd.educ8.demo.utils.CryptoUtil;
import com.solvd.educ8.demo.utils.EmailManager;
import com.solvd.educ8.demo.utils.EmailMsg;
import com.solvd.educ8.demo.utils.UserData;
import org.testng.annotations.Test;

import java.util.Date;

public class DonationTest implements IAbstractTest {

    @Test
    public void makeDonationTest() {
        Date date = new Date();
        SamplePage samplePage = new SamplePage(getDriver());
        samplePage.open();
        samplePage.clickDonateButton();
        samplePage.switchToFrame();
        Educ8Frame educ8Frame = new Educ8Frame(getDriver());
        educ8Frame.clickStartDonationButton();
        educ8Frame.clickMostPopularOption();
        educ8Frame.clickSixMonthPlan();
        educ8Frame.clickAgreeAndContinue();
        educ8Frame.typeFirstName(R.TESTDATA.get("first_name"));
        educ8Frame.typeLastName(R.TESTDATA.get("last_name"));
        educ8Frame.typeEmail(R.TESTDATA.get("email"));
        educ8Frame.typeMobileNumber(R.TESTDATA.get("mobile_number"));
        educ8Frame.clickLetsContinueButton();
        educ8Frame.clickGetCodeViaEmailLink();
        UserData user = new UserData(R.TESTDATA.get("email"), CryptoUtil.decrypt(R.TESTDATA.get("password")));
        EmailMsg msg = EmailManager.readEmail(getDriver(), date, user, "Verify your e-mail address", "This is your verification code");
        String code = msg.getContent().strip().split("\\s")[0];
        educ8Frame.typeCode(code);
    }

}
