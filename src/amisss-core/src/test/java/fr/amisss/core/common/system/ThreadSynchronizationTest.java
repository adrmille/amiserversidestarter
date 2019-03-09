package fr.amisss.core.common.system;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import fr.amisss.CoreTestApplication;
import fr.amisss.core.util.PrintServiceListAppender;
import org.awaitility.Awaitility;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CoreTestApplication.class })
@SpringBootTest
@Transactional
@ActiveProfiles("disableRealTrading")
public class ThreadSynchronizationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadSynchronizationTest.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TestDatabaseHelper testDatabaseHelper;

    @Before
    public void init() {
        testDatabaseHelper.clearDatabase();
    }

    @Test
    public void should_merge_threads_at_the_end_given_flow_seperate_in_two_threads() throws InterruptedException {

        final ListAppender<ILoggingEvent> listAppender = PrintServiceListAppender.build();

        runtimeService.startProcessInstanceByKey("Mutli-thread-test");

        Awaitility.await().atMost(2, TimeUnit.SECONDS).timeout(14, TimeUnit.SECONDS).until(() -> {
            final List<String> expectedMessages = new ArrayList<>(Arrays.asList("Print: in thread 1",
                    "Print: in thread 2", "Print: in thread 3", "Print: should be print only one time"));
            final int expectedMessagesListSize = expectedMessages.size();
            LOGGER.debug("Event log: " + listAppender.list);
            for (ILoggingEvent iLoggingEvent : listAppender.list) {
                expectedMessages.remove(iLoggingEvent.getFormattedMessage());
            }
            LOGGER.debug("Expected remaining messages: " + expectedMessages);
            return expectedMessages.isEmpty() && expectedMessagesListSize == listAppender.list.size();
        });

        Assert.assertTrue(!listAppender.list.isEmpty()
                && !listAppender.list.get(0).getFormattedMessage().equals("Print: in thread 1"));
    }

}
