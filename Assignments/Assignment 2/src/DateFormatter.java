import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateFormatter class has all the methods to manipulate LocalDateTime.
 */
public class DateFormatter {
    public LocalDateTime dateTime;
    public DateTimeFormatter formatter;

    /**
     * @param pattern is the pattern of format.
     */
    public DateFormatter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * @param dateString is the String which has the date value.
     * @return it returns dateTime which is the correctly formatted date after matching and formatting operations.
     */
    public LocalDateTime getDateTime(String dateString) {
        try {
            dateTime = LocalDateTime.parse(dateString, formatter);
        }
        catch (Exception e) { // if formatting in standard format fails and throws exception, control goes to this block.
            if (dateString.matches("\\d{4}-\\d{2}-\\d{2}_\\d:\\d{2}:\\d{2}")) { // those if-else statements catch all possible formats
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_H:mm:ss");        // and formats them with corresponding pattern.
                dateTime = LocalDateTime.parse(dateString, formatter);
                formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]_HH:mm:ss");
            }
            else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d:\\d{2}")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:m:ss");
                dateTime = LocalDateTime.parse(dateString, formatter);
                formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]_HH:mm:ss");
            }
            else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:s");
                dateTime = LocalDateTime.parse(dateString, formatter);
                formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]_HH:mm:ss");
            }
            else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}_\\d:\\d:\\d{2}")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_H:m:ss");
                dateTime = LocalDateTime.parse(dateString, formatter);
                formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]_HH:mm:ss");
            }
            else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}_\\d:\\d{2}:\\d")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_H:mm:s");
                dateTime = LocalDateTime.parse(dateString, formatter);
                formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]_HH:mm:ss");
            }
            else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d:\\d")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:m:s");
                dateTime = LocalDateTime.parse(dateString, formatter);
                formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]_HH:mm:ss");
            }
            else if (dateString.matches("\\d{4}-\\d{2}-\\d{2}_\\d:\\d:\\d")) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_H:m:s");
                dateTime = LocalDateTime.parse(dateString, formatter);
                formatter = DateTimeFormatter.ofPattern("yyyy[-M][-d]_HH:mm:ss");
            }
            else { // this means you've really entered a wrong format
                throw new IllegalArgumentException("ERROR: Format of the initial date is wrong! Program is going to terminate!");
            }
        }
        return dateTime;
    }

    /**
     * @param dateTime is the LocalDateTime variable which is aimed to turn into String.
     * @return returns either a String of dateTime variable in requested format or null.
     */
    public String getFormattedDate(LocalDateTime dateTime) {
        try {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"); // to acquire the format needed
            return dateTime.format(outputFormatter);
        }
        catch (Exception e) { // flow goes there if an exception occurs, in this case dateTime being empty.
            return null;
        }
    }
}
