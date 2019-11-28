package com.tbugardev.duedate.calc;

import com.tbugardev.duedate.calc.exception.InvalidStartDateException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.tbugardev.duedate.calc.DueDateHelper.isItWeekend;

public class DueDateCalculator {
    private static final int startWorkingHour = 9;
    private static final int endWorkingHour = 17;

    public static Date calcDueDate(Date startDate, int workingHours) throws InvalidStartDateException {
        if (startDate == null) throw new InvalidStartDateException("startDate is must not be null");

        Calendar start = GregorianCalendar.getInstance();
        start.setTime(startDate);

        checkStartDay(start.get(Calendar.DAY_OF_WEEK));
        checkStartHour(start.get(Calendar.HOUR_OF_DAY));

        WorkInterval workInterval = new WorkInterval(start, startWorkingHour, endWorkingHour);
        workInterval.addHours(workingHours);

        return workInterval.getDueDate().getTime();
    }

    private static void checkStartDay(int startDay) throws InvalidStartDateException {
        if (isItWeekend(startDay)) throw new InvalidStartDateException("Working hours must be start on working days");
    }

    private static void checkStartHour(int startHourParam) throws InvalidStartDateException {
        if (startHourParam < startWorkingHour || startHourParam > endWorkingHour) throw new InvalidStartDateException("Working hours are from " + startWorkingHour +" to " + endWorkingHour);
    }
}
