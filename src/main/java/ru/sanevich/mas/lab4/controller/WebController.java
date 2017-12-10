package ru.sanevich.mas.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sanevich.mas.lab4.core.StepService;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.MyMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sanevich.mas.lab4.core.CommonData.FINISH_ACHIEVED;

@Controller
@RequestMapping("/")
public class WebController {


    private final MyMap myMap;
    private final StepService stepService;

    @Autowired
    public WebController(MyMap myMap, StepService stepService) {
        this.myMap = myMap;
        this.stepService = stepService;
    }

    @GetMapping
    public String showMapInit(Model model) {
        List<List<Cell>> rows = Arrays.stream(myMap.getCells())
                .map(Arrays::asList)
                .collect(Collectors.toList());

        model.addAttribute("rows", rows);
        return "map";
    }

    @GetMapping("generate")
    public String iterateMap(Model model) {
        String msg = "";

        if (!FINISH_ACHIEVED) {
            stepService.doStep();
        } else {
            msg = stepService.getMessage();
        }

        List<List<Cell>> rows = Arrays.stream(myMap.getCells())
                .map(Arrays::asList)
                .collect(Collectors.toList());

        if (!msg.isEmpty()) {
            model.addAttribute("msg", msg);
        }

        model.addAttribute("rows", rows);
        return "map :: mymap";
    }

}
