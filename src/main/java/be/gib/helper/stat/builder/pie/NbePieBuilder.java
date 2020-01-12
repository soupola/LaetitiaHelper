package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
import be.gib.helper.core.enums.EnumShowCategory;
import javafx.scene.Node;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NbePieBuilder extends AbstractPieBuilder {
    @Override
    public Node buildGraph(Scheduler scheduler) {
        return null;
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        Scheduler merge = super.merge(schedulers);
        Scheduler customScheduler = getCustomScheduler(merge);
        List<EnumOrigine> origines = Arrays.asList(EnumOrigine.NBE, EnumOrigine.NL);
        return super.generateOriginPieChart(
                super.loadMapOrigine(customScheduler.getTimeSlots(),
                        customScheduler.getTotalTime(),
                        origines),
                "Pourcentage d'émissions belges néerlandophone pour toute chaines néerlandophone confondue");
    }

    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> EnumShowCategory.getCategory(p.getShow().getType()) != EnumShowCategory.VULLING)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }
}
