package com.oliverandcode.c4l3nd4r.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    @RequestMapping(value = "events")
    public String index(Model model) {
        model.addAttribute("title", "My Events");
        return "events/my-events";
    }

}
