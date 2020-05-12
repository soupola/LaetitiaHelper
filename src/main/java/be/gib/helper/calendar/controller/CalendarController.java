package be.gib.helper.calendar.controller;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.controller.MainController;
import be.gib.helper.core.enums.EnumChaine;
import be.gib.helper.core.enums.EnumOrigine;
import be.gib.helper.stat.builder.ChartFactory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.*;
import java.util.stream.Collectors;

public class CalendarController extends MainController {

    @FXML
    private TabPane eenTabPane;

    @FXML
    private TabPane vtmTabPane;

    @FXML
    private TabPane vierTbPane;

    @FXML
    private TabPane uneTabPane;

    @FXML
    private TabPane rtlTabPane;

    @FXML
    private TabPane ab3TabPane;
    @FXML
    private TabPane commonTabPane;

    @FXML
    void initialize() {
        assert eenTabPane != null : "fx:id=\"eenTabPane\" was not injected: check your FXML file 'calendarView.fxml'.";
        assert vtmTabPane != null : "fx:id=\"vtmTabPane\" was not injected: check your FXML file 'calendarView.fxml'.";
        assert vierTbPane != null : "fx:id=\"vierTbPane\" was not injected: check your FXML file 'calendarView.fxml'.";
        assert uneTabPane != null : "fx:id=\"uneTabPane\" was not injected: check your FXML file 'calendarView.fxml'.";
        assert rtlTabPane != null : "fx:id=\"rtlTabPane\" was not injected: check your FXML file 'calendarView.fxml'.";
        assert ab3TabPane != null : "fx:id=\"ab3TabPane\" was not injected: check your FXML file 'calendarView.fxml'.";
        assert commonTabPane != null : "fx:id=\"ab3TabPane\" was not injected: check your FXML file 'calendarView.fxml'.";
        setCalendarController(this);
        Map<EnumChaine, TabPane> map = new HashMap<>();
        map.put(EnumChaine.VTM, vtmTabPane);
        map.put(EnumChaine.EEN, eenTabPane);
        map.put(EnumChaine.VIER, vierTbPane);
        map.put(EnumChaine.LA_UNE, uneTabPane);
        map.put(EnumChaine.RTL, rtlTabPane);
        map.put(EnumChaine.AB3, ab3TabPane);


        if (!getSchedulers().isEmpty()) {
            for (Map.Entry<EnumChaine, TabPane> entry : map.entrySet()) {
                entry.getValue().getTabs().addAll(LoadChannel(entry.getKey(), getSchedulers()));
            }
            commonTabPane.getTabs().addAll(generateGlobalFr(filterByOrigin(EnumOrigine.FBE, getSchedulers())));
            commonTabPane.getTabs().addAll(generateGlobalNl(filterByOrigin(EnumOrigine.NBE, getSchedulers())));
        }

    }

    public ArrayList<Tab> LoadChannel(EnumChaine chaine, List<Scheduler> schedulers) {
        ArrayList<Tab> tabs = new ArrayList<>();
        Scheduler scheduler = filterByName(chaine, schedulers);
        tabs.addAll(generateCommon(scheduler));
        if (chaine.getOrigine() == EnumOrigine.FBE) {
            tabs.addAll(generateFr(scheduler));
        } else {
            tabs.addAll(generateNl(scheduler));
        }
        return tabs;
    }

    private Scheduler filterByName(EnumChaine chaine, List<Scheduler> schedulers) {
        return schedulers.stream()
                .filter(p -> p.getChaine() == chaine)
                .findFirst()
                .orElse(null);
    }

    private List<Scheduler> filterByOrigin(EnumOrigine origine, List<Scheduler> schedulers) {
        return schedulers.stream()
                .filter(p -> p.getChaine().getOrigine() == origine)
                .collect(Collectors.toList());
    }

    private ArrayList<Tab> generateCommon(Scheduler scheduler) {
        ArrayList<Tab> tabs = new ArrayList<>();
        tabs.add(
                generateTab("total en catégorie",
                        ChartFactory.generateCategoryChart(scheduler)));
        tabs.add(generateTab("total en catégorie s/ vulling",
                ChartFactory.generateCategoryChartWithoutVulling(scheduler)));
        return tabs;
    }

    private ArrayList<Tab> generateNl(Scheduler current) {
        ArrayList<Tab> list = new ArrayList<>();
        list.add(generateTab("% flamand de chaque catégorie",
                ChartFactory.nbeOnNbeCategoryChart(current)));
        list.add(generateTab("Catégorie des emissions flamandes",
                ChartFactory.nbeOnTotalCategoryChart(current)));
        list.add(generateTab("Nationnalité par chaine",
                ChartFactory.generateNbeChart(Arrays.asList(current))));
        list.add(generateTab("Catégorie entertainment exploded",
                ChartFactory.entertainmentNlExploded(Collections.singletonList(current))));
        return list;
    }

    private ArrayList<Tab> generateFr(Scheduler current) {
        ArrayList<Tab> list = new ArrayList<>();
        list.add(generateTab("% Francophone de chaque catégorie",
                ChartFactory.fbeOnFbeCategoryChart(current)));
        list.add(generateTab("Catégorie des emissions Francophone",
                ChartFactory.fbeOnTotalCategoryChart(current)));
        list.add(generateTab("Nationnalité par chaine",
                ChartFactory.generateFbeChart(Arrays.asList(current))));
        list.add(generateTab("Catégorie entertainment exploded",
                ChartFactory.entertainmentFrExploded(Collections.singletonList(current))));
        return list;
    }

    private ArrayList<Tab> generateGlobalFr(List<Scheduler> schedulers) {
        ArrayList<Tab> tabs = new ArrayList<>();
        tabs.add(generateTab("Catégorie belge Trois chaines francophone",
                ChartFactory.generateFbeChart(schedulers)));
        tabs.add(generateTab("Catégorie des émissions francophone",
                ChartFactory.fbeAllChannelCategory(schedulers)));
        tabs.add(generateTab("Entertaiment des émissions francophone",
                ChartFactory.entertainmentFrExploded(schedulers)));
        return tabs;
    }

    private ArrayList<Tab> generateGlobalNl(List<Scheduler> schedulers) {
        ArrayList<Tab> tabs = new ArrayList<>();
        tabs.add(generateTab("Catégorie belge Trois chaines néerlandophone",
                ChartFactory.generateNbeChart(schedulers)));
        tabs.add(generateTab("Catégorie des émissions néerlandophone",
                ChartFactory.nbeAllChannelCategory(schedulers)));
        tabs.add(generateTab("Entertaiment des émissions néerlandophone",
                ChartFactory.entertainmentNlExploded(schedulers)));
        return tabs;
    }

    private Tab generateTab(String name, Node content) {
        Tab tab = new Tab();
        tab.setText(name);
        tab.setClosable(false);
        tab.setContent(content);
        return tab;
    }
}
