package b2b;

import com.audatex.axn.classchecker.b2b.WebServiceProvider;
import com.audatex.axn.classchecker.b2b.calc.CalculationService;
import com.audatex.axn.classchecker.b2b.task.TaskService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public WebServiceProvider webServiceProvider() {
        return new WebServiceProvider("https://www.audatex.sg");
    }

    @Bean
    public TaskService taskService() {
        return new TaskService(webServiceProvider().taskService());
    }

    @Bean
    public CalculationService calculationService() {
        return new CalculationService(webServiceProvider().calculationService());
    }
}
