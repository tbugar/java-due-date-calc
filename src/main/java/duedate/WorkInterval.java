package duedate;

import java.util.Calendar;
import java.util.GregorianCalendar;

class WorkInterval {

    private final Calendar dueDate;
    private final int startWorkHour;
    private final int endWorkHour;

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
        addDays(hours / 8);
        int currentEndHour = getHours() + hours % 8;
        if (currentEndHour > endWorkHour
                || (currentEndHour == endWorkHour && getMinutes() > 0)) {
            addDays(1);
            currentEndHour = currentEndHour-endWorkHour+startWorkHour;
        }
        dueDate.set(Calendar.HOUR_OF_DAY, currentEndHour);
    }

    private int getMinutes() {
        return dueDate.get(Calendar.MINUTE);
    }

    private int getHours() {
        return dueDate.get(Calendar.HOUR_OF_DAY);
    }

    private int getDays() {
        return dueDate.get(Calendar.DAY_OF_WEEK);
    }

    private void addDays(int days) {
        dueDate.set(Calendar.DAY_OF_WEEK, getDays() + days);
    }


}
