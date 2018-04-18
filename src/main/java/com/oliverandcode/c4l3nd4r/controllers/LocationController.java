package com.oliverandcode.c4l3nd4r.controllers;

import com.oliverandcode.c4l3nd4r.models.Location;
import com.oliverandcode.c4l3nd4r.models.data.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "locations")
public class LocationController {

    @Autowired
    private LocationDao locationDao;

    // request path: /locations
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "My Locations");
        model.addAttribute("locations", locationDao.findAll());
        return "locations/my-locations";
    }

    @RequestMapping(value = "add-location", method = RequestMethod.GET)
    public String displayAddLocationForm(Model model) {
        model.addAttribute("title", "New Location");
        model.addAttribute(new Location());
        return "locations/add-location";
    }

    @RequestMapping(value = "add-location", method = RequestMethod.POST)
    public String processAddLocationForm(@ModelAttribute @Valid Location newLocation,
                                         Errors errors,
                                         Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Location");
            return "locations/add-location";
        }
        locationDao.save(newLocation);
        return "redirect:";
    }


}
