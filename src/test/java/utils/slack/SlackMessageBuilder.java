package utils.slack;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utils.UtilitiesManager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlackMessageBuilder {

    String alertMessage;
    private JSONObject jenkinsInfo = new JSONObject();

    public void composeAlertMessage(ITestResult result) {
        JSONObject message = new JSONObject();
        message.put("text", result.getMethod().getMethodName() + " - " + String.valueOf(result.isSuccess()));
        alertMessage = message.toJSONString();
    }

    public String getAlertMessage() {
        return alertMessage;
    }
}
