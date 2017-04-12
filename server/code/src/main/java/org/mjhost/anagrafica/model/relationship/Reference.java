package org.mjhost.anagrafica.model.relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.ContactTypeConverter;
import org.mjhost.anagrafica.model.enumeration.ContactType;
import org.mjhost.anagrafica.model.node.Contact;
import org.mjhost.anagrafica.model.node.Person;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "HAS_REFERENCE")
public class Reference {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Contact contact;

    @Convert(ContactTypeConverter.class)
    @Property(name = "type")
    private ContactType type;

    public Reference(Person person, Contact contact, ContactType type) {
        setPerson(person);
        setContact(contact);
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }
}
