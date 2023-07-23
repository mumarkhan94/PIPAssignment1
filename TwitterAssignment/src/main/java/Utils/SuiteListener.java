package Utils;

import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;

public class SuiteListener implements ITestListener, IAnnotationTransformer{
	// This class is used for take the screen shot if the test case is fail.
	// If test case is fail then retry the test case.
//
//	 public void onTestFailure(ITestResult result) {
//		    // If test case will fail then this method will execute and take the screenshot.
//		 String filename = System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+result.getMethod().getMethodName();
//		 File f1= ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
//		 try {
//			FileUtils.copyFile(f1, new File(filename+ " .png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	 }
//
//	 public void onTestSuccess(ITestResult result) {
//		    // If test case will Pass then this method will execute and take the screenshot.
//				 String filename = System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+result.getMethod().getMethodName();
//				 File f1= ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
//				 try {
//					FileUtils.copyFile(f1, new File(filename+ " .png"));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		  }
//
//	  public void transform(
//		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
//		  // It will call the retry analyzer class.
//		  annotation.setRetryAnalyzer(RetryAnalyzer.class);
//
//		  }
	 
	 
}
