package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
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
        List<EnumOrigine> origines = Arrays.asList(EnumOrigine.NBE, EnumOrigine.NL);
        return super.generateOriginPieChart(
                super.loadMapOrigine(merge.getTimeSlots(),
                        merge.getTotalTime(),
                        origines),
                "Pourcentage d'émissions belges néerlandophone pour toute chaines néerlandophone confondue");
    }

    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> p.getShow().getCountry() == EnumOrigine.NBE)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }
}
