package be.gib.helper.calendar.controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import java.time.LocalTime;

public class CalendarFactory {

    public static CalendarView getNewCalendar() {
        CalendarView calendarView = new CalendarView();
        Calendar programs = new Calendar("programs");

        programs.setStyle(Calendar.Style.STYLE1);

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(programs);

        calendarView.getCalendarSources().addAll(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

//       force showing month
        calendarView.showWeekPage();

        return calendarView;
    }
}
