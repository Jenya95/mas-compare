package ru.sanevich.mas.lab4.model.map;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyMap {
    private final Cell[][] cells;
    private final Walkable agent;
}
