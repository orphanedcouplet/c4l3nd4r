package com.oliverandcode.c4l3nd4r.controllers;

import com.oliverandcode.c4l3nd4r.models.data.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "locations")
public class LocationController {

    @Autowired
    private LocationDao locationDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "My Locations");
        model.addAttribute("locations", locationDao.findAll());
        return "locations/my-locations";
    }

}
