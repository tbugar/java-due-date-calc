package com.tbugardev.duedate.calc;

import com.tbugardev.duedate.calc.exception.InvalidStartDateException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DueDateCalculatorTest {

    private final Date invalidStartDate = new GregorianCalendar(2019, Calendar.NOVEMBER, 28, 8, 12).getTime();
    private final Date nonWorkingDay = new GregorianCalendar(2019, Calendar.NOVEMBER, 30, 10, 0).getTime();
    private final int withinADayWorkingHours = 4;

    @Test(expected = InvalidStartDateException.class)
    public void testInvalidStartDateThrowsException() throws InvalidStartDateException {
        DueDateCalculator.calcDueDate(invalidStartDate, withinADayWorkingHours);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullStartDateThrowsException() throws InvalidStartDateException {
        DueDateCalculator.calcDueDate(null, withinADayWorkingHours);
    }

    @Test(expected = InvalidStartDateException.class)
    public void testNonWorkingHourStartTimeThrowsException() throws InvalidStartDateException {
        DueDateCalculator.calcDueDate(nonWorkingDay, withinADayWorkingHours);
    }
}
