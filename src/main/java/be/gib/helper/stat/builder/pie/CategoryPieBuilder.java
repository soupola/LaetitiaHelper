package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import javafx.scene.Node;

import java.util.List;

public class CategoryPieBuilder extends AbstractPieBuilder {
    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        return null;
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        return super.generatePieChartCategory(
                super.loadCategoryMap(
                        scheduler.getTimeSlots(),
                        scheduler.getTotalTime()),
                scheduler.getChaine().getName());
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        return null;
    }
}
