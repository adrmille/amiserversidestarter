package fr.amisss.core.common.math;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
// FIXME this class needs tests and javadoc
public class Calculus {

    @Value("${rounding.scale}")
    private int roundingScale;

    /**
     * value / baseValue -1
     * if value &gt; basevalue the result is &gt; 0
     *
     * @param value     to compare
     * @param baseValue to compare
     * @return %age to add to baseValue
     */
    public BigDecimal calculateDiffPercentage(BigDecimal value, BigDecimal baseValue) {
        return value.divide(baseValue, roundingScale, RoundingMode.DOWN).subtract(new BigDecimal("1")).multiply(new BigDecimal("100"));
    }

    public BigDecimal increaseByPercent(final BigDecimal valueToIncrease, final BigDecimal percentage) {
        return percentage.divide(new BigDecimal("100")).add(new BigDecimal("1")).multiply(valueToIncrease);
    }

    /**
     * Simple average between two {@link BigDecimal}.
     *
     * @param bg1 first value
     * @param bg2 second value
     * @return the average value
     */
    public BigDecimal average(final BigDecimal bg1, final BigDecimal bg2) {
        return bg1.add(bg2).divide(new BigDecimal("2"));
    }

}
