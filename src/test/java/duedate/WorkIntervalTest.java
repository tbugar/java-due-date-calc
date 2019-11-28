package duedate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WorkIntervalTest {

    private final Calendar startDate = new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 10, 12);
    private final Calendar startDateZeroMinutes = new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 10, 0);
    private final int startWorkingHour = 9;
    private final int endWorkingHour = 17;
    private WorkInterval workInterval;

    @Before
    public void setup() {
        workInterval = new WorkInterval(startDate, startWorkingHour, endWorkingHour);
    }

    @Test
    public void testAddHoursThroughDay() {
        workInterval.addHours(9);
        Assert.assertEquals(11, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(22, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursInDay() {
        workInterval.addHours(4);
        Assert.assertEquals(14, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(21, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursUntilEndHourWithMinutes() {
        workInterval.addHours(7);
        Assert.assertEquals(9, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(22, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursUntilEndHourWithZeroMinutes() {
        WorkInterval workIntervalZeroMinutes = new WorkInterval(startDateZeroMinutes, startWorkingHour, endWorkingHour);
        workIntervalZeroMinutes.addHours(7);
        Assert.assertEquals(17, workIntervalZeroMinutes.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(21, workIntervalZeroMinutes.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursThroughWeek(){
        workInterval.addHours(15);
        Assert.assertEquals(9, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(25, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursThroughMonth() {
        workInterval.addHours(15 + 40);
        Assert.assertEquals(9, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(2, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursThroughYear() {
        workInterval.addHours(15 + 216);
        Assert.assertEquals(9, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(1, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(Calendar.JANUARY, workInterval.getDueDate().get(Calendar.MONTH));
        Assert.assertEquals(2020, workInterval.getDueDate().get(Calendar.YEAR));
    }
}
