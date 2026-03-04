package org.example.testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FormTest {
    private static final Logger logger = LogManager.getLogger(FormTest.class);

    static public WebDriver driver;
    static public String URL = "https://demoqa.com/";
    static public String FIRST_NAME = "Jenia";
    static public String LAST_NAME = "Grigorita";
    static public String EMAIL = "grigorita@gmail.com";
    static public String GENDER = "Male";
    static public String NUMBER = "0695854360";
    static public String DATE = "31 Aug 2007";
    static public String SUBJECT = "Romanian";
    static public String STATE = "Haryana";
    static public String CITY = "Karnal";
    static public String HOBBY = "Reading";

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        logger.info("Start before method!");
//        driver = Driver.getAutoLocalDriver();
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest() {
//        System.out.println("Start test");
        logger.info("Start test!");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.clickForms();
        formPom.clickPracticeForm();
        formPom.closeAdvert();
        formPom.setFirstName(FIRST_NAME);
        logger.info("Set first name");

        formPom.setLastName(LAST_NAME);
        logger.info("Set last name");

        formPom.setEmail(EMAIL);
        logger.info("Set email");

        formPom.setGender(GENDER);
        logger.info("Set gender");

        formPom.setNumber(NUMBER);
        logger.info("Set number");

        formPom.setDate(DATE);
        logger.info("Set date");

        formPom.setHobby(HOBBY);
        logger.info("Set hobby");

        formPom.setSubject(SUBJECT);
        logger.info("Set subject");

        formPom.pause(5000);
        formPom.setState(STATE);
        logger.info("Set state");

        formPom.setCity(CITY);
        logger.info("Set cuty");

        formPom.clickSubmit();
        formPom.pause(5000);

        String actualName = formPom.getTableDataByLabel("Student Name");
        Assert.assertEquals(actualName, FIRST_NAME + " " + LAST_NAME);
//        System.out.println("Finish test");
        logger.info("Finish test!");
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Start after method");
        driver.quit();
    }
}
