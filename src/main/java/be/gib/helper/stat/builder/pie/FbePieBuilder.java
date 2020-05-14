package be.gib.helper.stat.builder.pie;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
import be.gib.helper.core.enums.EnumShowCategory;
import javafx.scene.Node;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FbePieBuilder extends AbstractPieBuilder {
    @Override
    Scheduler getCustomScheduler(Scheduler scheduler) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> EnumShowCategory.getCategory(p.getShow().getType()) != EnumShowCategory.VULLING)
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        return filteredScheduler;
    }

    public Node buildForCategorie(Scheduler scheduler, List<EnumShowCategory> categories) {
        List<TimeSlot> slots = scheduler.getTimeSlots().stream()
                .filter(p -> categories.contains(EnumShowCategory.getCategory(p.getShow().getType())))
                .collect(Collectors.toList());
        Scheduler filteredScheduler = new Scheduler(slots, scheduler.getChaine());
        List<EnumOrigine> origines = Arrays.asList(EnumOrigine.FBE, EnumOrigine.FR);
        return super.generateOriginPieChart(
                super.loadMapOrigine(filteredScheduler.getTimeSlots(),
                        filteredScheduler.getTotalTime(),
                        origines),
                "nationalite entertainment");
    }

    @Override
    public Node buildGraph(Scheduler scheduler) {
        return null;
    }

    @Override
    public Node buildGraph(List<Scheduler> schedulers) {
        Scheduler merge = super.merge(schedulers);
        Scheduler scheduler = getCustomScheduler(merge);
        List<EnumOrigine> origines = Arrays.asList(EnumOrigine.FBE, EnumOrigine.FR);
        return super.generateOriginPieChart(
                super.loadMapOrigine(scheduler.getTimeSlots(),
                        scheduler.getTotalTime(),
                        origines),
                "Pourcentage d'émissions belges francophone pour toute chaines francophone confondue");
    }
}
