package com.tbugardev.duedate.calc;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.tbugardev.duedate.calc.DueDateHelper.isItWeekend;

/**
 * Determines due date by the following requirements:
 * - weekdays are 8 h work hour long --> 8 h time = 1 day
 * - weekends are ignored --> Monday comes after Friday
 * Due date is initialized with submit date and updated according to turnaround time
 */
class WorkInterval {

    private final Calendar dueDate;
    private final int startWorkHour;
    private final int endWorkHour;
    private final int workingHoursPerDay;

    WorkInterval(Calendar startDate) {
        this(startDate, 9, 17);
    }

    WorkInterval(final Calendar startDate, int startWorkHour, int endWorkHour) {
        this.dueDate = GregorianCalendar.getInstance();
        this.dueDate.setTime(startDate.getTime());
        this.startWorkHour = startWorkHour;
        this.endWorkHour = endWorkHour;
        workingHoursPerDay = endWorkHour - startWorkHour;
    }

    Calendar getDueDate() {
        return dueDate;
    }

    /**
     * Adds hours to due date considering the working hours
     *
     * @param hours Number of hours to add
     */
    void addHours(int hours) {
        int daysToAdd = hours / workingHoursPerDay;
        int currentEndHour = dueDate.get(Calendar.HOUR_OF_DAY) + hours % workingHoursPerDay;
        if (currentEndHour > endWorkHour
                || (currentEndHour == endWorkHour && dueDate.get(Calendar.MINUTE) > 0)) {
            daysToAdd++;
            currentEndHour = currentEndHour - endWorkHour + startWorkHour;
        }
        addDays(daysToAdd);
        dueDate.set(Calendar.HOUR_OF_DAY, currentEndHour);
    }

    /**
     * Adds days to due date considering the workdays
     * If due date is a weekend day, then next Monday will be chosen
     *
     * @param days Number of days to add
     */
    private void addDays(int days) {
        int workingDaysPerWeek = 5;
        int fullWeeks = days / workingDaysPerWeek;
        dueDate.set(Calendar.DAY_OF_YEAR, dueDate.get(Calendar.DAY_OF_YEAR) + fullWeeks * 7);
        int remainingDays = days % workingDaysPerWeek;
        while (remainingDays > 0 || isItWeekend(dueDate.get(Calendar.DAY_OF_WEEK))) {
            if (!isItWeekend(dueDate.get(Calendar.DAY_OF_WEEK))) {
                remainingDays--;
            }
            dueDate.set(Calendar.DAY_OF_YEAR, dueDate.get(Calendar.DAY_OF_YEAR) + 1);
        }
    }
}
