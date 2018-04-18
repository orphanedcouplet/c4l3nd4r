package com.oliverandcode.c4l3nd4r.controllers;

import com.oliverandcode.c4l3nd4r.models.Event;
import com.oliverandcode.c4l3nd4r.models.Location;
import com.oliverandcode.c4l3nd4r.models.data.EventDao;
import com.oliverandcode.c4l3nd4r.models.data.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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

    // request path: /events/add-event
    @RequestMapping(value = "add-event", method = RequestMethod.GET)
    public String displayAddEventForm(Model model) {
        model.addAttribute("title", "New Event");
        model.addAttribute(new Event());
        model.addAttribute("locations", locationDao.findAll());
        return "events/add-event";
    }

    // request path: /events/add-event
    @RequestMapping(value = "add-event", method = RequestMethod.POST)
    public String processAddEventForm(@ModelAttribute @Valid Event newEvent,
                                      Errors errors,
                                      @RequestParam int locationId,
                                      Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Event");
            return "events/add-event";
        }

        Location location = locationDao.findOne(locationId);
        newEvent.setLocation(location);
        eventDao.save(newEvent);
        return "redirect:";
    }


}
