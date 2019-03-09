package fr.amisss.core.critical;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CriticalAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(CriticalAspect.class);

    /**
     * Check if the method execution return an exception. If so then log in error
     * and re-throw.
     * 
     * @param joinPoint exposed proceed
     * @return proceed result
     * @throws Throwable when an error occur
     */
    @Around("@annotation(fr.amisss.core.critical.Critical)")
    public Object checkTimeout(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            LOGGER.error("CRITICAL_ERROR", e);
            System.exit(1);
            throw e;
        }
    }

}
