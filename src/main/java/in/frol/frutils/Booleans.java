package in.frol.frutils;

/* Plain boolean static methods */
public class Booleans {

    public static boolean neTrue(final Boolean value) {
        return !eqTrue(value);
    }

    public static boolean eqTrue(final Boolean value) {
        return Boolean.TRUE.equals(value);
    }
}
