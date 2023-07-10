// for testing purpose
import java.time.LocalDate;

public class getDay {
    public static void main(String[] args) {
        // Set the desired month and year
        int year = 2023;
        int month = 8;

        // Create a LocalDate instance representing the first day of the desired month
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        // Get the starting day of the month (Sunday = 7, Monday = 1, Tuesday = 2,  etc.)
        int startingDay = firstDayOfMonth.getDayOfWeek().getValue();

        System.out.println("Starting day of the month: " + startingDay);
    }
}
