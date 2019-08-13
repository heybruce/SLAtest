package utils.slack;

import org.json.simple.JSONObject;
import org.testng.ITestResult;

import static utils.listeners.Retry.maxTry;

public class SlackMessageBuilder {

    private String alertMessage;

    public void composeAlertMessage(ITestResult result) {
        JSONObject message = new JSONObject();
        message.put("text", "Test failed " + (maxTry + 1) + " times in a row. Please verify for potential issue.\nTest name: "
                + result.getMethod().getMethodName()
                + "\nEnvironment: " + result.getTestContext().getCurrentXmlTest().getLocalParameters().get("env")
                + "\nCountry: " + result.getTestContext().getCurrentXmlTest().getLocalParameters().get("country")
                + "\nBrowser: " + result.getTestContext().getCurrentXmlTest().getLocalParameters().get("browser"));

        alertMessage = message.toJSONString();
    }

    public String getAlertMessage() {
        return alertMessage;
    }
}
