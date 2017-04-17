package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(path = "/person/{queryName}", method = RequestMethod.POST)
    public List<Map<String, Object>> findPeople(@PathVariable(value = "queryName") String queryName,
            @RequestBody String query) {
//        TODO: handle exceptions
        try {
            return personService.findPeople(queryName, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}