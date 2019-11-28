package duedate;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WorkIntervalTest {

    private final Calendar nov21_1012_Thursday = new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 10, 12);

    @Test
    public void testAddHoursThroughDay() {
        WorkInterval workInterval = new WorkInterval(nov21_1012_Thursday);
        workInterval.addHours(9);
        Assert.assertEquals(11, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(12, workInterval.getDueDate().get(Calendar.MINUTE));
        Assert.assertEquals(22, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursInDay() {
        WorkInterval workInterval = new WorkInterval(nov21_1012_Thursday);
        workInterval.addHours(4);
        Assert.assertEquals(14, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(21, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursUntilEndHourWithMinutes() {
        WorkInterval workInterval = new WorkInterval(nov21_1012_Thursday);
        workInterval.addHours(7);
        Assert.assertEquals(9, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(22, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursUntilEndHourWithZeroMinutes() {
        Calendar nov21_1000_Thursday = nov21_1012_Thursday;
        nov21_1000_Thursday.set(Calendar.MINUTE, 0);
        WorkInterval workInterval = new WorkInterval(nov21_1000_Thursday);
        workInterval.addHours(7);
        Assert.assertEquals(17, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(21, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursThroughWeek(){
        Calendar nov22_1012_Friday = nov21_1012_Thursday;
        nov22_1012_Friday.set(Calendar.DAY_OF_MONTH, 22);
        WorkInterval workInterval = new WorkInterval(nov22_1012_Friday);
        workInterval.addHours(7);
        Assert.assertEquals(9, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(25, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddHoursThroughMonth() {
        Calendar nov29_1012_Friday = nov21_1012_Thursday;
        nov29_1012_Friday.set(Calendar.DAY_OF_MONTH, 29);
        WorkInterval workInterval = new WorkInterval(nov29_1012_Friday);
        workInterval.addHours(7);
        Assert.assertEquals(9, workInterval.getDueDate().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(2, workInterval.getDueDate().get(Calendar.DAY_OF_MONTH));
    }

}
