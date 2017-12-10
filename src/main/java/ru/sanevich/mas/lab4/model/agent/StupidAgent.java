package ru.sanevich.mas.lab4.model.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sanevich.mas.lab4.model.Item;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.MyMap;
import ru.sanevich.mas.lab4.model.map.Walkable;
import ru.sanevich.mas.lab4.model.map.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.sanevich.mas.lab4.core.CommonData.HEIGHT_MAP;
import static ru.sanevich.mas.lab4.core.CommonData.WIDTH_MAP;

@Getter
@Setter
@AllArgsConstructor
public class StupidAgent extends Item implements Walkable {

    private Cell currentCell;
    private List<Cell> possibleSteps;
    private final Logger log = LoggerFactory.getLogger(StupidAgent.class);

    @Override
    public Cell makeStep(MyMap myMap) {

        int currX = currentCell.getX();
        int currY = currentCell.getY();
        Random randomGenerator = new Random();

        if (canGoDown()) {
            addIfNotWall(myMap.getCells()[currX + 1][currY]);
        }

        if (canGoUp()) {
            addIfNotWall(myMap.getCells()[currX - 1][currY]);
        }

        if (canGoRight()) {
            addIfNotWall(myMap.getCells()[currX][currY + 1]);
        }

        if (canGoLeft()) {
            addIfNotWall(myMap.getCells()[currX][currY - 1]);
        }

        if (canGoUp() && canGoLeft()) {
            addIfNotWall(myMap.getCells()[currX - 1][currY - 1]);
        }

        if (canGoUp() && canGoRight()) {
            addIfNotWall(myMap.getCells()[currX - 1][currY + 1]);
        }

        if (canGoDown() && canGoRight()) {
            addIfNotWall(myMap.getCells()[currX + 1][currY + 1]);
        }

        if (canGoDown() && canGoLeft()) {
            addIfNotWall(myMap.getCells()[currX + 1][currY - 1]);
        }

        int index = randomGenerator.nextInt(possibleSteps.size());
        log.info("Possible steps: {}", possibleSteps);
        log.info("Stupid agent decide to go to cell [{},{}]",
                possibleSteps.get(index).getX(),
                possibleSteps.get(index).getY());

        return possibleSteps.get(index);
    }

    private boolean canGoLeft() {
        return currentCell.getY() >= 1;
    }

    private boolean canGoRight() {
        return currentCell.getY() < WIDTH_MAP - 1;
    }

    private boolean canGoUp() {
        return currentCell.getX() >= 1;
    }

    private boolean canGoDown() {
        return currentCell.getX() < HEIGHT_MAP - 1;
    }

    @Override
    public void resetPossibleSteps() {
        possibleSteps = new ArrayList<>();
    }

    private void addIfNotWall(Cell cell) {
        if (!(cell.getItem() instanceof Wall)) {
            possibleSteps.add(cell);
        }
    }

    @Override
    public String toString() {
        return "StupidAgent{" +
                "x=" + currentCell.getX() +
                "y=" + currentCell.getY() +
                '}';
    }
}
