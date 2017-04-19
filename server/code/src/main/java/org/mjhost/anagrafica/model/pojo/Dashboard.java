package org.mjhost.anagrafica.model.pojo;

import org.mjhost.anagrafica.model.node.Person;

import java.util.List;

public class Dashboard {

    private List<Person> birthdays;

    public List<Person> getBirthdays() {
        return birthdays;
    }

    public void setBirthdays(List<Person> birthdays) {
        this.birthdays = birthdays;
    }
}
