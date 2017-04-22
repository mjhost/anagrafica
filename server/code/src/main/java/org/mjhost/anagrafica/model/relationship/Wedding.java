package org.mjhost.anagrafica.model.relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.LocalDateTimeConverter;
import org.mjhost.anagrafica.model.node.Person;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "IS_MARRIED_TO")
public class Wedding {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Person consort;

    @Convert(LocalDateTimeConverter.class)
    @Property(name = "date")
    private LocalDateTime date;

    public Wedding() {
//        DO NOT DELETE
    }

    public Wedding(Person person, Person consort, LocalDateTime date) {
        setPerson(person);
        setConsort(consort);
        setDate(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getConsort() {
        return consort;
    }

    public void setConsort(Person consort) {
        this.consort = consort;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
