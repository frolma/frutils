package in.frol.frutils;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isWhitespace;

/**
 * Anything not found elsewhere for String or the method names seemed inappropriate.
 */
public final class Strings {

    private static final char DECIMAL_SEPARATOR = '.';
    private static final char MINUS_SIGN = '-';

    private Strings() {
        /* empty body */
    }

    /** string length or 0 if the value is null */
    public static int length(final String string) {
        return string == null
                ? 0
                : string.length();
    }

    /** {@code true} if the string contains at least one non-whitespace symbol */
    public static boolean notBlank(final String string) {
        if (string == null) {
            return false;
        }
        if (string.length() == 0) {
            return false;
        }
        for (int symbolIndex = 0; symbolIndex < string.length(); symbolIndex++) {
            if (isWhitespace(string.charAt(symbolIndex))) {
                continue;
            }
            return true;
        }
        return false;
    }

    /** Returns {@code true} if the string does not contain any non-whitespace symbol */
    public static boolean isBlank(final String string) {
        return !notBlank(string);
    }

    /** Returns {@code true} if the string contains only alphanumeric characters */
    public static boolean isAlphaNumeric(final String string) {
        if (isBlank(string)) {
            return false;
        }
        for (int symbolIndex = 0; symbolIndex < string.length(); symbolIndex++) {
            if (isLetterOrDigit(string.charAt(symbolIndex))) {
                continue;
            }
            return false;
        }
        return true;
    }

    /** Returns {@code false} if the string contains only alphanumeric characters */
    public static boolean notAlphaNumeric(final String string) {
        return !isAlphaNumeric(string);
    }

    /**
     * Returns {@code true} if the string contains only numeric characters
     * <p>
     * by default decimal separator is only expected as .(dot)
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/math/BigDecimal.html#%3Cinit%3E(java.lang.String)">
     * docs.oracle: BigDecimal constructor - </a>
     */
    public static boolean isNumeric(final String string) {
        return checkIfStringIsNumeric(string, false);
    }

    /** Returns {@code false} if the string contains only numeric characters */
    public static boolean notNumeric(final String string) {
        return !isNumeric(string);
    }

    /** Extended version of isNumeric with '-' sign */
    public static boolean isSignedNumeric(final String string) {
        return checkIfStringIsNumeric(string, true);
    }

    /** Extended version of notNumeric with '-' sign */
    public static boolean notSignedNumeric(final String string) {
        return !isSignedNumeric(string);
    }

    private static boolean checkIfStringIsNumeric(final String string, final boolean isSigned) {
        if (isBlank(string)) {
            return false;
        }
        boolean isMetFloatingPoint = false;
        boolean isMetMinusSign = false;
        for (int symbolIndex = 0; symbolIndex < string.length(); symbolIndex++) {
            char currentChar = string.charAt(symbolIndex);
            if (!isDigit(currentChar)
                    && currentChar != DECIMAL_SEPARATOR
                    && (!isSigned || currentChar != MINUS_SIGN)) {
                return false;
            }
            if (currentChar == DECIMAL_SEPARATOR) {
                if (isMetFloatingPoint) {
                    return false;
                }
                isMetFloatingPoint = true;
            }
            if (isSigned && currentChar == MINUS_SIGN) {
                if (isMetMinusSign) {
                    return false;
                }
                isMetMinusSign = true;
            }
        }
        return true;
    }
}
