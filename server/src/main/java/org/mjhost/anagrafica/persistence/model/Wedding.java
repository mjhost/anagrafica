package org.mjhost.anagrafica.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "GOT_MARRIED_AT")
public class Wedding {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Parish parish;

    private String documentRecord;

    public Wedding() {}

    public Wedding(Person person, Parish parish) {
        this.person = person;
        this.parish = parish;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Parish getParish() {
        return parish;
    }

    public String getDocumentRecord() {
        return documentRecord;
    }
}
