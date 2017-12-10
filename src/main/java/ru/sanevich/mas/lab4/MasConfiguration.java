package ru.sanevich.mas.lab4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sanevich.mas.lab4.model.agent.StupidAgent;
import ru.sanevich.mas.lab4.model.map.*;

import static ru.sanevich.mas.lab4.core.CommonData.HEIGHT_MAP;
import static ru.sanevich.mas.lab4.core.CommonData.WIDTH_MAP;

@Configuration
public class MasConfiguration {
    @Bean
    public MyMap initMap() {
        Cell[][] cells = new Cell[HEIGHT_MAP][WIDTH_MAP];
        Cell cell;


        for (int i = 0; i < HEIGHT_MAP; i++) {
            for (int j = 0; j < WIDTH_MAP; j++) {
                if (Math.random() < 0.2) {
                    cell = new Wall();
                } else {
                    cell = new Empty();
                }
                cells[i][j] = cell;
           }
        }

        StupidAgent stupidAgent = new StupidAgent(0,0);
        cells[0][0] = stupidAgent;

        cells[HEIGHT_MAP-1][WIDTH_MAP-1] = new FinishPoint();

        return MyMap.builder()
                .cells(cells)
                .agent(stupidAgent)
                .build();
    }
}
