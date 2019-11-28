package duedate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DueDateCalculator {
    public static Date getDueDate(Date startDate, int workingHours) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + workingHours);
        return c.getTime();
    }
}
