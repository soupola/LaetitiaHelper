package be.gib.helper.calendar.controller;

import be.gib.helper.core.MainController;
import be.gib.helper.core.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.time.Duration;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class CalendarController extends MainController {

    @FXML
    private TabPane mainCalendar;

    @FXML
    void initialize() {
        assert mainCalendar != null : "fx:id=\"mainCalendar\" was not injected: check your FXML file 'calendarView.fxml'.";
        setCalendarController(this);
        if (!getSchedulers().isEmpty()) {
            for (Scheduler scheduler : getSchedulers()) {
                //création du calendrier
                CalendarView calendar = CalendarFactory.getNewCalendar();
                loadEmissions(calendar, scheduler);
                setupWeekView(calendar, scheduler);
                //création de la tabbed vue
                Tab tab = new Tab();
                tab.setClosable(false);
                tab.setContent(calendar);
                tab.setText(scheduler.getName());
                mainCalendar.getTabs().add(tab);
            }
        } else {
            System.err.println("No scheduler to display");
        }
    }

    private void loadEmissions(CalendarView calendar, Scheduler scheduler) {
        EntryFactory entryFactory = new EntryFactory();
        calendar.setEntryFactory(entryFactory);
        for (TimeSlot timeSlot : scheduler.getTimeSlots()) {
            Duration duration = timeSlot.getShow().getDuration();
            for (Date date : timeSlot.getStartDates()) {
                calendar.createEntryAt(
                        ZonedDateTime.ofInstant(date.toInstant(),
                                ZoneId.systemDefault()));
            }
        }
    }

    private void setupWeekView(CalendarView calendar, Scheduler scheduler) {
        TimeSlot timeSlot = scheduler.getTimeSlots()
                .stream()
                .findFirst()
                .orElse(null);

        if (timeSlot != null) {
            Date date = timeSlot.getStartDates()
                    .stream()
                    .findFirst()
                    .orElse(null);
            if (date != null) {
                Calendar calendarDate = Calendar.getInstance();
                calendar.showWeek(Year.of(calendarDate.get(Calendar.YEAR)), calendarDate.get(Calendar.WEEK_OF_YEAR));
            }
        }
    }
}
