package in.frol.frutils;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Utility class for providing no-op implementations of functional interfaces,
 * wrappers for non-checked exceptions and more
 */
public final class Functions {

    private Functions() {
        /* empty body */
    }

    /** No-op consumer plug: always do nothing */
    public static <T> Consumer<T> emptyConsumer() {
        return ignoredArgument -> {
            /* empty body */
        };
    }

    /** No-op consumer plug: always do nothing */
    public static <T> Consumer<T> doNothing() {
        return ignoredArgument -> {
            /* empty body */
        };
    }

    /** No-op bi consumer plug: always do nothing */
    public static <T, U> BiConsumer<T, U> emptyBiConsumer() {
        return (ignoredFirstArgument, ignoredSecondArgument) -> {
            /* empty body */
        };
    }

    /** No-op predicate plug: always positive */
    public static <T> Predicate<T> emptyPredicate() {
        return ignoredArgument -> true;
    }

    /** Alias for {@linkplain Functions#emptyPredicate()} */
    public static <T> Predicate<T> alwaysTrue() {
        return ignoredArgument -> true;
    }

    /** No-op predicate plug: always negative */
    public static <T> Predicate<T> alwaysFalse() {
        return ignoredArgument -> false;
    }

    /** No-op bi predicate plug: always positive */
    public static <T, U> BiPredicate<T, U> emptyBiPredicate() {
        return (ignoredFirstArgument, ignoredSecondArgument) -> true;
    }

    /** Alias for {@linkplain Functions#emptyPredicate()} */
    public static <T, U> BiPredicate<T, U> biPredicateTrue() {
        return (ignoredFirstArgument, ignoredSecondArgument) -> true;
    }

    /** No-op bi predicate plug: always negative */
    public static <T, U> BiPredicate<T, U> biPredicateFalse() {
        return (ignoredFirstArgument, ignoredSecondArgument) -> false;
    }

    /** No-op supplier plug: always null */
    public static <T> Supplier<T> emptySupplier() {
        return () -> null;
    }

    /** Alias for {@linkplain Functions#emptySupplier()} */
    public static <T> Supplier<T> nullSupplier() {
        return () -> null;
    }

    /** No-op boolean supplier plug: always positive */
    public static BooleanSupplier booleanSupplierTrue() {
        return () -> true;
    }

    /** No-op boolean supplier plug: always negative */
    public static BooleanSupplier booleanSupplierFalse() {
        return () -> false;
    }

    /**
     * No-op function plug: always null
     * <p>
     * and you may have been looking for:
     *
     * @see Functions#justReturn()
     * @see Functions#emptyUnaryOperator()
     */
    public static <T, R> Function<T, R> emptyFunction() {
        return ignoredArgument -> null;
    }

    /** Alias for {@linkplain Functions#emptyFunction()} */
    public static <T, R> Function<T, R> nullFunction() {
        return ignoredArgument -> null;
    }

    /** No-op bi function plug: always null */
    public static <T, U, R> BiFunction<T, U, R> emptyBiFunction() {
        return (ignoredFirstArgument, ignoredSecondArgument) -> null;
    }

    /** No-op unary operator plug: always input argument */
    public static <T> UnaryOperator<T> emptyUnaryOperator() {
        return argument -> argument;
    }

    /** Alias for {@linkplain Functions#emptyUnaryOperator()} */
    public static <T> UnaryOperator<T> justReturn() {
        return argument -> argument;
    }

    /** No-op binary operator plug: always first input argument */
    @SuppressWarnings("unused")
    public static <T, U> BinaryOperator<T> emptyBinaryOperator() {
        return (firstArgument, secondArgument) -> firstArgument;
    }

    /** Alias for {@linkplain Functions#emptyBinaryOperator()} */
    @SuppressWarnings("unused")
    public static <T, U> BinaryOperator<T> firstBinaryOperator() {
        return (firstArgument, secondArgument) -> firstArgument;
    }

    /** No-op binary operator plug: always second input argument */
    @SuppressWarnings("unused")
    public static <T, U> BinaryOperator<T> secondBinaryOperator() {
        return (firstArgument, secondArgument) -> secondArgument;
    }

    /** Alias for {@linkplain Functions#unchecked(CheckedFunction)} */
    public static <T, R, E extends Exception> Function<T, R> uncheckedFunction(
            CheckedFunction<T, R, E> function) {
        return unchecked(function);
    }

