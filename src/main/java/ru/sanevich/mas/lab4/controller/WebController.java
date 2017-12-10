package ru.sanevich.mas.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.MyMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private final MyMap myMap;

    public WebController(MyMap myMap) {
        this.myMap = myMap;
    }

    @GetMapping
    public String showMap(Model model) {
        List<List<Cell>> rows = Arrays.stream(myMap.getCells())
                .map(Arrays::asList)
                .collect(Collectors.toList());

        model.addAttribute("rows", rows);
        return "map";
    }

}
