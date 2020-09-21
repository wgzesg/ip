package duke.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateParser {

    private static final List<DateTimeFormatter> dtformaters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));

    private static final List<DateTimeFormatter> dformaters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy MM dd"));

    public static LocalDateTime parseDate(String command) throws DateTimeParseException {
        for(DateTimeFormatter dtf : dtformaters) {
            try {
                return LocalDateTime.parse(command, dtf);
            } catch (DateTimeParseException e) {
            }
        }

        for(DateTimeFormatter dtf : dformaters) {
            try {
                return LocalDate.parse(command, dtf).atStartOfDay();
            } catch (DateTimeParseException e) {
            }
        }

        return null;
    }
}
