package ru.sanevich.mas.lab4.core;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sanevich.mas.lab4.model.map.*;

import static ru.sanevich.mas.lab4.core.CommonData.FINISH_ACHIEVED;

@Service
public class StepService {

    private final MyMap myMap;
    private final Logger log = LoggerFactory.getLogger(StepService.class);
    private int counter = 0;

    @Getter
    private String message;


    @Autowired
    public StepService(MyMap myMap) {
        this.myMap = myMap;
    }

    public void doStep() {
        Walkable agent = myMap.getAgent();

        Cell next = agent.makeStep(myMap);
        Cell curr = agent.getCurrentCell();

        if (next.getItem() instanceof FinishPoint) {
            FINISH_ACHIEVED = true;
            message = "Finish was found by " + agent.getClass().getSimpleName() + ", it take him " + counter + " steps";
            log.info("Finish was found by {}, it take him {} steps", agent.getClass().getSimpleName(), counter);
        }

        myMap.getCells()[next.getX()][next.getY()].setItem(curr.getItem());
        myMap.getCells()[next.getX()][next.getY()].setX(next.getX());
        myMap.getCells()[next.getX()][next.getY()].setY(next.getY());

        myMap.getCells()[curr.getX()][curr.getY()].setItem(new Empty());

        agent.resetPossibleSteps();
        counter++;
    }
}
