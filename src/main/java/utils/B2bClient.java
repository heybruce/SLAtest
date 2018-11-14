package utils;

import com.audatex.axn.classchecker.b2b.calc.CalculateFromXRecordsRequest;
import com.audatex.axn.classchecker.b2b.calc.CalculationService;
import com.audatex.axn.classchecker.b2b.task.GetTaskRequest;
import com.audatex.axn.classchecker.b2b.task.TaskService;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class B2bClient {

    private static Logger logger = LoggerFactory.getLogger(B2bClient.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private CalculationService calculationService;

    public String getTask(String loginId, String password, String taskId, String responseStylesheet, String url) {
        GetTaskRequest req = new GetTaskRequest();
        req.setLoginId(loginId);
        req.setPassword(password);
        req.setTaskId(taskId);
        if(responseStylesheet!=null && !responseStylesheet.isEmpty()) req.setResponseStylesheet(responseStylesheet);
        Document res = taskService.getTask(req);
        logger.error(res.asXML());
        return res.asXML();
    }

    public String getCalculation(String loginId, String password, String xrecord, String url) {

        CalculateFromXRecordsRequest request = new CalculateFromXRecordsRequest();
        request.setLoginId(loginId);
        request.setPassword(password);
        request.setXrecords(xrecord);
        Document response = calculationService.calculateFromXRecords(request);
        logger.error(response.asXML());
        return response.asXML();
    }

}
