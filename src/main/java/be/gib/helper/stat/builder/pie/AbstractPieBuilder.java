package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumShowCategory;
import be.gib.helper.core.enums.EnumShowType;
import be.gib.helper.stat.builder.StatBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import java.text.DecimalFormat;
import java.util.*;

public abstract class AbstractPieBuilder implements StatBuilder {
    protected Map<EnumShowCategory, Double> fullCategoryMap;

    abstract Scheduler getCustomScheduler(Scheduler scheduler);

    private static final DecimalFormat DF = new DecimalFormat("#.###");

    protected Map<EnumShowType, Double> loadFullTypeMap(List<TimeSlot> slots, double totalTime) {
        if (slots == null) {
            return null;
        }
        Map<EnumShowType, Double> fullTypeMap = initializeFullTypeMap();
        for (TimeSlot slot : slots) {
            Double previous = fullTypeMap.get(slot.getShow().getType());
            fullTypeMap.put(slot.getShow().getType(), previous + slot.getTotalTime());
        }
        removeZeros(fullTypeMap);
        convertToPercentType(totalTime, fullTypeMap);
        return fullTypeMap;
    }

    protected Map<EnumShowCategory, Double> loadCategoryMap(List<TimeSlot> slots, double totalTime) {
        if (slots == null) {
            return null;
        }
        Map<EnumShowCategory, Double> map = initializeFullCategoryMap();
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

    private PieChart generatePieChart(List<PieChart.Data> data, String name) {
        ObservableList<PieChart.Data> displayData = FXCollections.observableList(data);
        PieChart chart = new PieChart(displayData);
        chart.setTitle(name);
        chart.setLabelsVisible(true);
        chart.setLabelsVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        return chart;
    }

    private Map<EnumShowType, Double> initializeFullTypeMap() {
        Map<EnumShowType, Double> map = new LinkedHashMap<>();
        for (EnumShowType type : EnumShowType.values()) {
            map.put(type, 0D);
        }
        return map;
    }

    private Map<EnumShowCategory, Double> initializeFullCategoryMap() {
        Map<EnumShowCategory, Double> map = new LinkedHashMap<>();
        for (EnumShowCategory type : EnumShowCategory.values()) {
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

    private double computePercent(double categoryTime, double totalTime) {
        return (categoryTime / totalTime) * 100;
    }

    private Map<?, Double> removeZeros(Map<?, Double> map) {
        map.entrySet().removeIf(p -> p.getValue() == 0);
        return map;
    }
}
