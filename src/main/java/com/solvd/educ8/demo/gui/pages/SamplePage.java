package com.solvd.educ8.demo.gui.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.*;

public class SamplePage extends AbstractPage {

    public SamplePage(WebDriver driver) {
        super(driver);
    }

    public void clickDonateButton() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement donateButton = (WebElement) js
                .executeScript("return document.querySelector('bgenerous-button')" +
                        ".shadowRoot.querySelector('span')");
        donateButton.click();
    }

    public void switchToFrame() {
        WebElement iframe = getDriver().findElement(By.tagName("iframe"));
        getDriver().switchTo().frame(iframe);
    }

}
