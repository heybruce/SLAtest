package b2b;

import com.audatex.axn.classchecker.b2b.WebServiceProvider;
import com.audatex.axn.classchecker.b2b.calc.CalculationService;
import com.audatex.axn.classchecker.b2b.task.TaskService;

import com.audatex.axn.classchecker.b2b.vehicle.VehicleService;
import org.springframework.stereotype.Component;

@Component
public class B2bServiceProvider {
    public TaskService getTaskService(String baseUrl) {
        return new TaskService(new WebServiceProvider(baseUrl).taskService());
    }

    public CalculationService getCalculationService(String baseUrl) {
        return new CalculationService(new WebServiceProvider(baseUrl).calculationService());
    }

    public VehicleService getVehicleService(String baseUrl) {
        return new VehicleService(new WebServiceProvider(baseUrl).vehicleIdentificationService());
    }
}
