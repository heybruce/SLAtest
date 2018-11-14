package utils;

import com.audatex.axn.classchecker.b2b.calc.CalculateFromXRecordsRequest;
import com.audatex.axn.classchecker.b2b.calc.CalculationService;
import com.audatex.axn.classchecker.b2b.task.GetTaskRequest;
import com.audatex.axn.classchecker.b2b.task.TaskService;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class B2bClient {

    private static Logger logger = LoggerFactory.getLogger(B2bClient.class);

    @Autowired
    private BeanFactory beanFactory;

    public String getTask(String loginId, String password, String taskId, String responseStylesheet, String url) {
        GetTaskRequest req = new GetTaskRequest();
        req.setLoginId(loginId);
        req.setPassword(password);
        req.setTaskId(taskId);
        if(responseStylesheet!=null && !responseStylesheet.isEmpty()) req.setResponseStylesheet(responseStylesheet);
        TaskService taskService = beanFactory.getBean(TaskService.class, url);
        Document res = taskService.getTask(req);
        logger.error(res.asXML());
        return res.asXML();
    }

    public String getCalculation(String xrecord, String url) {

        CalculateFromXRecordsRequest req = new CalculateFromXRecordsRequest();

        req.setXrecords(xrecord);
        CalculationService calculationService = beanFactory.getBean(CalculationService.class, url);
        Document res = calculationService.calculateFromXRecords(req);
        logger.error(res.asXML());
        return res.asXML();
    }

}
