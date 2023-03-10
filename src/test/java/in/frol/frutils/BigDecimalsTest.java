package in.frol.frutils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Date Utils:")
class BigDecimalsTest {

    @ParameterizedTest
    @ValueSource(strings = {"0.", ".0", "0.0", "0.0", "000.00000", "0"})
    void isZeroTrue(final BigDecimal value) {
        assertTrue(BigDecimals.isZero(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2.", ".4", "0.6", "0.1", "000.00001", "3"})
    void isZeroFalse(final BigDecimal value) {
        assertFalse(BigDecimals.isZero(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2.", ".4", "0.6", "0.1", "000.00001", "3"})
    void notZeroTrue(final BigDecimal value) {
        assertTrue(BigDecimals.notZero(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.", ".0", "0.0", "0.0", "000.00000", "0"})
    void notZeroFalse(final BigDecimal value) {
        assertFalse(BigDecimals.notZero(value));
    }

    @ParameterizedTest
    @CsvSource(value = {"null", "0.", "0.0", ".0", ".00", "0000.000000"}, nullValues = "null")
    void isNullOrZeroTrue(final BigDecimal value) {
        assertTrue(BigDecimals.isNullOrZero(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.", ".000001", "0.2", "567756.0", "-.00012", "93856"})
    void isNullOrZeroFalse(final BigDecimal value) {
        assertFalse(BigDecimals.isNullOrZero(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.", ".000001", "0.2", "567756.0", "-.00012", "93856"})
    void neitherNullNorZeroTrue(final BigDecimal value) {
        assertTrue(BigDecimals.neitherNullNorZero(value));
    }

    @ParameterizedTest
    @CsvSource(value = {"null", "0.", "0.0", ".0", ".00", "0000.000000"}, nullValues = "null")
    void neitherNullNorZeroFalse(final BigDecimal value) {
        assertFalse(BigDecimals.neitherNullNorZero(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.", ".000001", "0.2", "567756.0", ".00012", "93856"})
    void isPositiveTrue(final BigDecimal value) {
        assertTrue(BigDecimals.isPositive(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1.", "-.000001", "0.0", "-567756.0", "-.00012", "0."})
    void isPositiveFalse(final BigDecimal value) {
        assertFalse(BigDecimals.isPositive(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1.", "-.000001", "-00.01", "-567756.0", "-.00012", "-0.2"})
    void isNegativeTrue(final BigDecimal value) {
        assertTrue(BigDecimals.isNegative(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.", ".000001", "0.2", "567756.0", ".00012", "93856", "0", "0.0", ".0"})
    void isNegativeFalse(final BigDecimal value) {
        assertFalse(BigDecimals.isNegative(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1.", "-.000001", "-0.2", "-567756.0", "-.00012", "-93856", "0", "0.0", ".0"})
    void notPositiveTrue(final BigDecimal value) {
        assertTrue(BigDecimals.notPositive(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.", ".000001", "0.2", "567756.0", ".00012", "93856", "50", "0.05", "7.0"})
    void notPositiveFalse(final BigDecimal value) {
        assertFalse(BigDecimals.notPositive(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.", ".000001", "0.2", "567756.0", ".00012", "93856", "0.0"})
    void notNegativeTrue(final BigDecimal value) {
        assertTrue(BigDecimals.notNegative(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1.", "-.000001", "-0.01", "-567756.0", "-.00012", "-10."})
    void notNegativeFalse(final BigDecimal value) {
        assertFalse(BigDecimals.notNegative(value));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 1",
            "0, 0",
            "-1, -1",
            "-0.0000087, -0.0000087",
            "848474689406720286638275834.304867, 848474689406720286638275834.304867",
            "-0.09873, -0.09873"
    })
    void equalsTrue(final BigDecimal val1, final BigDecimal val2) {
        assertTrue(BigDecimals.equals(val1, val2));
        assertTrue(BigDecimals.eq(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, -1",
            "-1, 0",
            "-0.0000087, -0.0000088",
            "848474689406720286638275834.304866, 848474689406720286638275834.304867",
            "-0.09873, -0.09874"
    })
    void equalsFalse(final BigDecimal val1, final BigDecimal val2) {
        assertFalse(BigDecimals.equals(val1, val2));
        assertFalse(BigDecimals.eq(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-1, 0",
            "0, -1",
            "-1, -2",
            "-0.0000087, -0.0000088",
            "848474689406720286638275834.304866, 848474689406720286638275834.304867",
            "-0.09873, -0.09874"
    })
    void notEqualsTrue(final BigDecimal val1, final BigDecimal val2) {
        assertTrue(BigDecimals.notEquals(val1, val2));
        assertTrue(BigDecimals.ne(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 1",
            "0, 0",
            "-1, -1",
            "-0.0000087, -0.0000087",
            "848474689406720286638275834.304867, 848474689406720286638275834.304867",
            "-0.09873, -0.09873"
    })
    void notEqualsFalse(final BigDecimal val1, final BigDecimal val2) {
        assertFalse(BigDecimals.notEquals(val1, val2));
        assertFalse(BigDecimals.ne(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, -1",
            "1, 0",
            "1, -1",
            "0, -2",
            "-0.0000086, -0.0000087",
            "848474689406720286638275834.304869, 848474689406720286638275834.304868",
            "-0.09872, -0.09873"
    })
    void greater(final BigDecimal val1, final BigDecimal val2) {
        assertTrue(BigDecimals.greater(val1, val2));
        assertTrue(BigDecimals.gt(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, -1",
            "1, 0",
            "1, -1",
            "0, -2",
            "0, 0",
            "1, 1",
            "-1, -1",
            "-2, -2",
            "-0.0000086, -0.0000087",
            "848474689406720286638275834.304869, 848474689406720286638275834.304868",
            "-0.09872, -0.09873"
    })
    void greaterOrEquals(final BigDecimal val1, final BigDecimal val2) {
        assertTrue(BigDecimals.greaterOrEquals(val1, val2));
        assertTrue(BigDecimals.gte(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-2, -1",
            "-1, 0",
            "-2, -1",
            "-3, -2",
            "1, 2",
            "1, 2",
            "-1, 9375639",
            "-87, -86",
            "-0.0000087, -0.0000086",
            "848474689406720286638275834.30469, 848474689406720286638275834.30470",
            "-0.09874, -0.09873"
    })
    void less(final BigDecimal val1, final BigDecimal val2) {
        assertTrue(BigDecimals.less(val1, val2));
        assertTrue(BigDecimals.lt(val1, val2));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "-20, -19",
            "2, 2",
            "-1, 0",
            "0, 0",
            "-2, -1",
            "-3, -2",
            "1, 2",
            "1, 2",
            "-1, 9375639",
            "-1, -1",
            "9375639, 9375639",
            "-87, -86",
            "-87, -87",
            "-0.0000087, -0.0000086",
            "848474689406720286638275834.30469, 848474689406720286638275834.30470",
            "-0.09874, -0.09873"
    })
    void lessOrEquals(final BigDecimal val1, final BigDecimal val2) {
        assertTrue(BigDecimals.lessOrEquals(val1, val2));
        assertTrue(BigDecimals.lte(val1, val2));
    }

    @Test
    void zero() {
        assertTrue(BigDecimals.equals(BigDecimal.ZERO, BigDecimals.zero()));
        assertTrue(BigDecimals.eq(BigDecimal.ZERO, BigDecimals.zero()));
        assertTrue(BigDecimals.equals(new BigDecimal("0"), BigDecimals.zero()));
        assertTrue(BigDecimals.eq(new BigDecimal("0"), BigDecimals.zero()));
        assertTrue(BigDecimals.equals(new BigDecimal("0.000"), BigDecimals.zero()));
        assertTrue(BigDecimals.eq(new BigDecimal("0.000"), BigDecimals.zero()));
    }

    @Test
    void abs() {
        assertTrue(BigDecimals.eq(new BigDecimal("1"), BigDecimals.abs(new BigDecimal("1"))));
        assertTrue(BigDecimals.eq(new BigDecimal("1"), BigDecimals.abs(new BigDecimal("-1"))));
        assertTrue(BigDecimals.eq(new BigDecimal("9999.99999"), BigDecimals.abs(new BigDecimal("9999.99999"))));
        assertTrue(BigDecimals.eq(new BigDecimal("9999.99999"), BigDecimals.abs(new BigDecimal("-9999.99999"))));
    }
}