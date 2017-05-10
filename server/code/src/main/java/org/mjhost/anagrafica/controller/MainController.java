package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.graph.GraphManager;
import org.mjhost.anagrafica.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final MainService mainService;

    private final GraphManager graphManager;

    @Autowired
    public MainController(MainService mainService, GraphManager graphManager) {
        this.mainService = mainService;
        this.graphManager = graphManager;
    }

    @RequestMapping(path = "/graphql/{context}/{queryName}", method = RequestMethod.POST)
    public List<Map<String, Object>> graphQL(
        @PathVariable(value = "context") String context,
        @PathVariable(value = "queryName") String queryName,
        @RequestBody String query
    ) {
//        TODO: handle exceptions
        try {
            Method handler = mainService.getClass().getDeclaredMethod(
                graphManager.getRequestHandler(context, queryName), String.class, String.class
            );
            return (List<Map<String, Object>>) handler.invoke(mainService, queryName, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}