package fr.amisss.core.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import fr.amisss.core.common.system.PrintDelegate;
import org.slf4j.LoggerFactory;

public class PrintServiceListAppender {

    private PrintServiceListAppender() {
    }

    public static ListAppender<ILoggingEvent> build() {
        final Logger logger = (Logger) LoggerFactory.getLogger(PrintDelegate.class);
        final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.setName("list-appender");
        listAppender.setContext(logger.getLoggerContext());
        listAppender.start();
        logger.addAppender(listAppender);
        return listAppender;
    }
}
