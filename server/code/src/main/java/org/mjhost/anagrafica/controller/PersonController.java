package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

//    TODO: implement
//    @RequestMapping("/wedding")
//    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
//        return movieService.graph(limit == null ? 100 : limit);
//    }

}