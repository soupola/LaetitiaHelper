package be.gib.helper.calendar.builder;

import be.gib.helper.core.bean.Scheduler;
import javafx.scene.Node;

import java.util.List;

public interface CalendarFactory {
    Node getLoadedCalendar(List<Scheduler> schedulers);
}
