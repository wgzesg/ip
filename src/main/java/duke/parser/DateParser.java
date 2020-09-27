package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {

    // Accepted valid forms of string that can be properly parsed
    private static final List<DateTimeFormatter> dtFormaters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));

    private static final List<DateTimeFormatter> dFormaters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy MM dd"));

    /**
     * Parse a given string following one of the accepted format into date-time format.
     * @param command String input to be parsed
     * @return Parsed result in the form of LocalDateTime or null if no value parsing is found
     */
    public static LocalDateTime parseDate(String command) {
        for (DateTimeFormatter dtf : dtFormaters) {
            try {
                return LocalDateTime.parse(command, dtf);
            } catch (DateTimeParseException e) {
            }
        }

        for (DateTimeFormatter dtf : dFormaters) {
            try {
                return LocalDate.parse(command, dtf).atStartOfDay();
            } catch (DateTimeParseException e) {
            }
        }

        return null;
    }
}
