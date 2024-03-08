package app.demo.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Listens to the tesNG tests and perform provided actions by methods
 **/
public class TestListener extends TestListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult tr) {
        log.info(tr.getName().concat("--Test execution started"));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error(tr.getName().concat("--Test failed"));
        log.error(String.valueOf(tr.getThrowable().getMessage()));
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.info(tr.getName().concat("--Test skipped"));
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info(tr.getName().concat("--Test successfully executed"));
    }

}