package ru.sanevich.mas.lab4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sanevich.mas.lab4.model.Item;
import ru.sanevich.mas.lab4.model.agent.SmartAgent;
import ru.sanevich.mas.lab4.model.agent.StupidAgent;
import ru.sanevich.mas.lab4.model.map.*;
import ru.sanevich.mas.lab4.pathfinding.AstarMap;
import ru.sanevich.mas.lab4.pathfinding.ExampleFactory;
import ru.sanevich.mas.lab4.pathfinding.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.sanevich.mas.lab4.core.CommonData.*;

@Configuration
public class MasConfiguration {

    private final Logger log = LoggerFactory.getLogger(MasConfiguration.class);

    @Bean
    public MyMap initMap() {
        Cell[][] cells = new Cell[HEIGHT_MAP][WIDTH_MAP];
        Item item;


        for (int i = 0; i < HEIGHT_MAP; i++) {
            for (int j = 0; j < WIDTH_MAP; j++) {
                if (Math.random() < 0.5) {
                    item = new Wall();
                } else {
                    item = new Empty();
                }
                cells[i][j] = new Cell(i, j, item);
                if (!isFinishReachable(cells)) {
                    cells[i][j].setItem(new Empty());
                }
            }
        }

        SmartAgent smartAgent = new SmartAgent(cells[0][0], new HashMap<>());
        cells[0][0].setItem(smartAgent);

//        StupidAgent stupidAgent = new StupidAgent(cells[0][0], new ArrayList<>());
//        cells[0][0].setItem(stupidAgent);

        cells[X_FINISH][Y_FINISH].setItem(new FinishPoint());

        return MyMap.builder()
                .cells(cells)
                .build();
    }

    private boolean isFinishReachable(Cell[][] cells) {
        AstarMap<Point> myAstarMap = new AstarMap<>(cells[0].length, cells.length, new ExampleFactory());
        try {
            for (int i = 0; i < HEIGHT_MAP; i++) {
                for (int j = 0; j < WIDTH_MAP; j++) {

                    if (cells[i][j] == null) {
                        cells[i][j] = new Cell(i, j, new Empty());
                    }

                    if (cells[i][j].getItem() instanceof Wall) {
                        myAstarMap.setWalkable(j, i, false);
                    }
                }
            }
            List<Point> path = myAstarMap.findPath(0, 0, Y_FINISH, X_FINISH);
            return !(path == null || path.isEmpty());
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Pathfinder broken =(: {}", e.getMessage());
        }
        return false;
    }
}
