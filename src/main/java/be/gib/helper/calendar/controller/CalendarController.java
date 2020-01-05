package be.gib.helper.calendar.controller;

import be.gib.helper.calendar.builder.CalendarFactory;
import be.gib.helper.calendar.builder.calendarfx.CalendarFactoryImpl;
import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.controller.MainController;
import be.gib.helper.stat.builder.ChartFactory;
import be.gib.helper.stat.builder.pie.NbePieBuilder;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import sun.tools.jconsole.inspector.XNodeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

            //Load graphs
            Map<String, Node> graphs = new HashMap<>();
            for (Scheduler scheduler : getSchedulers()) {
                graphs.put("Category for " + scheduler.getName(), ChartFactory.generateCategoryChart(scheduler));
            }

            for (Map.Entry<String, Node> entry : graphs.entrySet()) {
                Tab tab = new Tab();
                tab.setText(entry.getKey());
                tab.setClosable(false);
                tab.setContent(entry.getValue());
                mainCalendar.getTabs().add(tab);
            }
        } else {
            System.err.println("No scheduler to display");
        }
    }
}
