package fr.amisss;

import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AmiSSSApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmiSSSApplication.class, args);
    }

    @EventListener
    private void processPostDeploy(PostDeployEvent event) {
        // Exemple to launch a process at startup
        // runtimeService.startProcessInstanceByKey("Process_1");
    }
}
