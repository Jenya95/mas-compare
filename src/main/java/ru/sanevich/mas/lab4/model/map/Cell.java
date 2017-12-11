package ru.sanevich.mas.lab4.model.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.sanevich.mas.lab4.model.Item;
import ru.sanevich.mas.lab4.model.agent.SmartAgent;
import ru.sanevich.mas.lab4.model.agent.StupidAgent;

import java.util.Objects;

@Getter
@AllArgsConstructor
@Setter
@ToString
public class Cell {
    private int x;
    private int y;
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
