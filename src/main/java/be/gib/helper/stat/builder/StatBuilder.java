package be.gib.helper.stat.builder;

import be.gib.helper.core.bean.Scheduler;
import javafx.scene.Node;

import java.util.List;

public interface StatBuilder {
    Node buildGraph(Scheduler scheduler);

    Node buildGraph(List<Scheduler> schedulers);
}
