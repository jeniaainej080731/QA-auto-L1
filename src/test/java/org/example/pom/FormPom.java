package org.example.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class FormPom {

    static public WebDriver driver;
    static public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElement dateOfBirthInput;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectsInput;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement submit;

    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public String getTableDataByLabel(String labelParam) {
        WebElement data = driver.findElement(By.xpath("//table//*[text()='" + labelParam  + "']/../*[2]"));
        return data.getText();
    }

    public void clickSubmit() {
        submit.click();
    }

    @Step("Set City")
    public void setCity(String cityParam) {
        takeScreenshot("Before city");
        city.click();
        WebElement ddSCity = city.findElement(By.xpath("//*[text()='" + cityParam  + "']"));
        ddSCity.click();
        takeScreenshot("After city");
    }

    @Step("Set State")
    public void setState(String stateParam) {
        takeScreenshot("Before state");
        state.click();
        WebElement ddState = state.findElement(By.xpath("//*[text()='" + stateParam  + "']"));
//        Utils.explicitWait(driver, ExpectedConditions.elementToBeClickable(ddState), 10);
//        pause(2000);

        ddState.click();
        takeScreenshot("After state");
    }

    @Step("Set Hobby")
    public void setHobby(String hobbyParam) {
        takeScreenshot("Before hobby");
        WebElement hobby = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbyParam + "']/../input"));
        hobby.sendKeys(" ");
        takeScreenshot("After hobby");
    }

    @Step("Set Subject")
    public void setSubject(String subjectParam) {
        takeScreenshot("Before subject");
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
        takeScreenshot("After subject");
    }

    @Step("Set Date")
    public void setDate(String dateParam) {
        takeScreenshot("Before date");
        dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        dateOfBirthInput.sendKeys(dateParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        takeScreenshot("After date");
    }

    @Step("Set Number")
    public void setNumber(String numberParam) {
        takeScreenshot("Before number");
        userNumber.clear();
        userNumber.sendKeys(numberParam);
        takeScreenshot("After number");
    }

    @Step("Set Gender")
    public void setGender(String genderParam) {
        takeScreenshot("Before gender");
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
        takeScreenshot("After gender");
    }

    @Step("Set Email")
    public void setEmail(String emailParam) {
        takeScreenshot("Before email");
        userEmail.clear();
        userEmail.sendKeys(emailParam);
        takeScreenshot("After email");
    }

    @Step("Set Last Name")
    public void setLastName(String lastNameParam) {
        takeScreenshot("Before last name");
        lastName.clear();
        lastName.sendKeys(lastNameParam);
        takeScreenshot("After last name");
    }

    @Step("Set First Name")
    public void setFirstName(String firstNameParam) {
        takeScreenshot("Before first name");
        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("After first name");
    }

    public void clickPracticeForm() {
        Utils.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForm), 10);
        practiceForm.click();
    }

    public void clickForms() {
        forms.click();
    }

    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }

    private void takeScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", e.toString());
        }
    }
}
