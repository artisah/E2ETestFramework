package com.dasb;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.dasb.base.Base;
import com.dasb.base.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    ExtentTest test;
    ExtentReports extent =  ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
         threadLocal.set(test);

    }

    public void onTestSuccess(ITestResult result) {
          threadLocal.get().log(Status.PASS, "Test Passed");
       // test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail(result.getThrowable());
       // test.fail(result.getThrowable());
        WebDriver driver = null;

        String testMethodname = result.getMethod().getMethodName();

        try {
            driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception  e) {
            e.printStackTrace();
        }

        try {
            String imagePath = captureScreenshot(testMethodname, driver);
            threadLocal.get().addScreenCaptureFromPath(imagePath);
           // test.addScreenCaptureFromPath(imagePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        extent.flush();

    }
}
