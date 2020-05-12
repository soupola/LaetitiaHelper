package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumShowCategory;
import javafx.scene.Node;

import java.util.List;
import java.util.stream.Collectors;

public class MagazinePieBuilder extends AbstractPieBuilder {
    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> EnumShowCategory.MAGDOC.getTypes().contains(p.getShow().getType()))
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        Scheduler customScheduler = getCustomScheduler(scheduler);
        return super.generatePieChartType(
                super.loadSubTypeMap(EnumShowCategory.MAGDOC.getTypes(),
                        customScheduler.getTimeSlots(),
                        customScheduler.getTotalTime()),
                "Détail magazine"
        );
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        Scheduler merge = super.merge(schedulers);
        return buildGraph(merge);
    }
}
