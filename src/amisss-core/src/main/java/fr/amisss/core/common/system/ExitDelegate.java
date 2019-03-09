package fr.amisss.core.common.system;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExitDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExitDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Closing the application with remaining parameters: {}", execution);
        System.exit(0);
    }

}
