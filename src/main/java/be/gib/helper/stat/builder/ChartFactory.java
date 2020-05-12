package be.gib.helper.stat.builder;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.stat.builder.pie.*;
import javafx.scene.Node;

import java.util.List;

public class ChartFactory {
    public static Node generateFbeChart(List<Scheduler> scheduler) {
        FbePieBuilder builder = new FbePieBuilder();
        return builder.buildGraph(scheduler);
    }

    public static Node generateNbeChart(List<Scheduler> scheduler) {
        NbePieBuilder builder = new NbePieBuilder();
        return builder.buildGraph(scheduler);
    }

    public static Node generateCategoryChart(Scheduler scheduler) {
        CategoryPieBuilder builder = new CategoryPieBuilder();
        return builder.buildGraph(scheduler);
    }

    public static Node generateCategoryChartWithoutVulling(Scheduler scheduler) {
        CategoryPieBuilder builder = new CategoryPieBuilder();
        return builder.buildGraphNoVulling(scheduler);
    }

    public static Node fbeOnFbeCategoryChart(Scheduler scheduler) {
        FrGlobalCategoryOrigin builder = new FrGlobalCategoryOrigin();
        return builder.buildGraph(scheduler);
    }

    public static Node fbeOnTotalCategoryChart(Scheduler scheduler) {
        FrGlobalCategoryOrigin builder = new FrGlobalCategoryOrigin();
        return builder.buildGlobalGraph(scheduler);
    }

    public static Node nbeOnNbeCategoryChart(Scheduler scheduler) {
        NlGlobalCategoryOrigin builder = new NlGlobalCategoryOrigin();
        return builder.buildGraph(scheduler);
    }

    public static Node nbeOnTotalCategoryChart(Scheduler scheduler) {
        NlGlobalCategoryOrigin builder = new NlGlobalCategoryOrigin();
        return builder.buildGlobalGraph(scheduler);
    }

    public static Node nbeAllChannelCategory(List<Scheduler> schedulers) {
        NlGlobalCategoryOrigin builder = new NlGlobalCategoryOrigin();
        return builder.buildGraph(schedulers);
    }

    public static Node fbeAllChannelCategory(List<Scheduler> schedulers) {
        FrGlobalCategoryOrigin builder = new FrGlobalCategoryOrigin();
        return builder.buildGraph(schedulers);
    }

    public static Node entertainmentExploded(Scheduler scheduler) {
        EntertainmentPieBuilder builder = new EntertainmentPieBuilder();
        return builder.buildGraph(scheduler);
    }

    public static Node entertainmentExploded(List<Scheduler> schedulers) {
        EntertainmentPieBuilder builder = new EntertainmentPieBuilder();
        return builder.buildGraph(schedulers);
    }

    public static Node entertainmentNlExploded(List<Scheduler> schedulers) {
        EntertainmentPieBuilder builder = new EntertainmentPieBuilder();
        return builder.buildNlFiltered(schedulers);
    }

    public static Node entertainmentFrExploded(List<Scheduler> schedulers) {
        EntertainmentPieBuilder builder = new EntertainmentPieBuilder();
        return builder.buildFrFiltered(schedulers);
    }
}
