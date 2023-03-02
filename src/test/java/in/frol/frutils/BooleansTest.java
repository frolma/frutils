package in.frol.frutils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleansTest {

    @ParameterizedTest
    @CsvSource(value = {
            "true, false",
            "false, true",
            "null, true",
    }, nullValues = "null")
    void neTrue(Boolean param, boolean expected) {
        assertEquals(expected, Booleans.neTrue(param));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "true, true",
            "false, false",
            "null, false",
    }, nullValues = "null")
    void eqTrue(Boolean param, boolean expected) {
        assertEquals(expected, Booleans.eqTrue(param));
    }
}
