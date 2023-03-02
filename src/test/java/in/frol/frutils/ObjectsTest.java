package in.frol.frutils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.of;

@DisplayName("Frutils Objects Utils:")
class ObjectsTest {

    static final Object TEST_OBJECT_A = new Object();
    static final Object TEST_OBJECT_B = new Object();
    static final Object TEST_OBJECT_C = new Object();
    static final Object TEST_OBJECT_D = new Object();
    static final Object TEST_FUNCTION_RESULT = new Object();
    static final Function<?, ?> TEST_FUNCTION = val -> TEST_FUNCTION_RESULT;
    static final Function<?, ?> TEST_FUNCTION_RETURN = val -> val;
    static final Function<?, ?> TEST_FUNCTION_A_IF_B_OR_NULL = val -> val == TEST_OBJECT_B ? TEST_OBJECT_A : null;
    static final Function<?, ?> TEST_FUNCTION_B_IF_A_OR_NULL = val -> val == TEST_OBJECT_A ? TEST_OBJECT_B : null;
    static final Function<?, ?> TEST_FUNCTION_B_IF_C_OR_NULL = val -> val == TEST_OBJECT_C ? TEST_OBJECT_B : null;
    static final Function<?, ?> TEST_FUNCTION_C_IF_B_OR_NULL = val -> val == TEST_OBJECT_B ? TEST_OBJECT_C : null;
    static final Function<?, ?> TEST_FUNCTION_D_IF_C_OR_NULL = val -> val == TEST_OBJECT_C ? TEST_OBJECT_D : null;
    static final Object TEST_SUPPLIER_RESULT = new Object();
    static final Supplier<?> TEST_SUPPLIER = () -> TEST_SUPPLIER_RESULT;
    static final Supplier<?> TEST_SUPPLIER_A = () -> TEST_OBJECT_A;
    static final Supplier<?> TEST_SUPPLIER_B = () -> TEST_OBJECT_B;
    static final Supplier<?> TEST_SUPPLIER_C = () -> TEST_OBJECT_C;

    static Stream<Arguments> sourceTestNeNullWithFunctionAndSupplier() {
        return Stream.of(
                of(null, null, null, null),
                of(TEST_OBJECT_A, null, null, null),
                of(TEST_OBJECT_A, TEST_FUNCTION_B_IF_A_OR_NULL, null, TEST_OBJECT_B),
                of(null, TEST_FUNCTION_B_IF_A_OR_NULL, null, null),
                of(null, TEST_FUNCTION_B_IF_A_OR_NULL, null, null),
                of(null, null, TEST_SUPPLIER_C, TEST_OBJECT_C),
                of(TEST_SUPPLIER_B, null, TEST_SUPPLIER_C, null),
                of(TEST_OBJECT_B, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_SUPPLIER_C, null),
                of(TEST_OBJECT_A, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_SUPPLIER_C, TEST_OBJECT_B),
                of(null, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_SUPPLIER_C, TEST_OBJECT_C),
                of(TEST_OBJECT_B, TEST_FUNCTION_A_IF_B_OR_NULL, TEST_SUPPLIER_C, TEST_OBJECT_A),
                of(null, TEST_FUNCTION_A_IF_B_OR_NULL, TEST_SUPPLIER_C, TEST_OBJECT_C),
                of(TEST_OBJECT_C, TEST_FUNCTION_B_IF_C_OR_NULL, TEST_SUPPLIER_C, TEST_OBJECT_B),
                of(null, TEST_FUNCTION_B_IF_C_OR_NULL, TEST_SUPPLIER_C, TEST_OBJECT_C)
        );
    }

    static Stream<Arguments> sourceTestNeNullWithFunction3() {
        return Stream.of(
                of(null, null, null, null, null),
                of(null, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_FUNCTION_C_IF_B_OR_NULL, TEST_FUNCTION_D_IF_C_OR_NULL, null),
                of(TEST_OBJECT_B, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_FUNCTION_C_IF_B_OR_NULL, TEST_FUNCTION_D_IF_C_OR_NULL, null),
                of(TEST_OBJECT_A, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_FUNCTION_C_IF_B_OR_NULL, TEST_FUNCTION_D_IF_C_OR_NULL, TEST_OBJECT_D)
        );
    }

