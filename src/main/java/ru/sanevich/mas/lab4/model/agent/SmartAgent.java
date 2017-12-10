package ru.sanevich.mas.lab4.model.agent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.sanevich.mas.lab4.model.Item;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.MyMap;
import ru.sanevich.mas.lab4.model.map.Walkable;

@Getter
@Setter
public class SmartAgent extends Item implements Walkable {

    private Cell currentCell;

    @Override
    public Cell makeStep(MyMap myMap) {
        return null;
    }

    @Override
    public void resetPossibleSteps() {
    }

    @Override
    public String toString() {
        return "SmartAgent{" +
                "x=" + currentCell.getX() +
                "y=" + currentCell.getY() +
                '}';
    }
}
