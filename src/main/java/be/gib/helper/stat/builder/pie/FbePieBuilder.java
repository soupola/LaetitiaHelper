package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
import javafx.scene.Node;

import java.util.List;
import java.util.stream.Collectors;

public class FbePieBuilder extends AbstractPieBuilder {
    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> p.getShow().getCountry() == EnumOrigine.FBE)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return scheduler;
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        Scheduler filteredScheduler = getCustomScheduler(scheduler);
        return super.generatePieChartType(
                super.loadFullTypeMap(
                        filteredScheduler.getTimeSlots(),
                        filteredScheduler.getTotalTime()),
                scheduler.getChaine().getName());
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        return null;
    }
}
