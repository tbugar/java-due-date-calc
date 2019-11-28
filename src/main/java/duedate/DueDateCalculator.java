package duedate;

import duedate.exception.InvalidStartDateException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DueDateCalculator {
    private static final int startWorkingHour = 9;
    private static final int endWorkingHour = 17;

    public static Date calcDueDate(Date startDate, int workingHours) throws InvalidStartDateException {
        if (startDate == null) throw new InvalidStartDateException("startDate is must not be null");

        Calendar c = GregorianCalendar.getInstance();
        c.setTime(startDate);
        checkStartDate(c.get(Calendar.HOUR_OF_DAY));

        WorkInterval workInterval = new WorkInterval(c, startWorkingHour, endWorkingHour);
        workInterval.addHours(workingHours);

        return workInterval.getDueDate().getTime();
    }

    private static void checkStartDate(int startHourParam) throws InvalidStartDateException {
        if (startHourParam < startWorkingHour || startHourParam > endWorkingHour) throw new InvalidStartDateException("Working hours are from " + startWorkingHour +" to " + endWorkingHour);
    }
}
