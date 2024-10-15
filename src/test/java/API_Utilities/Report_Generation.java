package API_Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Report_Generation extends TestListenerAdapter {
	public ExtentSparkReporter SparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
@Override
public void onStart(ITestContext testContext) {
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//Time Stamp
	String reportName=testContext.getName()+"-"+timeStamp+".html";

    SparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+reportName); //Location specify

	extent=new ExtentReports();

	extent.setSystemInfo("Host Name", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Imtha");
	extent.setSystemInfo("Operating system", System.getProperty("os.name"));
	extent.setSystemInfo("User Name", System.getProperty("user.name"));

	

	SparkReporter.config().setDocumentTitle("DCI Site Test Report"); //Title of Report
	SparkReporter.config().setReportName("DCI Site Automation Execution"); //Name of the Report
	SparkReporter.config().setTheme(Theme.STANDARD); //Background Theme of Report
	extent.attachReporter(SparkReporter);
	super.onStart(testContext);
}

	
@Override
public void onTestSuccess(ITestResult tr) {
	logger=extent.createTest(tr.getName()).assignAuthor("Imtha").assignCategory("functional test").assignDevice("Chrome"); //Create new entry in the report
	logger.assignCategory(tr.getMethod().getGroups());
	logger.createNode(tr.getName());
	logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information

	super.onTestSuccess(tr);
}

@Override
public void onTestFailure(ITestResult tr) {
	logger=extent.createTest(tr.getName()).assignAuthor("Imtha").assignCategory("functional test").assignDevice("Chrome");//create new entry in the report
	logger.assignCategory(tr.getMethod().getGroups());
	logger.createNode(tr.getName());
	logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the failed information
	logger.log(Status.FAIL,tr.getThrowable().getMessage());
	String screetshotpath=System.getProperty("user.dir")+"\\screenshots\\"+tr.getName()+".png";
	File f=new File(screetshotpath);
	
	if (f.exists()) {
		{try {
			logger.fail("screenshot is below:"+logger.addScreenCaptureFromPath(screetshotpath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		}
	super.onTestFailure(tr);
}
	
@Override
   public void onTestSkipped(ITestResult tr) {
	logger=extent.createTest(tr.getName());//create new entry in the report
	logger.assignCategory(tr.getMethod().getGroups());
	logger.createNode(tr.getName());
	logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	logger.log(Status.SKIP,tr.getThrowable().getMessage());
	super.onTestSkipped(tr);
}
	
@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();

		super.onFinish(testContext);
	}
}
