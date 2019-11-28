package duedate;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DueDateCalculatorTest {

    private final Date startDate = new GregorianCalendar(2019, Calendar.NOVEMBER, 28, 10, 12).getTime();
    private final int withinADayWorkingHours = 4;
    private final Date withinADayEndDate = new GregorianCalendar(2019, Calendar.NOVEMBER, 28, 14, 12).getTime();

    @Test
    public void testWithinADay() {
        Assert.assertEquals(withinADayEndDate, DueDateCalculator.getDueDate(startDate, withinADayWorkingHours));
    }
}
