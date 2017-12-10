package ru.sanevich.mas.lab4.model.map;

import lombok.Builder;
import lombok.Getter;

import static ru.sanevich.mas.lab4.core.CommonData.HEIGHT_MAP;
import static ru.sanevich.mas.lab4.core.CommonData.WIDTH_MAP;

@Builder
@Getter
public class MyMap {
    private final Cell[][] cells;

    public Walkable getAgent() {

        Walkable agent = null;

        for (int i = 0; i < HEIGHT_MAP; i++) {
            for (int j = 0; j < WIDTH_MAP; j++) {
                if (cells[i][j].getItem() instanceof Walkable) {
                    agent = (Walkable) cells[i][j].getItem();
                    agent.setCurrentCell(cells[i][j]);
                }
            }
        }

        return agent;
    }
}
