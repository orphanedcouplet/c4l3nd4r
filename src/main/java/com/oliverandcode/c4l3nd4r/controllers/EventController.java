package com.oliverandcode.c4l3nd4r.controllers;

import com.oliverandcode.c4l3nd4r.models.Event;
import com.oliverandcode.c4l3nd4r.models.Location;
import com.oliverandcode.c4l3nd4r.models.data.EventDao;
import com.oliverandcode.c4l3nd4r.models.data.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

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
                                      @RequestParam int year,
                                      @RequestParam int month,
                                      @RequestParam int dayOfMonth,
                                      @RequestParam int hourOfDay,
                                      @RequestParam int minute,
                                      @RequestParam int endHour,
                                      @RequestParam int endMinute,
                                      Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Event");
            return "events/add-event";
        }

        Location location = locationDao.findOne(locationId);
        newEvent.setLocation(location);

        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.set(year, month, dayOfMonth, hourOfDay, minute);
        newEvent.setDateAndTime(dateAndTime);

        Calendar end = Calendar.getInstance();
        end.set(year, month, dayOfMonth, endHour, endMinute);
        newEvent.setEnd(end);

        eventDao.save(newEvent);

        return "redirect:";
    }

    // request path: /events/edit-event/{#}
    @RequestMapping(value = "edit-event/{eventId}", method = RequestMethod.GET)
    public String displayEditEventForm(Model model, @PathVariable int eventId) {

        model.addAttribute("title", "Edit Event");

        Event eventToEdit = eventDao.findOne(eventId);
        model.addAttribute("event", eventToEdit);

        Calendar dateAndTime = eventToEdit.getDateAndTime();
        model.addAttribute("year", dateAndTime.get(Calendar.YEAR));
        model.addAttribute("month",dateAndTime.get(Calendar.MONTH));
        model.addAttribute("dayOfMonth", dateAndTime.get(Calendar.DAY_OF_MONTH));
        model.addAttribute("hourOfDay", dateAndTime.get(Calendar.HOUR_OF_DAY));
        model.addAttribute("minute", dateAndTime.get(Calendar.MINUTE));

        Calendar end = eventToEdit.getEnd();
        if (end != null) {
            model.addAttribute("endHour", end.get(Calendar.HOUR_OF_DAY));
            model.addAttribute("endMinute", end.get(Calendar.MINUTE));
        }

        model.addAttribute("eventLocation", eventToEdit.getLocation());

        model.addAttribute("locations", locationDao.findAll());

        return "events/edit-event";
    }

    // request path: /events/edit-event/{#}
    @RequestMapping(value = "edit-event/{eventId}", method = RequestMethod.POST)
    public String processEditEventForm(@PathVariable int eventId,
                                       @RequestParam String title,
                                       @RequestParam String description,
                                       @RequestParam int year,
                                       @RequestParam int month,
                                       @RequestParam int dayOfMonth,
                                       @RequestParam int hourOfDay,
                                       @RequestParam int minute,
                                       @RequestParam int endHour,
                                       @RequestParam int endMinute,
                                       @RequestParam int locationId) {

        Event eventToEdit = eventDao.findOne(eventId);

        eventToEdit.setTitle(title);
        eventToEdit.setDescription(description);

        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.set(year, month, dayOfMonth, hourOfDay, minute);
        eventToEdit.setDateAndTime(dateAndTime);

        Calendar end = Calendar.getInstance();
        end.set(year, month, dayOfMonth, endHour, endMinute);
        eventToEdit.setEnd(end);

        Location location = locationDao.findOne(locationId);
        eventToEdit.setLocation(location);

        eventDao.save(eventToEdit);
        return "redirect:/events";
    }

    // request path: /events/delete-event
    @RequestMapping(value = "delete-event", method = RequestMethod.GET)
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", eventDao.findAll());
        return "events/delete-event";
    }

    // request path: /events/delete-event
    @RequestMapping(value = "delete-event", method = RequestMethod.POST)
    public String processDeleteEventForm(@RequestParam int[] eventIds) {

        for (int eventId : eventIds) {
            eventDao.delete(eventId);
        }

        return "redirect:";
    }

}
