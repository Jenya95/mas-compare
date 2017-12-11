package ru.sanevich.mas.lab4.model.agent;


import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.MyMap;
import ru.sanevich.mas.lab4.model.map.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class StupidAgent extends Agent {

    private List<Cell> possibleSteps;
    private final Logger log = LoggerFactory.getLogger(StupidAgent.class);

    public StupidAgent(Cell currentCell, List<Cell> possibleSteps) {
        super(currentCell);
        this.possibleSteps = possibleSteps;
    }

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
                "x=" + currentCell.getX() + "," +
                "y=" + currentCell.getY() +
                '}';
    }
}
