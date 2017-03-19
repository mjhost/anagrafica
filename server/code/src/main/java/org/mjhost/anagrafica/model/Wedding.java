package org.mjhost.anagrafica.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.*;

import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "GOT_MARRIED_AT")
public class Wedding {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Parish parish;

    @Property(name = "date")
    private Date date;

    @Property(name = "document_record")
    private String documentRecord;

    public Wedding(Person person, Parish parish, Date date, String documentRecord) {
        setPerson(person);
        setParish(parish);
        setDate(date);
        setDocumentRecord(documentRecord);
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

    public Parish getParish() {
        return parish;
    }

    public void setParish(Parish parish) {
        this.parish = parish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDocumentRecord() {
        return documentRecord;
    }

    public void setDocumentRecord(String documentRecord) {
        this.documentRecord = documentRecord;
    }
}
