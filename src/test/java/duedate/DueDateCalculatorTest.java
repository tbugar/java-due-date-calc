package duedate;

import duedate.exception.DueDateCalcException;
import duedate.exception.InvalidStartDateException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DueDateCalculatorTest {

    private final Date invalidStartDate = new GregorianCalendar(2019, Calendar.NOVEMBER, 28, 8, 12).getTime();
    private final Date nonWorkingDay = new GregorianCalendar(2019, Calendar.NOVEMBER, 30, 10, 0).getTime();
    private final int withinADayWorkingHours = 4;

    @Test(expected = InvalidStartDateException.class)
    public void testInvalidStartDateThrowsException() throws DueDateCalcException {
        DueDateCalculator.calcDueDate(invalidStartDate, withinADayWorkingHours);
    }

    @Test(expected = InvalidStartDateException.class)
    public void testNullDueDateThrowsException() throws DueDateCalcException {
        DueDateCalculator.calcDueDate(null, withinADayWorkingHours);
    }

    @Test(expected = InvalidStartDateException.class)
    public void testReportNonWorkingHour() throws DueDateCalcException {
        DueDateCalculator.calcDueDate(nonWorkingDay, withinADayWorkingHours);
    }
}
