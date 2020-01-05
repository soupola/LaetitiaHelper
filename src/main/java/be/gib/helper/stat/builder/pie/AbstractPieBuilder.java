package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumShowType;
import be.gib.helper.stat.builder.StatBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class AbstractPieBuilder implements StatBuilder {
    protected Map<EnumShowType, Double> fullCategoryMap;
    private static final DecimalFormat DF = new DecimalFormat("#.###");

    protected Map<EnumShowType, Double> loadFullCategoryMap(List<TimeSlot> slots, Double totalTime) {
        if (slots == null) {
            return null;
        }
        fullCategoryMap = initializeFullMap();
        for (TimeSlot slot : slots) {
            Double previous = fullCategoryMap.get(slot.getShow().getType());
            fullCategoryMap.put(slot.getShow().getType(), previous + slot.getTotalTime());
        }
        removeZeros(fullCategoryMap);
        convertToPercent(totalTime, fullCategoryMap);
        return fullCategoryMap;
    }

    protected PieChart generatePieChart(Map<EnumShowType, Double> category,String graphName) {
        if (category == null) {
            return null;
        }
        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (Map.Entry<EnumShowType, Double> entry : category.entrySet()) {
            data.add(new PieChart.Data(entry.getKey().getName()+" : "+DF.format(entry.getValue())+" %",
                    entry.getValue()));
        }
        ObservableList<PieChart.Data> displayData = FXCollections.observableList(data);
        PieChart chart = new PieChart(displayData);
        chart.setTitle(graphName);
        chart.setLabelsVisible(true);
        chart.setLabelsVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        return chart;
    }

    private Map<EnumShowType, Double> initializeFullMap() {
        Map<EnumShowType, Double> map = new LinkedHashMap<>();
        for (EnumShowType type : EnumShowType.values()) {
            map.put(type, 0D);
        }
        return map;
    }

    private Map<EnumShowType, Double> convertToPercent(Double totalTime, Map<EnumShowType, Double> map) {
        for (Map.Entry<EnumShowType, Double> entry : map.entrySet()) {
            double categoryTime = entry.getValue();
            double percent = (categoryTime / totalTime) * 100;
            map.put(entry.getKey(), percent);
        }
        return map;
    }

    private Map<EnumShowType, Double> removeZeros(Map<EnumShowType, Double> map) {
        map.entrySet().removeIf(p -> p.getValue() == 0);
        return map;
    }
}
