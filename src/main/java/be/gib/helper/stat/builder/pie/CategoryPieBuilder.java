package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import javafx.scene.Node;

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
                scheduler.getName());
    }
}
