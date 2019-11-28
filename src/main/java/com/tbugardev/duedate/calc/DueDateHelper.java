package com.tbugardev.duedate.calc;

import java.util.Calendar;

class DueDateHelper {
    static boolean isItWeekend(int startDay) {
        return startDay == Calendar.SATURDAY ||
                startDay == Calendar.SUNDAY;
    }
}
