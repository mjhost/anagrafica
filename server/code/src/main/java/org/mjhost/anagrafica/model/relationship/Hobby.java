package org.mjhost.anagrafica.model.relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.LocalDateTimeConverter;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.node.Subject;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "HAS_HOBBY")
public class Hobby {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Subject subject;

    @Convert(LocalDateTimeConverter.class)
    @Property(name = "since")
    private LocalDateTime since;

    public Hobby() {
//        DO NOT DELETE
    }

    public Hobby(Person person, Subject subject, LocalDateTime since) {
        setPerson(person);
        setSubject(subject);
        setSince(since);
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDateTime getSince() {
        return since;
    }

    public void setSince(LocalDateTime since) {
        this.since = since;
    }
}