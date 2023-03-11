package in.frol.frutils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

@DisplayName("Strings:")
class StringsTest {

    static Stream<Arguments> isBlankSource() {
        return Stream.of(
                of(null, true),
                of("", true),
                of(" ", true),
                of("  ", true),
                of("   ", true),
                of("    ", true),
                of("\n", true),
                of("\t", true),
                of("\n\n\t\n", true),
                of("t", false),
                of("te", false),
                of("tes", false),
                of("test", false),
                of("test:", false),
                of(":", false),
                of("_", false),
                of("&", false),
                of("*", false),
                of(")", false),
                of("-", false)
        );
    }

    static Stream<Arguments> isNotBlankSource() {
        return Stream.of(
                of(null, false),
                of("", false),
                of(" ", false),
                of("  ", false),
                of("   ", false),
                of("    ", false),
                of("\n", false),
                of("\t", false),
                of("\n\n\t\n", false),
                of("t", true),
                of("te", true),
                of("tes", true),
                of("test", true),
                of("test:", true),
                of(":", true),
                of("_", true),
                of("&", true),
                of("*", true),
                of(")", true),
                of("-", true)
        );
    }

    static Stream<Arguments> isNumericSource() {
        return Stream.of(
                of(null, false),
                of("", false),
                of(" ", false),
                of("  ", false),
                of("   ", false),
                of("    ", false),
                of("\n", false),
                of("\t", false),
                of("\n\n\t\n", false),
                of("v", false),
                of("va", false),
                of("val", false),
                of("valu", false),
                of("value", false),
                of("fff131311", false),
                of("131311fdfdg", false),
                of("0", true),
                of("0.121212", true),
                of("0121212.", true),
                of(".121212", true),
                of("123", true),
                of("0.121212g", false),
                of("0.121212e", false),
                of("0,121212", false),
                of("325,4343", false)
        );
    }

    static Stream<Arguments> isSignedNumericSource() {
        return Stream.of(
                of(null, false),
                of("", false),
                of(" ", false),
                of("  ", false),
                of("   ", false),
                of("    ", false),
                of("\n", false),
                of("\t", false),
                of("\n\n\t\n", false),
                of("v", false),
                of("va", false),
                of("val", false),
                of("valu", false),
                of("value", false),
                of("fff131311", false),
                of("131311fdfdg", false),
                of("0", true),
                of("0.121212", true),
                of("0121212.", true),
                of(".121212", true),
                of("123", true),
                of("-123", true),
                of("-123.45", true),
                of("-123.", true),
                of("-.1212", true),
                of("0.121212g", false),
                of("0.121212e", false),
                of("0,121212", false),
                of("325,4343", false),
                of("--325,4343", false),
                of("..325,4343", false)
        );
    }

    static Stream<Arguments> isAlphaNumeric() {
        return Stream.of(
                of(null, false),
                of("", false),
                of(" ", false),
                of("  ", false),
                of("   ", false),
                of("    ", false),
                of(" AlphaNumeric", false),
                of(" alphanumeric", false),
                of("AlphaNumeric ", false),
                of("Alpha Numeric", false),
                of("_AlphaNumeric", false),
                of("AlphaNumeric_", false),
                of("Alpha_Numeric", false),
                of("-AlphaNumeric", false),
                of("AlphaNumeric=", false),
                of("Alpha*Numeric", false),
                of("Alpha Numeric)", false),
                of("(AlphaNumeric)", false),
                of("AlphaNumeric", true),
                of("Alpha", true),
                of("Numeric", true),
                of("Num", true),
                of("N", true)
        );
    }


    @DisplayName("isBlank:")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @MethodSource("isBlankSource")
    void isBlank(String value, boolean expected) {
        assertEquals(expected, Strings.isBlank(value));
    }

    @DisplayName("isNotBlank:")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @MethodSource("isNotBlankSource")
    void isNotBlank(String value, boolean expected) {
        assertEquals(expected, Strings.notBlank(value));
    }

    @DisplayName("isNumeric:")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @MethodSource("isNumericSource")
    void isNumeric(String value, boolean expected) {
        assertEquals(expected, Strings.isNumeric(value));
        assertEquals(!expected, Strings.notNumeric(value));
    }

    @DisplayName("isNumeric:")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @MethodSource("isSignedNumericSource")
    void isSignedNumeric(String value, boolean expected) {
        assertEquals(expected, Strings.isSignedNumeric(value));
        assertEquals(!expected, Strings.notSignedNumeric(value));
    }

    @DisplayName("isAlphaNumeric:")
    @ParameterizedTest(name = "{displayName} [{index}] {argumentsWithNames}")
    @MethodSource("isAlphaNumeric")
    void isAlphaNumeric(String value, boolean expected) {
        assertEquals(expected, Strings.isAlphaNumeric(value));
        assertEquals(!expected, Strings.notAlphaNumeric(value));
    }

    @DisplayName("length:")
    @ParameterizedTest
    @CsvSource(value = {
            "9, stringify",
            "8, stringif",
            "3, str",
            "2, st",
            "1, s",
            "0, "
    })
    void length(int expected, String value) {
        assertEquals(expected, Strings.length(value));
    }
}