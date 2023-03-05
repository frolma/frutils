package in.frol.frutils;

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
}
