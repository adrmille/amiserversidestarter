package fr.amisss.core.common.system;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class PrintDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintDelegate.class);

    /**
     * IN: MESSAGE {@link String}<br>
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        final String message = (String) execution.getVariable("MESSAGE");
        Assert.notNull(message, "message");

        LOGGER.info("Print: {}", message);
    }

}
