package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
import be.gib.helper.core.enums.EnumShowCategory;
import be.gib.helper.core.enums.EnumShowType;
import be.gib.helper.stat.builder.StatBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import java.text.DecimalFormat;
import java.util.*;

import static be.gib.helper.core.enums.EnumOrigine.RESTE;

public abstract class AbstractPieBuilder implements StatBuilder {
    protected Map<EnumShowCategory, Double> fullCategoryMap;

    abstract Scheduler getCustomScheduler(Scheduler scheduler);

    private static final DecimalFormat DF = new DecimalFormat("#.###");

    protected Map<EnumOrigine, Double> loadMapOrigine(List<TimeSlot> slots, Double totalTime, List<EnumOrigine> origines) {
        if (slots == null) {
            return null;
        }
        Map<EnumOrigine, Double> map = new HashMap<>();
        map.put(RESTE, 0D);
        for (EnumOrigine origine : origines) {
            map.put(origine, 0D);
        }
        for (TimeSlot slot : slots) {
            EnumOrigine origine = slot.getShow().getCountry();
            if (origines.contains(origine)) {
                double old = map.get(origine);
                map.put(origine, old + slot.getTotalTime());
            } else {
                double old = map.get(RESTE);
                map.put(RESTE, old + slot.getTotalTime());
            }
        }
        return convertToPercentOrigine(totalTime, map);
    }

    protected Map<EnumShowType, Double> loadSubTypeMap(List<EnumShowType> types, List<TimeSlot> slots, double totalTime) {
        if (slots == null) {
            return null;
        }
        Map<EnumShowType, Double> fullTypeMap = initializeFullTypeMap(types);
        for (TimeSlot slot : slots) {
            Double previous = fullTypeMap.get(slot.getShow().getType());
            fullTypeMap.put(slot.getShow().getType(), previous + slot.getTotalTime());
        }
        removeZeros(fullTypeMap);
        convertToPercentType(totalTime, fullTypeMap);
        return fullTypeMap;
    }

    protected Map<EnumShowCategory, Double> loadCustomCategoryMap(List<EnumShowCategory> categories, List<TimeSlot> slots, double totalTime) {
        if (slots == null) {
            return null;
        }
        Map<EnumShowCategory, Double> map = initializeCategoryMap(categories);
        return loadCategoryMap(map, slots, totalTime);
    }

    protected Map<EnumShowCategory, Double> loadFullCategoryMap(List<TimeSlot> slots, double totalTime) {
        if (slots == null) {
            return null;
        }
        Map<EnumShowCategory, Double> map = initializeCategoryMap(Arrays.asList(EnumShowCategory.values()));
        return loadCategoryMap(map, slots, totalTime);
    }

    protected Map<EnumShowCategory, Double> loadCategoryMap(Map<EnumShowCategory, Double> map, List<TimeSlot> slots, double totalTime) {
        for (TimeSlot slot : slots) {
            EnumShowCategory category = EnumShowCategory.getCategory(slot.getShow().getType());
            Double previous = map.get(category);
            map.put(category, previous + slot.getTotalTime());
        }
        removeZeros(map);
        convertToPercentCategory(totalTime, map);
        return map;
    }

    protected PieChart generatePieChartType(Map<EnumShowType, Double> category, String graphName) {
        if (category == null) {
            return null;
        }
        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (Map.Entry<EnumShowType, Double> entry : category.entrySet()) {
            data.add(new PieChart.Data(entry.getKey().getName() + " : " + DF.format(entry.getValue()) + " %",
                    entry.getValue()));
        }
        return generatePieChart(data, graphName);
    }

    protected PieChart generatePieChartCategory(Map<EnumShowCategory, Double> category, String graphName) {
        if (category == null) {
            return null;
        }
        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (Map.Entry<EnumShowCategory, Double> entry : category.entrySet()) {
            data.add(new PieChart.Data(entry.getKey().getName() + " : " + DF.format(entry.getValue()) + " %",
                    entry.getValue()));
        }
        return generatePieChart(data, graphName);
    }

    protected PieChart generateOriginPieChart(Map<EnumOrigine, Double> map, String graphName) {
        if (map == null) {
            return null;
        }
        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (Map.Entry<EnumOrigine, Double> entry : map.entrySet()) {
            data.add(new PieChart.Data(entry.getKey().getName() + " : " + DF.format(entry.getValue()) + " %",
                    entry.getValue()));
        }
        return generatePieChart(data, graphName);
    }

    private PieChart generatePieChart(List<PieChart.Data> data, String name) {
        ObservableList<PieChart.Data> displayData = FXCollections.observableList(data);
        PieChart chart = new PieChart(displayData);
        chart.setTitle(name);
        chart.setLabelsVisible(true);
        chart.setLabelsVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        return chart;
    }

    private Map<EnumShowType, Double> initializeFullTypeMap(List<EnumShowType> types) {
        Map<EnumShowType, Double> map = new LinkedHashMap<>();
        for (EnumShowType type : types) {
            map.put(type, 0D);
        }
        return map;
    }

    private Map<EnumShowCategory, Double> initializeCategoryMap(List<EnumShowCategory> categories) {
        Map<EnumShowCategory, Double> map = new LinkedHashMap<>();
        for (EnumShowCategory type : categories) {
            map.put(type, 0D);
        }
        return map;
    }

    private Map<EnumShowType, Double> convertToPercentType(Double totalTime, Map<EnumShowType, Double> map) {
        for (Map.Entry<EnumShowType, Double> entry : map.entrySet()) {
            map.put(entry.getKey(), computePercent(entry.getValue(), totalTime));
        }
        return map;
    }

    private Map<EnumShowCategory, Double> convertToPercentCategory(Double totalTime, Map<EnumShowCategory, Double> map) {
        for (Map.Entry<EnumShowCategory, Double> entry : map.entrySet()) {
            map.put(entry.getKey(), computePercent(entry.getValue(), totalTime));
        }
        return map;
    }

    private Map<EnumOrigine, Double> convertToPercentOrigine(Double totalTime, Map<EnumOrigine, Double> map) {
        for (Map.Entry<EnumOrigine, Double> entry : map.entrySet()) {
            map.put(entry.getKey(), computePercent(entry.getValue(), totalTime));
        }
        return map;
    }

    private double computePercent(double categoryTime, double totalTime) {
        return (categoryTime / totalTime) * 100;
    }

    private Map<?, Double> removeZeros(Map<?, Double> map) {
        map.entrySet().removeIf(p -> p.getValue() == 0);
        return map;
    }

    protected Scheduler merge(List<Scheduler> schedulers) {
        if (schedulers == null) {
            return null;
        }
        ArrayList<TimeSlot> slots = new ArrayList<>();
        for (Scheduler scheduler : schedulers) {
            slots.addAll(scheduler.getTimeSlots());
        }
        Scheduler newScheduler = new Scheduler(slots, null);
        return newScheduler;
    }

}
