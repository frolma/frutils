package in.frol.frutils;

import in.frol.frutils.helpers.TestA;
import in.frol.frutils.helpers.TestClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static in.frol.frutils.Functions.unchecked;
import static in.frol.frutils.Functions.uncheckedBiConsumer;
import static in.frol.frutils.Functions.uncheckedBiFunction;
import static in.frol.frutils.Functions.uncheckedConsumer;
import static in.frol.frutils.Functions.uncheckedFunction;
import static in.frol.frutils.Functions.uncheckedSupplier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("java.util.Date Date Utils:")
class FunctionsTest {

    @Test
    @DisplayName("Noo operation stubs:")
    void noopFunctions() {
        // compile checks
        var set = Stream.of(new Object())
                .filter(Functions.emptyPredicate())
                .filter(Functions.alwaysTrue())
                .filter(Functions.alwaysFalse())
                .map(Functions.emptyUnaryOperator())
                .map(Functions.emptyFunction())
                .map(Functions.nullFunction())
                .collect(Collectors.toSet());
        Functions.doNothing().accept(new Object());
        Functions.emptyConsumer().accept(new Object());
        Functions.emptyBiConsumer().accept(new Object(), 7);
        assertTrue(Functions.emptyBiPredicate().test(new Object(), 7));
        assertTrue(Functions.biPredicateTrue().test(new Object(), 7));
        assertFalse(Functions.biPredicateFalse().test(new Object(), 7));
        assertNull(Functions.emptySupplier().get());
        assertNull(Functions.nullSupplier().get());
        assertTrue(Functions.booleanSupplierTrue().getAsBoolean());
        assertFalse(Functions.booleanSupplierFalse().getAsBoolean());
        assertNull(Functions.emptyBiFunction().apply(1, 2));
        Object test = new Object();
        assertEquals(Functions.justReturn().apply(test), test);
        assertEquals(Functions.emptyBinaryOperator().apply(test, 1), test);
        assertEquals(Functions.firstBinaryOperator().apply(test, 1), test);
        assertEquals(Functions.secondBinaryOperator().apply(1, test), test);

        assertTrue(set.isEmpty());
    }

    @Test
    void uncheckedFunctions() {
        // compile checks
        var object = new TestClass();
        Stream.of(new TestA())
                .peek(unchecked(object::voidWithInputUncheckedException))
                .peek(uncheckedConsumer(object::voidWithInputUncheckedException))
                .map(unchecked(object::withInputUncheckedException))
                .map(val -> uncheckedSupplier(object::withReturnUncheckedException).get())
                .map(uncheckedFunction(object::withInputUncheckedException))
                .map(val -> new TestA())
                .forEach(unchecked(object::voidWithInputUncheckedException));
        object.biConsumer(unchecked(object::voidWith2InputUncheckedException));
        object.biConsumer(uncheckedBiConsumer(object::voidWith2InputUncheckedException));
        object.biFunction(unchecked(object::with2InputUncheckedException));
        object.biFunction(uncheckedBiFunction(object::with2InputUncheckedException));
        Assertions.assertTrue(true);
    }

}