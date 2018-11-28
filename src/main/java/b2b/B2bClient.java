package b2b;

import com.audatex.axn.classchecker.b2b.calc.CalculateFromXRecordsRequest;
import com.audatex.axn.classchecker.b2b.task.GetTaskRequest;
import com.audatex.axn.classchecker.b2b.vehicle.VinQueryRequest;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class B2bClient {

    private static Logger logger = LoggerFactory.getLogger(B2bClient.class);

    @Autowired
    private B2bServiceProvider b2bServiceProvider;

    public String getTask(String loginId, String password, String taskId, String responseStylesheet, String url) {
        GetTaskRequest req = new GetTaskRequest();
        req.setLoginId(loginId);
        req.setPassword(password);
        req.setTaskId(taskId);
//        if(responseStylesheet!=null && !responseStylesheet.isEmpty()) req.setResponseStylesheet(responseStylesheet);
        Document res = b2bServiceProvider.getTaskService(url).getTask(req);
        logger.debug(res.asXML());
        return res.asXML();
    }

    public String getCalculation(String loginId, String password, String xrecord, String country, String url) {
        CalculateFromXRecordsRequest request = new CalculateFromXRecordsRequest();
        request.setLoginId(loginId);
        request.setPassword(password);
        request.setXrecords(xrecord);
        request.setCountry(country);
        Document response = b2bServiceProvider.getCalculationService(url).calculateFromXRecords(request);
        logger.debug(response.asXML());
        return response.asXML();
    }

    public String vinQuery(String loginId, String password, String vin, String callingApp, String url) {
        VinQueryRequest request = new VinQueryRequest();
        request.setLoginId(loginId);
        request.setPassword(password);
        request.setVin(vin);
        request.setCallingApplication(callingApp);
        Document response = b2bServiceProvider.getVehicleService(url).fullVinQuery(request);
        logger.debug(response.asXML());
        return response.asXML();
    }

}