    /**
     * Creates an unchecked wrapper for a function that throws a checked exception.
     *
     * @param function function to wrap
     * @param <T>      type of the function's input parameter
     * @param <R>      type of the function's return value
     * @param <E>      type of the checked exception that the function may throw
     * @return new function that throws an unchecked exception
     */
    public static <T, R, E extends Exception> Function<T, R> unchecked(
            CheckedFunction<T, R, E> function) {
        return input -> {
            try {
                return function.apply(input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /** Alias for {@linkplain Functions#unchecked(CheckedConsumer)} */
    public static <T, E extends Exception> Consumer<T> uncheckedConsumer(
            CheckedConsumer<T, E> consumer) {
        return unchecked(consumer);
    }

    /**
     * Creates an unchecked wrapper for a consumer that throws a checked exception.
     *
     * @param consumer consumer to wrap
     * @param <T>      type of the consumer's input parameter
     * @param <E>      type of the checked exception that the consumer may throw
     * @return new consumer that throws an unchecked exception
     */
    public static <T, E extends Exception> Consumer<T> unchecked(
            CheckedConsumer<T, E> consumer) {
        return argument -> {
            try {
                consumer.accept(argument);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /** Alias for {@linkplain Functions#unchecked(CheckedBiFunction)} */
    public static <T, U, R, E extends Exception> BiFunction<T, U, R> uncheckedBiFunction(
            CheckedBiFunction<T, U, R, E> biFunction) {
        return unchecked(biFunction);
    }

    /**
     * Creates an unchecked wrapper for a BiConsumer that throws a checked exception.
     *
     * @param biFunction BiFunction to wrap
     * @param <T>        type of the first input parameter to the bi-consumer
     * @param <U>        type of the second input parameter to the bi-consumer
     * @param <R>        type of the BiFunction's return value
     * @param <E>        type of the checked exception that the bi-consumer may throw
     * @return new BiConsumer that throws an unchecked exception
     */
    public static <T, U, R, E extends Exception> BiFunction<T, U, R> unchecked(
            CheckedBiFunction<T, U, R, E> biFunction) {
        return (t, u) -> {
            try {
                return biFunction.apply(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /** Alias for {@linkplain Functions#unchecked(CheckedBiConsumer)} */
    public static <T, U, E extends Exception> BiConsumer<T, U> uncheckedBiConsumer(
            CheckedBiConsumer<T, U, E> biConsumer) {
        return unchecked(biConsumer);
    }

    /**
     * Creates an unchecked wrapper for a BiConsumer that throws a checked exception.
     *
     * @param biConsumer BiConsumer to wrap
     * @param <T>        type of the first input parameter to the bi-consumer
     * @param <U>        type of the second input parameter to the bi-consumer
     * @param <E>        type of the checked exception that the bi-consumer may throw
     * @return new BiConsumer that throws an unchecked exception
     */
    public static <T, U, E extends Exception> BiConsumer<T, U> unchecked(
            CheckedBiConsumer<T, U, E> biConsumer) {
        return (t, u) -> {
            try {
                biConsumer.accept(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /** Alias for {@linkplain Functions#unchecked(CheckedSupplier)} */
    public static <T, E extends Exception> Supplier<T> uncheckedSupplier(
            CheckedSupplier<T, E> supplier) {
        return unchecked(supplier);
    }

    /**
     * Creates an unchecked wrapper for a supplier that throws a checked exception.
     *
     * @param supplier supplier to wrap
     * @param <T>      type of the supplier's return value
     * @param <E>      type of the checked exception that the supplier may throw
     * @return new supplier that throws an unchecked exception
     */
    public static <T, E extends Exception> Supplier<T> unchecked(
            CheckedSupplier<T, E> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Functional interface for a Consumer that throws a checked exception.
     */
    @FunctionalInterface
    public interface CheckedConsumer<T, E extends Exception> {
        void accept(T argument) throws E;
    }

    /**
     * Functional interface for a BiConsumer that throws a checked exception.
     */
    @FunctionalInterface
    public interface CheckedBiConsumer<T, U, E extends Exception> {
        void accept(T firstArgument, U secondArgument) throws E;
    }

    /**
     * Functional interface for a Function that throws a checked exception.
     */
    @FunctionalInterface
    public interface CheckedFunction<T, R, E extends Exception> {
        R apply(T argument) throws E;
    }

    /**
     * Functional interface for a BiFunction that throws a checked exception.
     */
    @FunctionalInterface
    public interface CheckedBiFunction<T, U, R, E extends Exception> {
        R apply(T firstArgument, U secondArgument) throws E;
    }

    /**
     * Functional interface for a Supplier that throws a checked exception.
     */
    @FunctionalInterface
    public interface CheckedSupplier<T, E extends Exception> {
        T get() throws E;
    }
}
