package org.mjhost.anagrafica.model.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.EducationLevelConverter;
import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.mjhost.anagrafica.model.relationship.*;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity
public class Person {

    @GraphId
    private Long id;

    @Property(name = "first_name")
    private String firstName;

    @Property(name = "last_name")
    private String lastName;

    @Property(name = "sex")
    private String sex;

    @Property(name = "title")
    private String title;

    @Convert(EducationLevelConverter.class)
    @Property(name = "education_level")
    private EducationLevel educationLevel;

    @Relationship(type = "HAS_ADDRESS", direction = Relationship.OUTGOING)
    private Set<Address> addresses = new HashSet<>();

    @Relationship(type = "BORN", direction = Relationship.OUTGOING)
    private Birth birth;

    @Relationship(type = "DEAD", direction = Relationship.OUTGOING)
    private Death death;

    @Relationship(type = "IS_PARENT_OF", direction = Relationship.OUTGOING)
    private Set<Person> children = new HashSet<>();

    @Relationship(type = "IS_CHILD_OF", direction = Relationship.OUTGOING)
    private Set<Person> parents = new HashSet<>();

    @Relationship(type = "IS_SIBLING_OF", direction = Relationship.OUTGOING)
    private Set<Person> siblings = new HashSet<>();

    @Relationship(type = "IS_EMPLOYED_AS", direction = Relationship.OUTGOING)
    private Set<Employment> employments = new HashSet<>();

    @Relationship(type = "HAS_HOBBY", direction = Relationship.OUTGOING)
    private Set<Hobby> hobbies = new HashSet<>();

    @Relationship(type = "HAS_REFERENCES", direction = Relationship.OUTGOING)
    private Set<Reference> references = new HashSet<>();

    @Relationship(type = "IS_MARRIED_TO", direction = Relationship.OUTGOING)
    private Wedding wedding;

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public Death getDeath() {
        return death;
    }

    public void setDeath(Death death) {
        this.death = death;
    }

    public Set<Person> getParents() {
        return parents;
    }

    public void addParent(Person child) {
        this.parents.add(child);
    }

    public Set<Person> getChildren() {
        return children;
    }

    public void addChild(Person parent) {
        this.children.add(parent);
    }

    public Set<Person> getSiblings() {
        return siblings;
    }

    public void addSibling(Person sibling) {
        this.siblings.add(sibling);
    }

    public Set<Employment> getEmployments() {
        return employments;
    }

    public void addEmployment(Employment employment) {
        this.employments.add(employment);
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void addHobby(Hobby hobby) {
        this.hobbies.add(hobby);
    }

    public Set<Reference> getReferences() {
        return references;
    }

    public void addReference(Reference reference) {
        this.references.add(reference);
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }
}