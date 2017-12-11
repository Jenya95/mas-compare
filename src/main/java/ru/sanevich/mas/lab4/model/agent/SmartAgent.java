package ru.sanevich.mas.lab4.model.agent;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.FinishPoint;
import ru.sanevich.mas.lab4.model.map.MyMap;
import ru.sanevich.mas.lab4.model.map.Wall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
public class SmartAgent extends Agent {
    public SmartAgent(Cell currentCell, Map<Cell, Integer> possibleSteps) {
        super(currentCell);
        this.possibleSteps = possibleSteps;
    }

    private int prevX;
    private int prevY;
    private Map<Cell, Integer> possibleSteps;
    private final Logger log = LoggerFactory.getLogger(StupidAgent.class);


    @Override
    public Cell makeStep(MyMap myMap) {

        int currX = currentCell.getX();
        int currY = currentCell.getY();
        Random r = new Random();

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

        Integer maxScore = possibleSteps.values()
                .stream()
                .max(Integer::compare)
                .orElse(0);

        List<Cell> nextCellList = possibleSteps.entrySet()
                .stream()
                .filter(x -> x.getValue().equals(maxScore))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Cell nextCell = nextCellList.get(r.nextInt(nextCellList.size()));

        log.info("Possible steps: {}", possibleSteps);
        log.info("Smart agent decide to go to cell [{},{}]",
                nextCell.getX(),
                nextCell.getY());

        prevX = currentCell.getX();
        prevY = currentCell.getY();

        return nextCell;

    }

    private void addIfNotWall(Cell nextCell) {
        if (!(nextCell.getItem() instanceof Wall)) {
            if ((nextCell.getX() == prevX) && (nextCell.getY() == prevY)) {
                possibleSteps.put(nextCell, 0);
            } else if (nextCell.getItem() instanceof FinishPoint) {
                possibleSteps.put(nextCell, 10);
            } else {
                possibleSteps.put(nextCell, 5);
            }

        }
    }

    @Override
    public void resetPossibleSteps() {
        possibleSteps = new HashMap<>();
    }

    @Override
    public String toString() {
        return "SmartAgent{" +
                "x=" + currentCell.getX() + "," +
                "y=" + currentCell.getY() +
                '}';
    }
}
