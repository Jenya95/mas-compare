package ru.sanevich.mas.lab4.model;

import ru.sanevich.mas.lab4.model.agent.SmartAgent;
import ru.sanevich.mas.lab4.model.agent.StupidAgent;
import ru.sanevich.mas.lab4.model.map.Empty;
import ru.sanevich.mas.lab4.model.map.FinishPoint;
import ru.sanevich.mas.lab4.model.map.Wall;

abstract public class Item {
    public boolean isEmpty() {
        return this instanceof Empty;
    }
    public boolean isWall() {
        return this instanceof Wall;
    }
    public boolean isSmartAgent() {
        return this instanceof SmartAgent;
    }
    public boolean isStupidAgent() {
        return this instanceof StupidAgent;
    }
    public boolean isFinish() {
        return this instanceof FinishPoint;
    }
}
