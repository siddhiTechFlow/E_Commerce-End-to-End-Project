package org.listeners.qa;

import org.reports.qa.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

public class TestListeners implements ITestListener {

    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult res) {
        test = ExtentManager.getReportInstance()
                .createTest(res.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult res) {
        test.pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult res) {
        test.fail("Test failed");
        test.fail(res.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult res) {
        test.skip("Test skipped");
        test.skip(res.getThrowable());
    }

    @Override
    public void onFinish(ITestContext con) {
        ExtentManager.getReportInstance().flush();
    }
}