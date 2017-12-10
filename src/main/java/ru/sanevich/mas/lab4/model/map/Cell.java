package ru.sanevich.mas.lab4.model.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.sanevich.mas.lab4.model.Item;
import ru.sanevich.mas.lab4.model.agent.SmartAgent;
import ru.sanevich.mas.lab4.model.agent.StupidAgent;

@Getter
@AllArgsConstructor
@Setter
@ToString
public class Cell {
    private int x;
    private int y;
    private Item item;
}