    static Stream<Arguments> sourceTestNeNullWithFunction2() {
        return Stream.of(
                of(null, null, null, null),
                of(null, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_FUNCTION_C_IF_B_OR_NULL, null),
                of(TEST_OBJECT_B, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_FUNCTION_C_IF_B_OR_NULL, null),
                of(TEST_OBJECT_A, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_FUNCTION_B_IF_A_OR_NULL, null),
                of(TEST_OBJECT_A, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_FUNCTION_C_IF_B_OR_NULL, TEST_OBJECT_C),
                of(TEST_OBJECT_C, TEST_FUNCTION_B_IF_C_OR_NULL, TEST_FUNCTION_A_IF_B_OR_NULL, TEST_OBJECT_A)
        );
    }

    static Stream<Arguments> sourceTestNeNullWithFunction() {
        return Stream.of(
                of(new Object(), TEST_FUNCTION, TEST_FUNCTION_RESULT),
                of(TEST_OBJECT_A, TEST_FUNCTION_RETURN, TEST_OBJECT_A),
                of(TEST_OBJECT_B, TEST_FUNCTION_RETURN, TEST_OBJECT_B),
                of(TEST_OBJECT_B, TEST_FUNCTION_A_IF_B_OR_NULL, TEST_OBJECT_A),
                of(TEST_OBJECT_A, TEST_FUNCTION_A_IF_B_OR_NULL, null),
                of(TEST_OBJECT_A, TEST_FUNCTION_B_IF_A_OR_NULL, TEST_OBJECT_B),
                of(TEST_OBJECT_B, TEST_FUNCTION_B_IF_A_OR_NULL, null),
                of(null, TEST_FUNCTION_A_IF_B_OR_NULL, null),
                of(null, TEST_FUNCTION_B_IF_A_OR_NULL, null),
                of(null, TEST_FUNCTION_RETURN, null),
                of(null, null, null),
                of(TEST_OBJECT_A, null, null),
                of(TEST_OBJECT_B, null, null)
        );
    }

    static Stream<Arguments> sourceTestNeNullWithSupplier() {
        return Stream.of(
                of(null, TEST_SUPPLIER, TEST_SUPPLIER_RESULT),
                of(TEST_OBJECT_A, TEST_SUPPLIER, TEST_OBJECT_A),
                of(TEST_OBJECT_B, TEST_SUPPLIER, TEST_OBJECT_B),
                of(null, null, null),
                of(TEST_OBJECT_A, null, TEST_OBJECT_A),
                of(TEST_OBJECT_B, null, TEST_OBJECT_B),
                of(TEST_SUPPLIER_A, null, TEST_SUPPLIER_A),
                of(TEST_SUPPLIER_B, null, TEST_SUPPLIER_B),
                of(null, TEST_SUPPLIER_A, TEST_OBJECT_A),
                of(null, TEST_SUPPLIER_B, TEST_OBJECT_B)
        );
    }

    static Stream<Arguments> sourceTestNeNullWithSupplierVarArgs() {
        return Stream.of(
                of(null, null, null),
                of(null, new Supplier[]{null}, null),
                of(null, new Supplier[]{null, null}, null),
                of(null, new Supplier[]{null, null, null}, null),
                of(null, new Supplier[]{null, null, null, null}, null),
                of(null, new Supplier[]{null, null, null, TEST_SUPPLIER}, TEST_SUPPLIER_RESULT),
                of(null, new Supplier[]{null, null, null, TEST_SUPPLIER_A}, TEST_OBJECT_A),
                of(null, new Supplier[]{null, null, null, TEST_SUPPLIER_B}, TEST_OBJECT_B),
                of(null, new Supplier[]{null, null, TEST_SUPPLIER_A, TEST_SUPPLIER_B}, TEST_OBJECT_A),
                of(null, new Supplier[]{TEST_SUPPLIER, null, TEST_SUPPLIER_A, TEST_SUPPLIER_B}, TEST_SUPPLIER_RESULT),
                of(TEST_OBJECT_A, new Supplier[]{null, null, null, TEST_SUPPLIER}, TEST_OBJECT_A),
                of(TEST_OBJECT_B, new Supplier[]{null, null, null, TEST_SUPPLIER_A}, TEST_OBJECT_B)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, string,  string",
            "string, null,  string",
            "string, string2,  string",
            "string2, string,  string2",
            "null, 1,  1",
            "1, null,  1",
            "1, 2,  1",
            "1, 2,  1",
            "null, null, null",
    }, nullValues = "null")
    void testNeNull(Object val1, Object val2, Object result) {
        assertEquals(result, Objects.neNull(val1, val2));
    }

