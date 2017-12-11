package ru.sanevich.mas.lab4.model.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.sanevich.mas.lab4.model.Item;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.Walkable;

import static ru.sanevich.mas.lab4.core.CommonData.HEIGHT_MAP;
import static ru.sanevich.mas.lab4.core.CommonData.WIDTH_MAP;

@AllArgsConstructor
@Getter
@Setter
public abstract class Agent extends Item implements Walkable {
    protected Cell currentCell;

    boolean canGoLeft() {
        return currentCell.getY() >= 1;
    }

    boolean canGoRight() {
        return currentCell.getY() < WIDTH_MAP - 1;
    }

    boolean canGoUp() {
        return currentCell.getX() >= 1;
    }

    boolean canGoDown() {
        return currentCell.getX() < HEIGHT_MAP - 1;
    }
}
