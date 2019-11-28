package it;

import duedate.DueDateCalculator;
import duedate.exception.InvalidStartDateException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DueDateCalculatorIT {

    @Test
    public void test19Nov1Friday8H() throws InvalidStartDateException {
        Calendar nov1_9000_Friday = new GregorianCalendar(2019, Calendar.NOVEMBER, 1, 9, 0);
        Calendar nov4_9000_Monday = new GregorianCalendar(2019, Calendar.NOVEMBER, 4, 9, 0);
        Date dueDate = DueDateCalculator.calcDueDate(nov1_9000_Friday.getTime(), 8);
        Assert.assertEquals(nov4_9000_Monday.getTime(), dueDate);
    }

    @Test
    public void test19Okt31Thursday12H() throws InvalidStartDateException {
        Calendar oct31_9000_Thursday = new GregorianCalendar(2019, Calendar.OCTOBER, 31, 9, 0);
        Calendar nov1_1300_Monday = new GregorianCalendar(2019, Calendar.NOVEMBER, 1, 13, 0);
        Date dueDate = DueDateCalculator.calcDueDate(oct31_9000_Thursday.getTime(), 12);
        Assert.assertEquals(nov1_1300_Monday.getTime(), dueDate);
    }

    @Test
    public void test19Nov1Friday50H() throws InvalidStartDateException {
        Calendar nov1_9000_Friday = new GregorianCalendar(2019, Calendar.NOVEMBER, 1, 9, 0);
        Calendar nov11_1100_Monday = new GregorianCalendar(2019, Calendar.NOVEMBER, 11, 11, 0);
        Date dueDate = DueDateCalculator.calcDueDate(nov1_9000_Friday.getTime(), 50);
        Assert.assertEquals(nov11_1100_Monday.getTime(), dueDate);
    }

    @Test
    public void test0H() throws InvalidStartDateException {
        Calendar nov1_9000_Friday = new GregorianCalendar(2019, Calendar.NOVEMBER, 1, 9, 0);
        Date dueDate = DueDateCalculator.calcDueDate(nov1_9000_Friday.getTime(), 0);
        Assert.assertEquals(nov1_9000_Friday.getTime(), dueDate);
    }
}
