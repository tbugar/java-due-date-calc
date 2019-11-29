package com.tbugardev.duedate.calc;

import com.tbugardev.duedate.calc.exception.InvalidStartDateException;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.tbugardev.duedate.calc.DueDateHelper.isItWeekend;

/**
 * Calculate due date of an issue
 * Start time of work: 9 am
 * End time of work: 5 pm
 */
public class DueDateCalculator {
    private static final int startWorkingHour = 9;
    private static final int endWorkingHour = 17;

    /**
     * Calculate due date of issue starting starting from startDate with turnAroundHours duration
     *
     * @param startDate       Start time of the issue. Necessarily weekday with time between start-end times
     * @param turnAroundHours Duration time of the issue in hours
     * @return Due date
     * @throws InvalidStartDateException when startDate is not filled, not weekday or not in start-end time interval
     */
    public static Date calcDueDate(@NotNull Date startDate, int turnAroundHours) throws InvalidStartDateException {
        Calendar start = GregorianCalendar.getInstance();
        start.setTime(startDate);

        checkStartDay(start.get(Calendar.DAY_OF_WEEK));
        checkStartHour(start.get(Calendar.HOUR_OF_DAY));

        WorkInterval workInterval = new WorkInterval(start, startWorkingHour, endWorkingHour);
        workInterval.addHours(turnAroundHours);

        return workInterval.getDueDate().getTime();
    }

    private static void checkStartDay(int startDay) throws InvalidStartDateException {
        if (isItWeekend(startDay)) throw new InvalidStartDateException("Working hours must be start on working days");
    }

    private static void checkStartHour(int startHourParam) throws InvalidStartDateException {
        if (startHourParam < startWorkingHour || startHourParam > endWorkingHour)
            throw new InvalidStartDateException("Working hours are from " + startWorkingHour + " to " + endWorkingHour);
    }
}
