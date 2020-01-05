package be.gib.helper.stat.builder;

import be.gib.helper.core.bean.Scheduler;
import javafx.scene.Node;

public interface StatBuilder {
    Node buildGraph(Scheduler scheduler);
}
