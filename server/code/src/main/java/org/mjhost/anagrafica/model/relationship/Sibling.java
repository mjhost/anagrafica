package org.mjhost.anagrafica.model.relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.node.Person;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "IS_SIBLING_OF")
public class Sibling {

    @GraphId
    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Person sibling;

    public Sibling() {
//        DO NOT DELETE
    }

    public Sibling(Person person, Person sibling) {
        setPerson(person);
        setSibling(sibling);
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

    public Person getSibling() {
        return sibling;
    }

    public void setSibling(Person sibling) {
        this.sibling = sibling;
    }
}
