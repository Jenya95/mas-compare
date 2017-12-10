package ru.sanevich.mas.lab4.model.map;

import ru.sanevich.mas.lab4.model.agent.SmartAgent;
import ru.sanevich.mas.lab4.model.agent.StupidAgent;

public abstract class Cell {
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
