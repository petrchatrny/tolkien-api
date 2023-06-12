package cz.mendelu.pef.xchatrny.tolkiendictionaryapi.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {
    public static LocalDateTime unixToLocalDateTime(Long unixTime) {
        Instant instant = Instant.ofEpochSecond(unixTime);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
