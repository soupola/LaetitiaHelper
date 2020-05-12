package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
import be.gib.helper.core.enums.EnumShowCategory;
import javafx.scene.Node;

import java.util.List;
import java.util.stream.Collectors;

public class NlGlobalCategoryOrigin extends AbstractPieBuilder {

    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> p.getShow().getCountry() == EnumOrigine.NBE)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }

    private Scheduler getSchedulerWithoutVulling(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> EnumShowCategory.getCategory(p.getShow().getType()) != EnumShowCategory.VULLING)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        Scheduler filtered = getCustomScheduler(getSchedulerWithoutVulling(scheduler));
        return super.generatePieChartCategory(
                super.loadFullCategoryMap(filtered.getTimeSlots(), filtered.getTotalTime()),
                "NBE/nbe"
        );
    }

    public Node buildGlobalGraph(Scheduler scheduler) {
        Scheduler total = getSchedulerWithoutVulling(scheduler);
        Scheduler filtered = getCustomScheduler(total);
        return super.generatePieChartCategory(
                super.loadFullCategoryMap(filtered.getTimeSlots(), total.getTotalTime()),
                "NBE/nbe total"
        );
    }

    public Node buildGlobalGraph(List<Scheduler> schedulers) {
        Scheduler merge = super.merge(schedulers);
        Scheduler total = getSchedulerWithoutVulling(merge);
        Scheduler filtered = getCustomScheduler(total);
        return super.generatePieChartCategory(
                super.loadFullCategoryMap(filtered.getTimeSlots(), total.getTotalTime()),
                "NBE/nbe total"
        );
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        Scheduler merge = super.merge(schedulers);
        return buildGraph(merge);
    }
}
