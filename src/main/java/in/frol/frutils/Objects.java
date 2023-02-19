package in.frol.frutils;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Nullable objects, comparisons and more.
 * <p>
 * Methods à la SQL Server's COALESCE or ORACLE's NVL and other ISNULL functions,
 * in a convenient, concise and static "pack" for Java with lazy extensions.
 */
public class Objects {

    /**
     * Checks values for NotEqual Null('ne': null) by ordering
     * the input parameters and returns the first match.
     * <p>
     * neNull(null, notNullObject) == notNullObject
     * neNull(notNullObject, null) == notNullObject
     * neNull(nonNullObject, nonNullObject2) == notNullObject
     * neNull(null, "string") == "string"
     * neNull("string", null) == "string"
     * neNull("string", "string2") == "string"
     * neNull("string2", "string") == "string2"
     * neNull(null, 1) == 1
     * neNull(1, nul) == 1
     * neNull(1, 2) == 1
     *
     * @see Objects#neNull(Object, Object, Object)
     * @see Objects#neNull(Object[])
     */
    public static <T> T neNull(final T firstValue,
                               final T secondValue) {
        return firstValue == null
                ? secondValue
                : firstValue;
    }

    /**
     * Applies the function/extractor to a non-null
     * object and returns the result otherwise null
     * <p>
     * neNull()
     */
    public static <T, R> R neNull(final T firstValue,
                                  final Function<T, R> functionForNotNull) {
        if (firstValue == null) {
            return null;
        }
        return functionForNotNull == null
                ? null
                : functionForNotNull.apply(firstValue);
    }

    /**
     * If the parameter is null then returns the result of the Supplier
     *
     * @see Objects#neNull(Object, Supplier[])
     */
    public static <T> T neNull(final T firstValue,
                               final Supplier<T> supplierIfNull) {
        if (firstValue != null) {
            return firstValue;
        }
        return supplierIfNull == null
                ? null
                : supplierIfNull.get();
    }

    /**
     * Extended version of NotEqual Null - {@link Objects#neNull(Object, Object) }
     */
    public static <T> T neNull(final T firstValue,
                               final T secondValue,
                               final T thirdValue) {
        if (firstValue != null) {
            return firstValue;
        }
        if (secondValue != null) {
            return secondValue;
        }
        return thirdValue;
    }

    /** Varargs version of NotEqual Null {@link Objects#neNull(Object, Object) } */
    @SafeVarargs
    public static <T> T neNull(final T... objects) {
        if (objects == null) {
            return null;
        }
        for (final T current : objects) {
            if (current == null) {
                continue;
            }
            return current;
        }
        return null;
    }

    /** Varargs Lazy version of NotEqual {@link Objects#neNull(Object, Supplier)} */
    @SafeVarargs
    public static <T> T neNull(final T firstValue,
                               final Supplier<T>... suppliersIfNull) {
        if (firstValue != null) {
            return firstValue;
        }
        if (suppliersIfNull == null) {
            return null;
        }
        for (final Supplier<T> supplier : suppliersIfNull) {
            if (supplier == null) {
                continue;
            }
            final T current = supplier.get();
            if (current == null) {
                continue;
            }
            return current;
        }
        return null;
    }

    /** {@code true} if all objects are {@code null} */
    public static <T> boolean allNull(final T firstValue,
                                      final T secondValue) {
        return !anyNotNull(firstValue, secondValue);
    }

    /** Varargs version of {@link Objects#allNull(Object, Object)} */
    @SafeVarargs
    public static <T> boolean allNull(final T... values) {
        return !anyNotNull(values);
    }

    /** {@code true} if all objects are non-null */
    public static <T> boolean allNotNull(final T firstValue,
                                         final T secondValue) {
        if (firstValue == null) {
            return false;
        }
        return secondValue != null;
    }

    /** Varargs version of {@link Objects#allNotNull(Object, Object)} */
    @SafeVarargs
    public static <T> boolean allNotNull(final T... values) {
        if (values == null) {
            return false;
        }
        for (final T val : values) {
            if (val == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@code true} if any of the objects is {@code null}
     *
     * @see Objects#neNull(Object[])
     */
    public static <T> boolean anyNotNull(final T firstValue,
                                         final T secondValue) {
        return neNull(firstValue, secondValue) != null;
    }

    /** Varargs version of {@link Objects#anyNotNull(Object, Object)} */
    @SafeVarargs
    public static <T> boolean anyNotNull(final T... objects) {
        return neNull(objects) != null;
    }

    /** True if any of the objects is {@code null} */
    public static <T> boolean anyNull(final T firstValue,
                                      final T secondValue) {
        return !allNotNull(firstValue, secondValue);
    }

    /** Varargs version of {@link Objects#anyNull(Object, Object)} */
    @SafeVarargs
    public static <T> boolean anyNull(final T... objects) {
        return !allNotNull(objects);
    }

    /** Returns {@code true} if the object is {@code null} */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /** Returns {@code true} if the object is non-null */
    public static boolean nonNull(Object object) {
        return object != null;
    }
}
