package org.mjhost.anagrafica.model.pojo;

import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Wedding;

import java.util.List;

public class Dashboard {

    private List<Person> birthdays;

    private List<Wedding> weddings;

    List<Person> deaths;

    public List<Person> getBirthdays() {
        return birthdays;
    }

    public void setBirthdays(List<Person> birthdays) {
        this.birthdays = birthdays;
    }

    public List<Wedding> getWeddings() {
        return weddings;
    }

    public void setWeddings(List<Wedding> weddings) {
        this.weddings = weddings;
    }

    public List<Person> getDeaths() {
        return deaths;
    }

    public void setDeaths(List<Person> deaths) {
        this.deaths = deaths;
    }
}
