package org.mjhost.anagrafica.model.relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.LocalDateConverter;
import org.mjhost.anagrafica.model.node.Organization;
import org.mjhost.anagrafica.model.node.Person;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.time.LocalDate;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "GOT_MARRIED_AT")
public class Wedding {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Organization parish;

    @Convert(LocalDateConverter.class)
    @Property(name = "date")
    private LocalDate date;

    @Property(name = "document_record")
    private String documentRecord;

    public Wedding() {
//        DO NOT DELETE
    }

    public Wedding(Person person, Organization parish, LocalDate date, String documentRecord) {
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

    public Organization getParish() {
        return parish;
    }

    public void setParish(Organization parish) {
        this.parish = parish;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDocumentRecord() {
        return documentRecord;
    }

    public void setDocumentRecord(String documentRecord) {
        this.documentRecord = documentRecord;
    }
}
