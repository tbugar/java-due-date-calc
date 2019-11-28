package duedate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static duedate.DueDateHelper.isItWeekend;

class WorkInterval {

    private final Calendar dueDate;
    private final int startWorkHour;
    private final int endWorkHour;

    WorkInterval(Calendar startDate) {
        this(startDate, 9, 17);
    }

    WorkInterval(final Calendar startDate, int startWorkHour, int endWorkHour) {
        this.dueDate = GregorianCalendar.getInstance();
        this.dueDate.setTime(startDate.getTime());
        this.startWorkHour = startWorkHour;
        this.endWorkHour = endWorkHour;
    }

    Calendar getDueDate() {
        return dueDate;
    }

    void addHours(int hours) {
        int daysToAdd = hours / 8;
        int currentEndHour = dueDate.get(Calendar.HOUR_OF_DAY) + hours % 8;
        if (currentEndHour > endWorkHour
                || (currentEndHour == endWorkHour && dueDate.get(Calendar.MINUTE) > 0)) {
            daysToAdd++;
            currentEndHour = currentEndHour - endWorkHour + startWorkHour;
        }
        addDays(daysToAdd);
        dueDate.set(Calendar.HOUR_OF_DAY, currentEndHour);
    }

    private void addDays(int days) {
        int fullWeeks = days / 5;
        dueDate.set(Calendar.DAY_OF_YEAR, dueDate.get(Calendar.DAY_OF_YEAR) + fullWeeks * 7);
        int remainingDays = days % 5;
        while (remainingDays > 0 || isItWeekend(dueDate.get(Calendar.DAY_OF_WEEK))) {
            if (!isItWeekend(dueDate.get(Calendar.DAY_OF_WEEK))) {
                remainingDays--;
            }
            dueDate.set(Calendar.DAY_OF_YEAR, dueDate.get(Calendar.DAY_OF_YEAR) + 1);
        }
    }
}
