package ru.sanevich.mas.lab4.model.agent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.Walkable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StupidAgent extends Cell implements Walkable {

    private int X;
    private int Y;

    @Override
    public Cell makeStep() {
        return null;
    }

    @Override
    public String toString() {
        return "StupidAgent{}";
    }
}
