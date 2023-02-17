package in.frol.frutils.helpers;

import java.util.Locale;
import java.util.TimeZone;

public final class DefaultTimeZoneInitializer {

    private static final String DEFAULT_TIME_ZONE_NAME = "UTC";
    private static final Locale DEFAULT_LOCALE = Locale.US;

    private DefaultTimeZoneInitializer() {
        /* empty body */
    }

    public static void init() {
        Locale.setDefault(DEFAULT_LOCALE);
        TimeZone.setDefault(TimeZone.getTimeZone(DEFAULT_TIME_ZONE_NAME));
    }
}
