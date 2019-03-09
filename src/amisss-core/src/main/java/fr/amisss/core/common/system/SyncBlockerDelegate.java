package fr.amisss.core.common.system;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Service
public class SyncBlockerDelegate implements JavaDelegate {

    private Map<String, SyncBlocker> tokens = new HashMap<>();

    private Object globalMutex = new Object();

    /**
     * IN: NUMBER_OF_THREAD_TO_WAIT {@link Integer}<br>
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        final Integer numberOfThreadToWait = Integer
                .parseInt((String) execution.getVariable("NUMBER_OF_THREAD_TO_WAIT"));
        Assert.notNull(numberOfThreadToWait, "numberOfThreadToWait");

        final String id = execution.getProcessInstanceId();

        final SyncBlocker syncToken = tokens.computeIfAbsent(id, k -> createSyncToken(k, numberOfThreadToWait));

        final int numberOfRemainingThreads = syncToken.decrease();
        if (numberOfRemainingThreads > 0) {
            Thread.currentThread().interrupt();
        } else if (numberOfRemainingThreads == 0) {
            tokens.remove(id);
        } else if (numberOfRemainingThreads < 0) {
            throw new IllegalThreadStateException(
                    "numberOfRemainingThreads: " + numberOfRemainingThreads + " ; currentActivityId: " + id);
        }
    }

    private SyncBlocker createSyncToken(String currentActivityId, int numberOfThreadToWait) {
        synchronized (globalMutex) {
            return tokens.computeIfAbsent(currentActivityId, k -> new SyncBlocker(numberOfThreadToWait));
        }
    }

    private class SyncBlocker {

        private int numberOfThreadToWait;

        private Object mutex = new Object();

        public SyncBlocker(int numberOfThreadToWait) {
            this.numberOfThreadToWait = numberOfThreadToWait;
        }

        public int decrease() {
            synchronized (mutex) {
                return --numberOfThreadToWait;
            }
        }

    }

}
