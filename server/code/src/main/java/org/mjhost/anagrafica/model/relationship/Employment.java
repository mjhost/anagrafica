package org.mjhost.anagrafica.model.relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.LocalDateTimeConverter;
import org.mjhost.anagrafica.model.node.Job;
import org.mjhost.anagrafica.model.node.Person;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "IS_EMPLOYED_AS")
public class Employment {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Job job;

    @Convert(LocalDateTimeConverter.class)
    @Property(name = "since")
    private LocalDateTime since;

    public Employment(Person person, Job job, LocalDateTime since) {
        setPerson(person);
        setJob(job);
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDateTime getSince() {
        return since;
    }

    public void setSince(LocalDateTime since) {
        this.since = since;
    }
}
