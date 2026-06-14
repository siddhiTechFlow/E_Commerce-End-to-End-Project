package org.reports.qa;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String reportPath = System.getProperty("user.dir")
                    + "/reports/API_Automation_Report_" + timeStamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("DummyJSON API Automation Report");
            sparkReporter.config().setReportName("REST Assured API Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "DummyJSON API Testing");
            extent.setSystemInfo("Tool", "REST Assured");
            extent.setSystemInfo("Framework", "TestNG + Maven");
            extent.setSystemInfo("Tester", "Siddhi More");
        }

        return extent;
    }
}
