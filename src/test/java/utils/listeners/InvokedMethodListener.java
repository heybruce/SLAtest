package utils.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import utils.webdrivers.WebDriverFactory;

public class InvokedMethodListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) { }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) { }
}
