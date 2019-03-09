package fr.amisss.core.common.timeout;

import fr.amisss.core.common.exception.TechnicalException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
// FIXME add some tests
public class TimeoutAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutAspect.class);

    /**
     * Check if the method execution takes more than xxx millisecond to execute. If
     * a timeout is detected then a {@link RuntimeException} is thrown.
     *
     * @param joinPoint exposed proceed
     * @param timeout timeout before throwing an error if the method took too much time to complete
     * @return proceed result
     * @throws Throwable when an error occur
     */
    @Around("@annotation(timeout)")
    public Object checkTimeout(ProceedingJoinPoint joinPoint, Timeout timeout) throws Throwable {

        final long timeBeforeProceed = new Date().getTime();
        final String jointPointMethodName = joinPoint.getSignature().getName();

        final Object proceed = joinPoint.proceed();

        final long timeAfterProceed = new Date().getTime();
        final long timeElapsed = timeAfterProceed - timeBeforeProceed;

        LOGGER.debug("Elapsed time {} for method {}", timeElapsed, jointPointMethodName);

        if (timeElapsed > timeout.timeoutMilli()) {
            throw new TechnicalException(
                    String.format("Timeout: elapsed time %s for method %s ", timeElapsed, jointPointMethodName));
        }

        return proceed;
    }

}
