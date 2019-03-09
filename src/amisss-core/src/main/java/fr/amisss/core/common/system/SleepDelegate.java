package fr.amisss.core.common.system;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SleepDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SleepDelegate.class);

    /**
     * IN: MILLIS {@link Long}
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long millis = Long.parseLong((String) execution.getVariable("MILLIS"));
        Assert.notNull(millis, "millis");
        sleep(millis);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }

}
