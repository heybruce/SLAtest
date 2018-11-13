package utils.slack;

import org.json.simple.JSONObject;
import org.testng.ITestResult;

public class SlackMessageBuilder {

    String alertMessage;

    public void composeAlertMessage(ITestResult result) {
        JSONObject message = new JSONObject();
        message.put("text", "Test failed 3 times in a row. Please verify for potential issue.\nTest name: "
                + result.getMethod().getMethodName()
                + "\nEnvironment: " + result.getTestContext().getCurrentXmlTest().getLocalParameters().get("env")
                + "\nBrowser: " + result.getTestContext().getCurrentXmlTest().getLocalParameters().get("browser"));

        alertMessage = message.toJSONString();
    }

    public String getAlertMessage() {
        return alertMessage;
    }
}
