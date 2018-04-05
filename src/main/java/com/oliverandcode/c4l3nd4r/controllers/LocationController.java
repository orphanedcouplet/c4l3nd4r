package com.oliverandcode.c4l3nd4r.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocationController {

    @RequestMapping(value = "locations")
    public String index(Model model) {
        model.addAttribute("title", "My Locations");
        return "locations/my-locations";
    }

}
