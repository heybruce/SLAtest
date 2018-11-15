package utils.listeners;

import org.apache.commons.configuration2.Configuration;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.UtilitiesManager;
import utils.slack.SlackMessageBuilder;
import utils.slack.WebAPIClient;

import java.io.IOException;

public class TestListener extends TestListenerAdapter {
    //Slack
    public static Configuration slackProp = UtilitiesManager.setPropertiesFile("slack.tpe.server.properties");
    private static final String WEBHOOK_URL = slackProp.getString("webhook_url");
    public WebAPIClient webAPIClient = new WebAPIClient();
    public SlackMessageBuilder slackMessageBuilder = new SlackMessageBuilder();

    @Override
    public synchronized void onStart(ITestContext context) {
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        //Send Slack notification
        slackMessageBuilder.composeAlertMessage(result);
        try {
            String response = webAPIClient.post(WEBHOOK_URL, slackMessageBuilder.getAlertMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
    }
}