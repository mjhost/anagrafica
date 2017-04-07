package org.mjhost.anagrafica.controller;

import org.mjhost.anagrafica.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(path = "/people/find")
    public List<Map<String, Object>> findPeople(@RequestParam(value = "query", required = true) String query) {
//        TODO: handle exceptions
        try {
            return personService.findPeople(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}