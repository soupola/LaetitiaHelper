package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
import be.gib.helper.core.enums.EnumShowCategory;
import javafx.scene.Node;

import java.util.List;
import java.util.stream.Collectors;

public class EntertainmentPieBuilder extends AbstractPieBuilder {
    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> EnumShowCategory.ENTERTAINMENT.getTypes().contains(p.getShow().getType()))
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        Scheduler customScheduler = getCustomScheduler(scheduler);
        return super.generatePieChartType(
                super.loadSubTypeMap(EnumShowCategory.ENTERTAINMENT.getTypes(),
                        customScheduler.getTimeSlots(),
                        customScheduler.getTotalTime()),
                "Détail entertainment"
        );
    }

    public Node buildNlFiltered(List<Scheduler> scheduler) {
        Scheduler merge = super.merge(scheduler);
        return buildFiltered(merge, EnumOrigine.NBE);
    }

    public Node buildFrFiltered(List<Scheduler> scheduler) {
        Scheduler merge = super.merge(scheduler);
        return buildFiltered(merge, EnumOrigine.FBE);
    }

    private Node buildFiltered(Scheduler scheduler, EnumOrigine origine) {
        List<TimeSlot> collect = scheduler.getTimeSlots().stream()
                .filter(p -> p.getShow().getCountry() == origine)
                .collect(Collectors.toList());
        Scheduler filtered = new Scheduler(collect, null);
        return buildGraph(filtered);
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        Scheduler merge = super.merge(schedulers);
        return buildGraph(merge);
    }
}
