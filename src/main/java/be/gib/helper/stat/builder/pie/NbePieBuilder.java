package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import javafx.scene.Node;

public class NbePieBuilder extends AbstractPieBuilder {
    @Override
    public Node buildGraph(Scheduler scheduler) {

        return super.generatePieChartCategory(
                super.loadCategoryMap(
                        scheduler.getTimeSlots(),
                        scheduler.getTotalTime()),
                scheduler.getName());
    }

    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        return null;
    }
}
