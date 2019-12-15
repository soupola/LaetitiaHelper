package be.gib.helper.calendarFx.controller;

import com.calendarfx.model.Entry;
import com.calendarfx.view.AllDayView;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.VirtualGrid;
import javafx.util.Callback;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZonedDateTime;

public class EntryFactory implements Callback<DateControl.CreateEntryParameter, Entry<?>> {
    @Override
    public Entry<Object> call(DateControl.CreateEntryParameter param) {
        DateControl control = param.getDateControl();
        VirtualGrid grid = control.getVirtualGrid();
        ZonedDateTime time = param.getZonedDateTime();
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        ZonedDateTime lowerTime = grid.adjustTime(time, false, firstDayOfWeek);
        ZonedDateTime upperTime = grid.adjustTime(time, true, firstDayOfWeek);

        if (Duration.between(time, lowerTime).abs().minus(Duration.between(time, upperTime).abs()).isNegative()) {
            time = lowerTime;
        } else {
            time = upperTime;
        }

        Entry<Object> entry = new Entry<>("New Show");
        entry.changeStartDate(time.toLocalDate());
        entry.changeStartTime(time.toLocalTime());
        entry.changeEndDate(entry.getStartDate());
        entry.changeEndTime(entry.getStartTime().plusHours(1));

        if (control instanceof AllDayView) {
            entry.setFullDay(true);
        }

        return entry;
    }
}
