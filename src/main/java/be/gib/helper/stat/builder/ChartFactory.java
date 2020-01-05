package be.gib.helper.stat.builder;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.stat.builder.pie.CategoryPieBuilder;
import be.gib.helper.stat.builder.pie.FbePieBuilder;
import be.gib.helper.stat.builder.pie.NbePieBuilder;
import javafx.scene.Node;

public class ChartFactory {
    public static Node generateFbeChart(Scheduler scheduler) {
        FbePieBuilder builder = new FbePieBuilder();
        return builder.buildGraph(scheduler);
    }

    public static Node generateNbeChart(Scheduler scheduler) {
        NbePieBuilder builder = new NbePieBuilder();
        return builder.buildGraph(scheduler);
    }
    public static Node generateCategoryChart(Scheduler scheduler) {
        CategoryPieBuilder builder = new CategoryPieBuilder();
        return builder.buildGraph(scheduler);
    }
}
