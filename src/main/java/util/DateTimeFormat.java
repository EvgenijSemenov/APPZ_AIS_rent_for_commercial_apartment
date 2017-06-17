package util;

import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
    public static DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }
}
