package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import datamodel.TestResult;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


public class RestManager {
    private final static Logger logger = Logger.getLogger(RestManager.class);

    public static final String REST_SERVICE_URI ="http://10.29.25.75/monitor/health";

    public static void sendTestResult(TestResult testResult) {

        ObjectMapper mapper = new ObjectMapper().registerModule(new JSR310Module());
        RestTemplate restTemplate = new RestTemplate();
        String result;
        try {
            result = mapper.writeValueAsString(testResult);
            HttpEntity<String> request = new HttpEntity<>(result);
            restTemplate.postForLocation(REST_SERVICE_URI, request);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
