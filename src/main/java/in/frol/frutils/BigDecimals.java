package in.frol.frutils;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Utility class for simple operations with BigDecimal without
 * creating additional objects and improving code readability
 */
public final class BigDecimals {

    private static final int ZERO_INT = 0;
    private static final Comparator<BigDecimal> COMPARATOR = Comparator.naturalOrder();

    private BigDecimals() {
        /* empty body */
    }

    /**  */
    public static boolean isZero(final BigDecimal value) {
        return value.signum() == 0;
    }

    /**  */
    public static boolean notZero(final BigDecimal value) {
        return !isZero(value);
    }

    /**  */
    public static boolean isNullOrZero(final BigDecimal value) {
        return Objects.isNull(value) || isZero(value);
    }

    /**  */
    public static boolean neitherNullNorZero(final BigDecimal value) {
        return !isNullOrZero(value);
    }

    /**  */
    public static boolean isPositive(final BigDecimal value) {
        return value.signum() > 0;
    }

    /**  */
    public static boolean isNegative(final BigDecimal value) {
        return value.signum() < 0;
    }

    /**  */
    public static boolean notPositive(final BigDecimal value) {
        return !isPositive(value);
    }

    /**  */
    public static boolean notNegative(final BigDecimal value) {
        return !isNegative(value);
    }

    /**  */
    public static boolean equals(final BigDecimal value1, final BigDecimal value2) {
        return COMPARATOR.compare(value1, value2) == 0;
    }

    /**  */
    public static boolean notEquals(final BigDecimal value1, final BigDecimal value2) {
        return !equals(value1, value2);
    }

    /**  */
    public static boolean greater(final BigDecimal value1, final BigDecimal value2) {
        return COMPARATOR.compare(value1, value2) > ZERO_INT;
    }

    /**  */
    public static boolean greaterOrEquals(final BigDecimal value1, final BigDecimal value2) {
        return COMPARATOR.compare(value1, value2) >= ZERO_INT;
    }

    /**  */
    public static boolean less(final BigDecimal value1, final BigDecimal value2) {
        return COMPARATOR.compare(value1, value2) < ZERO_INT;
    }

    /**  */
    public static boolean lessOrEquals(final BigDecimal value1, final BigDecimal value2) {
        return COMPARATOR.compare(value1, value2) <= ZERO_INT;
    }
}
