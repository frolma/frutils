package in.frol.frutils;

import in.frol.frutils.helpers.DefaultTimeZoneInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("java.util.Date Utils:")
class UtilDatesTest {

    /* Fri Sep 10 23:51:23 UTC 1976 */
    static final Date TEST_UTIL_DATA = new Date(211247483639L);
    /* Fri Feb 17 23:51:23 UTC 2023 */
    static final Date TEST_UTIL_DATA_AFTER = new Date(1676677883639L);
    static final Date TEST_UTIL_DATA_TRUNCATED_TO_DAY = new Date(1676592000000L);
    static final LocalDateTime TEST_LOCAL_DATE_TIME = LocalDateTime.of(1976, 9, 10, 23, 51, 23, 639000000);

    static {
        DefaultTimeZoneInitializer.init();
    }

    @Test
    void toLocalDateTime() {
        assertEquals(TEST_LOCAL_DATE_TIME, UtilDates.toLocalDateTime(TEST_UTIL_DATA));
    }

    @Test
    void toLocalDate() {
        assertEquals(TEST_LOCAL_DATE_TIME.toLocalDate(), UtilDates.toLocalDate(TEST_UTIL_DATA));
    }

    @Test
    void toLocalTime() {
        assertEquals(TEST_LOCAL_DATE_TIME.toLocalTime(), UtilDates.toLocalTime(TEST_UTIL_DATA));
    }

    @Test
    void toDate() {
        assertEquals(TEST_UTIL_DATA, UtilDates.toDate(TEST_LOCAL_DATE_TIME));
    }

    @Test
    void dateToLocalDate() {
        assertEquals(UtilDates.truncateToDay(TEST_UTIL_DATA), UtilDates.toDate(TEST_LOCAL_DATE_TIME.toLocalDate()));
    }

    @Test
    void format() {
        String result = UtilDates.format(TEST_UTIL_DATA, "yyyy MM dd hh mm ss ccc");
        assertEquals("1976 09 10 11 51 23 Fri", result);
    }

    @Test
    void formatDate() {
        String result = UtilDates.formatDate(TEST_UTIL_DATA);
        assertEquals("1976-09-10", result);
    }

    @Test
    void formatDateTime() {
        String result = UtilDates.formatDateTime(TEST_UTIL_DATA);
        assertEquals("1976-09-10 23:51:23", result);
    }

    @Test
    void daysBetween() {
        long days = UtilDates.daysBetween(TEST_UTIL_DATA, TEST_UTIL_DATA_AFTER);
        assertEquals(16961, days);
    }

    @Test
    void addDays() {
        long days = UtilDates.daysBetween(UtilDates.addDays(TEST_UTIL_DATA, 16961), TEST_UTIL_DATA_AFTER);
        assertEquals(0, days);
    }

    @Test
    void addMonths() {
        long days = UtilDates.daysBetween(UtilDates.addMonths(TEST_UTIL_DATA, 10), TEST_UTIL_DATA_AFTER);
        assertEquals(16658, days);
    }

    @Test
    void addYears() {
        long days = UtilDates.daysBetween(UtilDates.addYears(TEST_UTIL_DATA, 1), TEST_UTIL_DATA_AFTER);
        assertEquals(16596, days);
    }

    @Test
    void truncateToDay() {
        assertEquals(TEST_UTIL_DATA_TRUNCATED_TO_DAY, UtilDates.truncateToDay(TEST_UTIL_DATA_AFTER));
    }

    @Test
    void truncateTo() {
        assertEquals(TEST_UTIL_DATA_TRUNCATED_TO_DAY, UtilDates.truncateTo(TEST_UTIL_DATA_AFTER, ChronoUnit.DAYS));
    }

    @Test
    void isSameDay() {
        assertTrue(UtilDates.isSameDay(new Date(TEST_UTIL_DATA_TRUNCATED_TO_DAY.getTime()), new Date(TEST_UTIL_DATA_TRUNCATED_TO_DAY.getTime())));
        assertTrue(UtilDates.isSameDay(new Date(TEST_UTIL_DATA_AFTER.getTime()), new Date(TEST_UTIL_DATA_AFTER.getTime())));
        assertTrue(UtilDates.isSameDay(new Date(TEST_UTIL_DATA.getTime()), new Date(TEST_UTIL_DATA.getTime())));
    }
}