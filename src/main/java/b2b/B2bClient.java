package b2b;

import com.audatex.axn.classchecker.b2b.calc.CalculateFromXRecordsRequest;
import com.audatex.axn.classchecker.b2b.task.CreateTaskRequest;
import com.audatex.axn.classchecker.b2b.task.GetTaskRequest;
import com.audatex.axn.classchecker.b2b.vehicle.VinQueryRequest;

import com.audatex.tw.gateway.service.Message;
import com.audatex.tw.gateway.service.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;
import org.dom4j.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xml.sax.SAXException;
import sun.rmi.runtime.Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

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

    public String createTask (String loginId, String password, String task, String url) throws ParserConfigurationException, IOException, SAXException {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setLoginId(loginId);
        request.setPassword(password);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(task));
        org.w3c.dom.Document doc =  builder.parse(is);
        org.dom4j.io.DOMReader reader = new DOMReader();
        org.dom4j.Document document = reader.read(doc);
        request.setTask(document);

        String response = b2bServiceProvider.getTaskService(url).createTask(request);
        logger.debug(response);
        return response;
    }

    public Task getTaskTwVolvo(String loginId, String claimNumber, String url) {
        Task response = new Task();
        try {
            URL requestUrl = new URL(url);
            response = b2bServiceProvider.getTaskServiceTwVolvo(requestUrl).getTask(loginId, claimNumber);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public Message uploadTaskTwVolvo(String loginId, String claimNumber, String vin, String plateNumber, String insuranceName, String url) {
        Message response = new Message();
        try {
            URL requestUrl = new URL(url);
            response = b2bServiceProvider.getTaskServiceTwVolvo(requestUrl).uploadTask(loginId, claimNumber, vin, plateNumber, insuranceName);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
