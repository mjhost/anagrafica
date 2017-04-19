package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public Map<String, Object> getDashboard() {
//        TODO: handle exceptions
        try {
            return dashboardService.getDashboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
