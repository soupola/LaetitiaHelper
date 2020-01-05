package be.gib.helper.calendar.controller;

import be.gib.helper.calendar.builder.CalendarFactory;
import be.gib.helper.calendar.builder.calendarfx.CalendarFactoryImpl;
import be.gib.helper.core.controller.MainController;
import be.gib.helper.stat.builder.pie.NbePieBuilder;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CalendarController extends MainController {

    @FXML
    private TabPane mainCalendar;

    @FXML
    void initialize() {
        assert mainCalendar != null : "fx:id=\"mainCalendar\" was not injected: check your FXML file 'calendarView.fxml'.";
        setCalendarController(this);
        if (!getSchedulers().isEmpty()) {
           // CalendarFactory factory = new CalendarFactoryImpl();
          //  Node calendar = factory.getLoadedCalendar(getSchedulers());
            Tab tab = new Tab();
            tab.setClosable(false);
            NbePieBuilder nbePieBuilder = new NbePieBuilder();
            tab.setContent(nbePieBuilder.buildGraph(getSchedulers().get(0)));
            tab.setText("Calendar");
            mainCalendar.getTabs().add(tab);
        } else {
            System.err.println("No scheduler to display");
        }
    }
}
