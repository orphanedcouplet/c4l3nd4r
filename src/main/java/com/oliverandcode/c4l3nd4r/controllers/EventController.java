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
                                      @RequestParam int startHourOfDay,
                                      @RequestParam int startMinute,
                                      @RequestParam int endHourOfDay,
                                      @RequestParam int endMinute,
                                      Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Event");
            return "events/add-event";
        }

        Location location = locationDao.findOne(locationId);

        Calendar startDateAndTime = Calendar.getInstance();
        startDateAndTime.set(year, month, dayOfMonth, startHourOfDay, startMinute);

        Calendar endDateAndTime = Calendar.getInstance();
        endDateAndTime.set(year, month, dayOfMonth, endHourOfDay, endMinute);

        newEvent.setLocation(location);
        newEvent.setStartDateAndTime(startDateAndTime);
        newEvent.setEndDateAndTime(endDateAndTime);

        eventDao.save(newEvent);

        return "redirect:";
    }

    // request path: /events/edit-event/{#}
    @RequestMapping(value = "edit-event/{eventId}", method = RequestMethod.GET)
    public String displayEditEventForm(Model model, @PathVariable int eventId) {

        model.addAttribute("title", "Edit Event");

        Event eventToEdit = eventDao.findOne(eventId);
        model.addAttribute("event", eventToEdit);

        Calendar dateAndTime = eventToEdit.getStartDateAndTime();
        model.addAttribute("year", dateAndTime.get(Calendar.YEAR));
        model.addAttribute("month",dateAndTime.get(Calendar.MONTH));
        model.addAttribute("dayOfMonth", dateAndTime.get(Calendar.DAY_OF_MONTH));
        model.addAttribute("hourOfDay", dateAndTime.get(Calendar.HOUR_OF_DAY));
        model.addAttribute("minute", dateAndTime.get(Calendar.MINUTE));

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
                                       @RequestParam int locationId) {

        Event eventToEdit = eventDao.findOne(eventId);

        eventToEdit.setTitle(title);
        eventToEdit.setDescription(description);

        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.set(year, month, dayOfMonth, hourOfDay, minute);
        eventToEdit.setStartDateAndTime(dateAndTime);

        Location location = locationDao.findOne(locationId);
        eventToEdit.setLocation(location);

        eventDao.save(eventToEdit);
        return "redirect:";
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