    @SuppressWarnings("ALL")
    @ParameterizedTest
    @MethodSource("sourceTestNeNullWithFunction")
    void testNeNullWithFunction(Object val, Function function, Object result) {
        assertEquals(result, Objects.neNull(val, function));
    }

    @SuppressWarnings("ALL")
    @ParameterizedTest
    @MethodSource("sourceTestNeNullWithFunctionAndSupplier")
    void testNeNullWithFunctionAndSupplier(Object val, Function function, Supplier supplier, Object result) {
        assertEquals(result, Objects.neNull(val, function, supplier));
    }

    @SuppressWarnings("ALL")
    @ParameterizedTest
    @MethodSource("sourceTestNeNullWithFunction2")
    void testNeNullWithFunction2(Object val, Function function1, Function function2, Object result) {
        assertEquals(result, Objects.neNull(val, function1, function2));
    }

    @SuppressWarnings("ALL")
    @ParameterizedTest
    @MethodSource("sourceTestNeNullWithFunction3")
    void testNeNullWithFunction3(Object val, Function function1, Function function2, Function function3, Object result) {
        assertEquals(result, Objects.neNull(val, function1, function2, function3));
    }

    @SuppressWarnings("ALL")
    @ParameterizedTest
    @MethodSource("sourceTestNeNullWithSupplier")
    void testNeNullWithSupplier(Object val, Supplier supplier, Object result) {
        assertEquals(result, Objects.neNull(val, supplier));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, null, null, null",
            "null, null, string, string",
            "null, string, string2, string",
            "null, string2, string, string2",
            "null, string, null, string",
            "null, string2, null, string2",
            "string, string2, string3, string",
            "string3, string2, string, string3",
            "string, null, null, string",
            "string2, null, null, string2",
            "string3, null, null, string3",
            "string3, null, string2, string3",
            "string, string, string, string",
            "1, 2, 3, 1",
            "1, 2, null, 1",
            "1, null, null, 1",
            "null, null, null, null",
            "null, 1, null, 1",
            "null, null, 2, 2",
            "2, null, 3, 2",
            "2857, 5464, 3562, 2857",
            "null, 5464, 3562, 5464",
            "null, null, 3562, 3562",
    }, nullValues = "null")
    void neNullThreeArg(Object val1, Object val2, Object val3, Object result) {
        assertEquals(result, Objects.neNull(val1, val2, val3));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, null, null, null, null",
            "null, null, string, string, string",
            "null, string, string2, string, string",
            "null, string2, string, string2, string2",
            "null, string, null, string, string",
            "null, string2, null, string2, string2",
            "string, string2, string3, string, string",
            "string3, string2, string, string3, string3",
            "string, null, null, string, string",
            "string2, null, null, string2, string2",
            "string3, null, null, string3, string3",
            "string3, null, string2, string3, string3",
            "string, string, string, string, string",
            "1, 2, 3, 1, 1",
            "1, 2, null, 1, 1",
            "1, null, null, 1, 1",
            "null, null, null, 42, 42",
            "null, 1, null, 1, 1",
            "null, null, 2, 2, 2",
            "2, null, 3, null, 2",
            "2857, 5464, 3562, 3466, 2857",
            "null, 5464, 3562, 3466, 5464",
            "null, null, 3562, 3466, 3562",
            "null, null, null, 3466, 3466",
    }, nullValues = "null")
    void neNullVarArg(Object val1, Object val2, Object val3, Object val4, Object result) {
        assertEquals(result, Objects.neNull(val1, val2, val3, val4));
    }

    @SuppressWarnings("ALL")
    @ParameterizedTest
    @MethodSource("sourceTestNeNullWithSupplierVarArgs")
    void testNeNullWithSupplierVarArg(Object val, Supplier[] supplier, Object result) {
        assertEquals(result, Objects.neNull(val, supplier));
        if (supplier == null) {
            return;
        }
        if (supplier.length == 1) {
            assertEquals(result, Objects.neNull(val, supplier[0]));
        }
        if (supplier.length == 2) {
            assertEquals(result, Objects.neNull(val, supplier[0], supplier[1]));
        }
        if (supplier.length == 3) {
            assertEquals(result, Objects.neNull(val, supplier[0], supplier[1], supplier[2]));
        }
        if (supplier.length == 4) {
            assertEquals(result, Objects.neNull(val, supplier[0], supplier[1], supplier[2], supplier[3]));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, null, true",
            "null, string, false",
            "string, null, false",
            "string, string, false"
    }, nullValues = "null")
    void testAllNull(Object val1, Object val2, boolean result) {
        assertEquals(result, Objects.allNull(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, null, null, null, true",
            "null, null, null, string, false",
            "null, null, string, string, false",
            "string, string, string, string, false",
            "string, null, null, null, false",
    }, nullValues = "null")
    void testAllNullVarArg(Object val1, Object val2, Object val3, Object val4, boolean result) {
        assertEquals(result, Objects.allNull(val1, val2, val3, val4));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "string1, string2, true",
            "1, 2, true",
            "null, null, false",
            "string1, null, false",
            "null, string1, false",
    }, nullValues = "null")
    void testAllNotNull(Object val1, Object val2, boolean result) {
        assertEquals(result, Objects.allNotNull(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "string1, string2, string3, string4, true",
            "1, 2, 3, 4, true",
            "null, null, null, null, false",
            "string1, string2, string3, null, false",
            "null, string2, string3, string4, false",
            "string1, string2, null, string4, false"
    }, nullValues = "null")
    void testAllNotNullVarArg(Object val1, Object val2, Object val3, Object val4, boolean result) {
        assertEquals(result, Objects.allNotNull(val1, val2, val3, val4));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "string1, string2, true",
            "1, 2, true",
            "null, null, false",
            "string, null, true",
            "null, string, true",
    }, nullValues = "null")
    void testAnyNotNull(Object val1, Object val2, boolean result) {
        assertEquals(result, Objects.anyNotNull(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "string1, string2, string3, string4, true",
            "1, 2, 3, 4, true",
            "null, null, null, null, false",
            "string1, string2, string3, null, true",
            "null, null, null, string4, true",
            "string1, string2, null, string4, true"
    }, nullValues = "null")
    void testAnyNotNullVarArg(Object val1, Object val2, Object val3, Object val4, boolean result) {
        assertEquals(result, Objects.anyNotNull(val1, val2, val3, val4));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "string1, string2, false",
            "1, 2, false",
            "null, null, true",
            "string, null, true",
            "null, string, true"
    }, nullValues = "null")
    void testAnyNull(Object val1, Object val2, boolean result) {
        assertEquals(result, Objects.anyNull(val1, val2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "string1, string2, string3, string4, false",
            "1, 2, 3, 4, false",
            "null, null, null, null, true",
            "string1, string2, string3, null, true",
            "null, null, null, string4, true",
            "string1, string2, null, string4, true"
    }, nullValues = "null")
    void testAnyNullVarArg(Object val1, Object val2, Object val3, Object val4, boolean result) {
        assertEquals(result, Objects.anyNull(val1, val2, val3, val4));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, true",
            "string, false",
            "1, false",
    }, nullValues = "null")
    void testIsNull(Object val, boolean result) {
        assertEquals(result, Objects.isNull(val));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, false",
            "string, true",
            "1, true",
    }, nullValues = "null")
    void testNonNull(Object val, boolean result) {
        assertEquals(result, Objects.nonNull(val));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1.0, 9.2, null",
            "null, 2.2, 3",
            "null, null, 7.3",
    }, nullValues = "null")
    void anyNullTrue(final BigDecimal val1, final BigDecimal val2, final BigDecimal val3) {
        assertTrue(Objects.anyNull(val1, val2, val3));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1.8, 9.0, 4.4",
            "0.0, 0., .0",
            "1, 0, 3",
    })
    void anyNullFalse(final BigDecimal val1, final BigDecimal val2, final BigDecimal val3) {
        assertFalse(Objects.anyNull(val1, val2, val3));
    }

    @ParameterizedTest
    @CsvSource(value = {"null, null, null"}, nullValues = "null")
    void allNullTrue(final BigDecimal val1, final BigDecimal val2, final BigDecimal val3) {
        assertTrue(Objects.allNull(val1, val2, val3));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, null, 4.4",
            "0.0, 0., .0, null",
            "null, 0, null",
    }, nullValues = "null")
    void allNullFalse(final BigDecimal val1, final BigDecimal val2, final BigDecimal val3) {
        assertFalse(Objects.allNull(val1, val2, val3));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1.8, 9.0, 4.4",
            "0.0, 0., .0",
            "1, 0, 3",
    })
    void allNotNullTrue(final BigDecimal val1, final BigDecimal val2, final BigDecimal val3) {
        assertTrue(Objects.allNotNull(val1, val2, val3));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1.8, null, 4.4",
            "0.0, 0., null",
            "1, 0, null",
    }, nullValues = "null")
    void allNotNullFalse(final BigDecimal val1, final BigDecimal val2, final BigDecimal val3) {
        assertFalse(Objects.allNotNull(val1, val2, val3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.", ".0", "0.0", "0.0", "1", "0"})
    void notNullTrue(final BigDecimal value) {
        assertTrue(Objects.nonNull(value));
    }

    @ParameterizedTest
    @NullSource
    void notNullFalse(final BigDecimal value) {
        assertFalse(Objects.nonNull(value));
    }

    @Test
    void neNullRunnableAndSupplier() {
        final int[] intArray = new int[3];

        assertArrayEquals(new int[]{0, 0, 0}, intArray);

        final Runnable integerRunnable = () -> intArray[1] = 1;
        Objects.neNull("non null value", integerRunnable);

        assertArrayEquals(new int[]{0, 0, 0}, intArray);

        Objects.neNull(null, integerRunnable);

        assertArrayEquals(new int[]{0, 1, 0}, intArray);

        final Supplier<Integer> integerSupplier = () -> intArray[0] = 1;
        var supplierResult = Objects.neNull("non null value", integerSupplier);
        assertEquals("non null value", supplierResult);

        assertArrayEquals(new int[]{0, 1, 0}, intArray);

        supplierResult = Objects.neNull(null, integerSupplier);
        assertEquals(1, supplierResult);

        assertArrayEquals(new int[]{1, 1, 0}, intArray);
    }

    @Test
    void eqAndNe() {
        Assertions.assertTrue(Objects.eq(1, 1));
        Assertions.assertTrue(Objects.equals(1, 1));
        Assertions.assertTrue(Objects.eq(1, 1));
        Assertions.assertTrue(Objects.equals("123", "123"));
        Assertions.assertTrue(Objects.eq(130, 130));
        Assertions.assertTrue(Objects.equals("130", "130"));
        Assertions.assertFalse(Objects.ne(1, 1));
        Assertions.assertFalse(Objects.notEquals(1, 1));
        Assertions.assertFalse(Objects.ne(1, 1));
        Assertions.assertFalse(Objects.notEquals("123", "123"));
        Assertions.assertFalse(Objects.ne(130, 130));
        Assertions.assertFalse(Objects.notEquals("130", "130"));
    }
}
