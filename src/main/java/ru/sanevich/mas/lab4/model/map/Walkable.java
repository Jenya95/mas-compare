package ru.sanevich.mas.lab4.model.map;

public interface Walkable {
    Cell makeStep(MyMap myMap);
    Cell getCurrentCell();
    void setCurrentCell(Cell currentCell);
    void resetPossibleSteps();
}
