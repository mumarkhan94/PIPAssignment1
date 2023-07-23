package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
int count=0;
int retrycount=1;
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
//		This method is use to rerun the fail testcase.
		while(count<retrycount)
		{
			count++;
			return true;
		}
		return false;
	}
	

}
