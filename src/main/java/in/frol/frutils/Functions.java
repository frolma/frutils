package in.frol.frutils;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Utility class for providing no-op implementations of functional interfaces,
 * wrappers for non-checked exceptions and more
 */
public class Functions {

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

    /** No-op supplier plug: always null */
    public static <T> Supplier<T> emptySupplier() {
        return () -> null;
    }

    /** Alias for {@linkplain Functions#emptySupplier()} */
    public static <T> Supplier<T> nullSupplier() {
        return () -> null;
    }

    /**
     * No-op supplier plug: always null
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
    public static <T, U> BinaryOperator<T> emptyBinaryOperator() {
        return (firstArgument, secondArgument) -> firstArgument;
    }

    /** Alias for {@linkplain Functions#emptyBinaryOperator()} */
    public static <T, U> BinaryOperator<T> firstBinaryOperator() {
        return (firstArgument, secondArgument) -> firstArgument;
    }

    /** No-op binary operator plug: always second input argument */
    public static <T, U> BinaryOperator<T> secondBinaryOperator() {
        return (firstArgument, secondArgument) -> secondArgument;
    }

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

    @FunctionalInterface
    public interface CheckedConsumer<T, E extends Exception> {
        void accept(T argument) throws E;
    }

    @FunctionalInterface
    public interface CheckedBiConsumer<T, U, E extends Exception> {
        void accept(T firstArgument, U secondArgument) throws E;
    }

    @FunctionalInterface
    public interface CheckedFunction<T, R, E extends Exception> {
        R apply(T argument) throws E;
    }

    @FunctionalInterface
    public interface CheckedBiFunction<T, U, R, E extends Exception> {
        R apply(T firstArgument, U secondArgument) throws E;
    }
}
