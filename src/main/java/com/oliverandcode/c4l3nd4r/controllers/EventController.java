package com.oliverandcode.c4l3nd4r.controllers;

import com.oliverandcode.c4l3nd4r.models.data.EventDao;
import com.oliverandcode.c4l3nd4r.models.data.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "events")
public class EventController {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private LocationDao locationDao;

    // request path: /events
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "My Events");
        model.addAttribute("events", eventDao.findAll());
        return "events/my-events";
    }

}
