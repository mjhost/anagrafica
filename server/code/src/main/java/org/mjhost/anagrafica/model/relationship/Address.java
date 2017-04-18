package org.mjhost.anagrafica.model.relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.ContactTypeConverter;
import org.mjhost.anagrafica.model.converter.LocalDateTimeConverter;
import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Person;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "HAS_ADDRESS")
public class Address {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Location location;

    @Convert(LocalDateTimeConverter.class)
    @Property(name = "since")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime since;

    @Convert(ContactTypeConverter.class)
    @Property(name = "type")
    private ContactType type;

    public Address() {
//        DO NOT DELETE
    }

    public Address(Person person, Location location, LocalDateTime since, ContactType type) {
        setPerson(person);
        setLocation(location);
        setSince(since);
        setType(type);
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getSince() {
        return since;
    }

    public void setSince(LocalDateTime since) {
        this.since = since;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }
}