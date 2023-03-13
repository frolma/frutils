package in.frol.frutils;

import java.util.function.Supplier;

import static in.frol.frutils.Objects.nonNull;

/* Plain boolean static methods */
public class Booleans {

    /**
     * <pre>
     * if it is necessary to check the nullable Bool variable
     * if (!Boolean.TRUE.equals(object.getBoolean()) {
     *
     * try this instead:
     * if (neTrue(object.getBoolean()) {
     * </pre>
     */
    public static boolean neTrue(final Boolean value) {
        return !eqTrue(value);
    }

    /* Alias for {@linkplain Booleans#neTrue()} */
    public static boolean not(final Boolean value) {
        return neTrue(value);
    }

    /**
     * <pre>
     * if it is necessary to check the nullable Bool variable
     * if (Boolean.TRUE.equals(object.getBoolean()) {
     *
     * try this instead:
     * if (eqTrue(object.getBoolean()) {
     * </pre>
     */
    public static boolean eqTrue(final Boolean value) {
        return Boolean.TRUE.equals(value);
    }

    /* Alias for {@linkplain Booleans#eqTrue()} */
    public static boolean is(final Boolean value) {
        return eqTrue(value);
    }

    public static <T> T getIfNeTrue(final Boolean condition, final T valueIfFalse) {
        return neTrue(condition)
                ? valueIfFalse
                : null;
    }

    public static <T> T getIfTrue(final Boolean condition, final T valueIfTrue) {
        return eqTrue(condition)
                ? valueIfTrue
                : null;
    }

    public static <T> T supplyIfNeTrue(final Boolean condition, final Supplier<T> supplierIfFalse) {
        if (neTrue(condition)
                && nonNull(supplierIfFalse)) {
            return supplierIfFalse.get();
        }
        return null;
    }

    public static <T> T supplyIfTrue(final Boolean condition, final Supplier<T> supplierIfTrue) {
        if (eqTrue(condition)
                && nonNull(supplierIfTrue)) {
            return supplierIfTrue.get();
        }
        return null;
    }

    public static void runIfNeTrue(final Boolean condition, final Runnable runnableIfFalse) {
        if (neTrue(condition)
                && nonNull(runnableIfFalse)) {
            runnableIfFalse.run();
        }
    }

    public static void runIfTrue(final Boolean condition, final Runnable runnableIfTrue) {
        if (eqTrue(condition)
                && nonNull(runnableIfTrue)) {
            runnableIfTrue.run();
        }
    }
}
