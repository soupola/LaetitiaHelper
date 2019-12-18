package be.gib.helper.calendar.builder;

import be.gib.helper.core.Scheduler;

import java.util.List;

public interface ExcelFactory {
    void generateCalendar(List<Scheduler> schedulers);
}
