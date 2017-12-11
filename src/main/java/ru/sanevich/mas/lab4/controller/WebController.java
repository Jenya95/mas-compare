package ru.sanevich.mas.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sanevich.mas.lab4.core.StepService;
import ru.sanevich.mas.lab4.model.agent.Agent;
import ru.sanevich.mas.lab4.model.agent.SmartAgent;
import ru.sanevich.mas.lab4.model.agent.StupidAgent;
import ru.sanevich.mas.lab4.model.map.Cell;
import ru.sanevich.mas.lab4.model.map.Empty;
import ru.sanevich.mas.lab4.model.map.FinishPoint;
import ru.sanevich.mas.lab4.model.map.MyMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sanevich.mas.lab4.core.CommonData.*;
import static ru.sanevich.mas.lab4.core.CommonData.Y_FINISH;

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

    @PostMapping("start")
    public String startNewMap(RedirectAttributes redirectAttributes, @RequestParam("optionsRadios") String param) {
        FINISH_ACHIEVED = false;
        stepService.setCounter(0);

        Cell[][] cells = myMap.getCells();

        for (int i = 0; i < HEIGHT_MAP; i++) {
            for (int j = 0; j < WIDTH_MAP; j++) {
                if (cells[i][j].getItem() instanceof Agent)
                    cells[i][j].setItem(new Empty());
            }
        }

        cells[X_FINISH][Y_FINISH].setItem(new FinishPoint());

        if (param.equals("smart")) {
            SmartAgent smartAgent = new SmartAgent(cells[0][0], new HashMap<>());
            cells[0][0].setItem(smartAgent);
        } else {
            StupidAgent stupidAgent = new StupidAgent(cells[0][0], new ArrayList<>());
            cells[0][0].setItem(stupidAgent);
        }

        redirectAttributes.addFlashAttribute("start", true);
        return "redirect:/";
    }

}
