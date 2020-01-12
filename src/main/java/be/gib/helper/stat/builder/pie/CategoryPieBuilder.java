package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumShowCategory;
import javafx.scene.Node;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryPieBuilder extends AbstractPieBuilder {
    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> EnumShowCategory.getCategory(p.getShow().getType()) != EnumShowCategory.VULLING)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        return super.generatePieChartCategory(
                super.loadCategoryMap(
                        scheduler.getTimeSlots(),
                        scheduler.getTotalTime()),
                scheduler.getChaine().getName());
    }

    public Node buildGraphNoVulling(Scheduler scheduler) {
        Scheduler customScheduler = getCustomScheduler(scheduler);
        return super.generatePieChartCategory(
                super.loadCategoryMap(
                        customScheduler.getTimeSlots(),
                        customScheduler.getTotalTime()),
                scheduler.getChaine().getName());
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        return null;
    }
}
