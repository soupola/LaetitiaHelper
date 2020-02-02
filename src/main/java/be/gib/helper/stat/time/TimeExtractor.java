package be.gib.helper.stat.time;

import be.gib.helper.core.bean.Scheduler;
import be.gib.helper.core.bean.TimeSlot;
import be.gib.helper.core.enums.EnumOrigine;
import be.gib.helper.core.enums.EnumShowCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeExtractor {
    //    NBE TOTAL /s vulling
    public static String extractNbeTimeWV(List<Scheduler> schedulers) {
        Scheduler merge = merge(schedulers);
        List<TimeSlot> collect = merge.getTimeSlots().stream()
                .filter(p -> !EnumShowCategory.VULLING.getTypes().contains(p.getShow().getType()))
                .filter(p -> p.getShow().getCountry() == EnumOrigine.NBE)
                .collect(Collectors.toList());
        Scheduler scheduler = new Scheduler(collect, null);
        return String.valueOf(scheduler.getTotalTime());
    }

    public static String extractFbeTimeWV(List<Scheduler> schedulers) {
        Scheduler merge = merge(schedulers);
        List<TimeSlot> collect = merge.getTimeSlots().stream()
                .filter(p -> !EnumShowCategory.VULLING.getTypes().contains(p.getShow().getType()))
                .filter(p -> p.getShow().getCountry() == EnumOrigine.FBE)
                .collect(Collectors.toList());
        Scheduler scheduler = new Scheduler(collect, null);
        return String.valueOf(scheduler.getTotalTime());
    }

//    Total /s Vull

    public static String extractTimeWV(Scheduler scheduler) {
        List<TimeSlot> collect = scheduler.getTimeSlots().stream()
                .filter(p -> !EnumShowCategory.VULLING.getTypes().contains(p.getShow().getType()))
                .collect(Collectors.toList());
        Scheduler sc = new Scheduler(collect, null);
        return String.valueOf(sc.getTotalTime());
    }

    //    -> chaine
    private static Scheduler merge(List<Scheduler> schedulers) {
        ArrayList<TimeSlot> slots = new ArrayList<>();
        for (Scheduler scheduler : schedulers) {
            slots.addAll(scheduler.getTimeSlots());
        }
        return new Scheduler(slots, null);
    }
}
