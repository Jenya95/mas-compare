package ru.sanevich.mas.lab4.model.agent;

import lombok.Getter;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.Walkable;

@Getter
public class SmartAgent extends Cell implements Walkable {

    private int X;
    private int Y;

    @Override
    public Cell makeStep() {
        return null;
    }

    @Override
    public String toString() {
        return "SmartAgent{}";
    }
}
