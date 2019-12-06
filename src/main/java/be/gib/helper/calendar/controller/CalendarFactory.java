package be.gib.helper.calendar.controller;

import be.gib.helper.core.Scheduler;
import be.gib.helper.core.bean.Show;
import be.gib.helper.core.bean.TimeSlot;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.time.ZoneId.systemDefault;


public class CalendarFactory {

    public static CalendarView getLoadedCalendar(List<Scheduler> schedulers) {
        CalendarView calendarView = new CalendarView();
        CalendarSource myCalendarSource = new CalendarSource("Programmes");

        for (Scheduler scheduler : schedulers) {
            Calendar calendar = new Calendar();
            calendar.setName(scheduler.getName());
            calendar.setStyle(Calendar.Style.STYLE1);
            loadEmissions(calendarView, calendar, scheduler);
            myCalendarSource.getCalendars().addAll(calendar);
        }
        calendarView.getCalendarSources().addAll(myCalendarSource);

//       force showing week
        calendarView.setDate(LocalDate.ofInstant(Objects.requireNonNull(getFirstShow(myCalendarSource)), systemDefault()));
        calendarView.showWeekPage();

        return calendarView;
    }

    private static void loadEmissions(CalendarView calendarView, Calendar calendar, Scheduler scheduler) {
        for (TimeSlot timeSlot : scheduler.getTimeSlots()) {
            Show show = timeSlot.getShow();
            Duration duration = show.getDuration();
            for (Date date : timeSlot.getStartDates()) {
                Instant endInstant = timeSlot.getEndDate(date, show.getDuration()).toInstant();
                Instant startInstant = date.toInstant();
                Entry<?> entryAt = calendarView.createEntryAt(
                        ZonedDateTime.ofInstant(startInstant, systemDefault()),
                        calendar);
                entryAt.setTitle(show.getTitle());
                entryAt.changeEndTime(LocalTime.ofInstant(endInstant, systemDefault()));
            }
        }
    }

    private static Instant getFirstShow(CalendarSource source) {
        Calendar calendar = source.getCalendars().stream()
                .findFirst()
                .orElse(null);
        if (calendar != null) {
            return calendar.getEarliestTimeUsed();
        }
        return null;
    }
}
