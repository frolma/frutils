package in.frol.frutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BooleansTest {

    @ParameterizedTest
    @CsvSource(value = {
            "true, false",
            "false, true",
            "null, true",
    }, nullValues = "null")
    void neTrue(Boolean param, boolean expected) {
        assertEquals(expected, Booleans.neTrue(param));
        assertEquals(expected, Booleans.not(param));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "true, true",
            "false, false",
            "null, false",
    }, nullValues = "null")
    void eqTrue(Boolean param, boolean expected) {
        assertEquals(expected, Booleans.eqTrue(param));
        assertEquals(expected, Booleans.is(param));
    }

    @Test
    void getIfNeTrue() {
        BigDecimal expectedValueIfFalse = new BigDecimal("1234.567");
        assertEquals(expectedValueIfFalse, Booleans.getIfNeTrue(false, expectedValueIfFalse));
        assertEquals(expectedValueIfFalse, Booleans.getIfNeTrue(null, expectedValueIfFalse));
        assertNull(Booleans.getIfNeTrue(true, expectedValueIfFalse));
    }

    @Test
    void getIfTrue() {
        BigDecimal expectedValueIfFalse = new BigDecimal("1234.567");
        assertNull(Booleans.getIfTrue(false, expectedValueIfFalse));
        assertNull(Booleans.getIfTrue(null, expectedValueIfFalse));
        assertEquals(expectedValueIfFalse, Booleans.getIfTrue(true, expectedValueIfFalse));
    }

    @Test
    void supplyIfNeTrue() {
        BigDecimal expectedValueIfFalse = new BigDecimal("1234.567");
        assertEquals(expectedValueIfFalse, Booleans.supplyIfNeTrue(false, () -> expectedValueIfFalse));
        assertEquals(expectedValueIfFalse, Booleans.supplyIfNeTrue(null, () -> expectedValueIfFalse));
        assertNull(Booleans.supplyIfNeTrue(true, () -> expectedValueIfFalse));
    }

    @Test
    void supplyIfTrue() {
        BigDecimal expectedValueIfFalse = new BigDecimal("1234.567");
        assertNull(Booleans.supplyIfTrue(false, () -> expectedValueIfFalse));
        assertNull(Booleans.supplyIfTrue(null, () -> expectedValueIfFalse));
        assertEquals(expectedValueIfFalse, Booleans.supplyIfTrue(true, () -> expectedValueIfFalse));
    }

    @Test
    void runIfNeTrue() {
        int[] values = new int[3];
        Booleans.runIfNeTrue(false, () -> values[0] = 1);
        assertArrayEquals(new int[]{1, 0, 0}, values);
        Booleans.runIfNeTrue(null, () -> values[1] = 1);
        assertArrayEquals(new int[]{1, 1, 0}, values);
        Booleans.runIfNeTrue(true, () -> values[2] = 1);
        assertArrayEquals(new int[]{1, 1, 0}, values);
    }

    @Test
    void runIfTrue() {
        int[] values = new int[3];
        Booleans.runIfTrue(false, () -> values[0] = 1);
        assertArrayEquals(new int[]{0, 0, 0}, values);
        Booleans.runIfTrue(null, () -> values[1] = 1);
        assertArrayEquals(new int[]{0, 0, 0}, values);
        Booleans.runIfTrue(true, () -> values[2] = 1);
        assertArrayEquals(new int[]{0, 0, 1}, values);
    }
}
