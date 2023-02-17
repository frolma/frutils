package in.frol.frutils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Utility class with static methods for working with
 * {@link Date} objects and the Java 8 Date-Time API.
 */
public final class UtilDates {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private UtilDates() {
        /* empty body */
    }

    /**
     * Converts {@link Date} object to {@link LocalDateTime} object.
     *
     * @param utilDate date to convert
     * @return date as a local date and time in the system default time zone
     */
    public static LocalDateTime toLocalDateTime(final Date utilDate) {
        return utilDate.toInstant()
                .atZone(DEFAULT_ZONE_ID)
                .toLocalDateTime();
    }

    /**
     * Converts {@link Date} object to {@link LocalDate} object.
     *
     * @param utilDate date to convert
     * @return date as a local date in the system default time zone
     */
    public static LocalDate toLocalDate(final Date utilDate) {
        return utilDate.toInstant()
                .atZone(DEFAULT_ZONE_ID)
                .toLocalDate();
    }

    /**
     * Converts {@link Date} object to {@link LocalTime} object.
     *
     * @param utilDate date to convert
     * @return time as a local time in the system default time zone
     */
    public static LocalTime toLocalTime(final Date utilDate) {
        return utilDate.toInstant()
                .atZone(DEFAULT_ZONE_ID)
                .toLocalTime();
    }

    /**
     * Converts {@link LocalDate} object to {@link Date} object.
     *
     * @param localDate local date to convert
     * @return local date as a date in the system default time zone
     */
    public static Date toDate(final LocalDate localDate) {
        return Date.from(localDate
                .atStartOfDay(DEFAULT_ZONE_ID)
                .toInstant());
    }

    /**
     * Converts {@link LocalDateTime} object to {@link Date} object.
     *
     * @param localDateTime local date and time to convert
     * @return local date and time as a date in the system default time zone
     */
    public static Date toDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime
                .atZone(DEFAULT_ZONE_ID)
                .toInstant());
    }

    /**
     * Formats {@link Date} object as a string using the specified pattern.
     *
     * @param utilDate date to format
     * @param pattern  pattern to use for formatting via {@link DateTimeFormatter}
     * @return formatted date string
     */
    public static String format(final Date utilDate, final String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = toLocalDateTime(utilDate);
        return localDateTime.format(formatter);
    }

    /**
     * Formats {@link Date} object as a string using default date format.
     *
     * @param utilDate date to format
     * @return formatted date string
     */
    public static String formatDate(final Date utilDate) {
        return format(utilDate, DEFAULT_DATE_FORMAT);
    }

    /**
     * Formats {@link Date} object as a string using default datetime format.
     *
     * @param utilDate date to format
     * @return formatted date with time string
     */
    public static String formatDateTime(final Date utilDate) {
        return format(utilDate, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * Counts days between two {@link Date} objects from inclusive to exclusive.
     *
     * @param startDate from inclusive
     * @param endDate   to exclusive
     * @return counted days
     */
    public static long daysBetween(final Date startDate, final Date endDate) {
        LocalDate startLocalDate = toLocalDate(startDate);
        LocalDate endLocalDate = toLocalDate(endDate);
        return ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
    }

    /**
     * Add days to {@link Date} objects.
     *
     * @param utilDate source date
     * @param days     number of days
     * @return result date
     */
    public static Date addDays(final Date utilDate, int days) {
        LocalDate localDate = toLocalDate(utilDate);
        localDate = localDate.plusDays(days);
        return toDate(localDate);
    }

    /**
     * Add months to {@link Date} objects.
     *
     * @param utilDate source date
     * @param months   number of months
     * @return result date
     */
    public static Date addMonths(final Date utilDate, int months) {
        LocalDate localDate = toLocalDate(utilDate);
        localDate = localDate.plusMonths(months);
        return toDate(localDate);
    }

    /**
     * Add years to {@link Date} objects.
     *
     * @param utilDate source date
     * @param years    number of years
     * @return result date
     */
    public static Date addYears(final Date utilDate, int years) {
        LocalDate localDate = toLocalDate(utilDate);
        localDate = localDate.plusYears(years);
        return toDate(localDate);
    }

    /**
     * Truncate {@link Date} object to day.
     *
     * @param utilDate source date
     * @return truncated date
     */
    public static Date truncateToDay(final Date utilDate) {
        return truncateTo(utilDate, ChronoUnit.DAYS);
    }

    /**
     * Truncate {@link Date} object to {@linkplain ChronoUnit}.
     *
     * @param utilDate   source date
     * @param chronoUnit unit
     * @return truncated date
     */
    public static Date truncateTo(final Date utilDate, final ChronoUnit chronoUnit) {
        LocalDateTime localDateTime = toLocalDateTime(utilDate);
        localDateTime = localDateTime.truncatedTo(chronoUnit);
        return toDate(localDateTime);
    }

    /**
     * Compare two {@link Date} objects and return true if dates are equal.
     *
     * @param leftUtilDate  first date
     * @param rightUtilDate second date
     * @return true if same day
     */
    public static boolean isSameDay(final Date leftUtilDate, final Date rightUtilDate) {
        LocalDate left = toLocalDate(leftUtilDate);
        LocalDate right = toLocalDate(rightUtilDate);
        return left.isEqual(right);
    }
}
