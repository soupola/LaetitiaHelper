package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
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

    private Scheduler getSchedulerForOneOrigine(Scheduler scheduler, EnumOrigine origine) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> p.getShow().getCountry() == EnumOrigine.FR)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        return super.generatePieChartCategory(
                super.loadFullCategoryMap(
                        scheduler.getTimeSlots(),
                        scheduler.getTotalTime()),
                scheduler.getChaine().getName());
    }

    public Node buildGraphNoVulling(Scheduler scheduler) {
        Scheduler customScheduler = getCustomScheduler(scheduler);
        return super.generatePieChartCategory(
                super.loadFullCategoryMap(
                        customScheduler.getTimeSlots(),
                        customScheduler.getTotalTime()),
                scheduler.getChaine().getName());
    }

    public Node buildGraphNoVulling(List<Scheduler> schedulers) {
        Scheduler merge = super.merge(schedulers);
        Scheduler customScheduler = getCustomScheduler(merge);
        return super.generatePieChartCategory(
                super.loadFullCategoryMap(
                        customScheduler.getTimeSlots(),
                        customScheduler.getTotalTime()),
                "catégorie total");
    }

    public Node buildGraphNoVullingOrigine(List<Scheduler> schedulers, EnumOrigine origine) {
        Scheduler merge = super.merge(schedulers);
        Scheduler customScheduler = getCustomScheduler(merge);
        Scheduler schedulerForOneOrigine = getSchedulerForOneOrigine(customScheduler, origine);
        return super.generatePieChartCategory(
                super.loadFullCategoryMap(
                        schedulerForOneOrigine.getTimeSlots(),
                        schedulerForOneOrigine.getTotalTime()),
                "catégorie total");
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        return null;
    }
}
