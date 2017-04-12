package org.mjhost.anagrafica.model.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.converter.EducationLevelConverter;
import org.mjhost.anagrafica.model.enumeration.EducationLevel;
import org.mjhost.anagrafica.model.relationship.Address;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.mjhost.anagrafica.model.relationship.Death;
import org.mjhost.anagrafica.model.relationship.Reference;
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

    @Property(name = "job_title")
    private String jobTitle;

    @Convert(EducationLevelConverter.class)
    @Property(name = "education_level")
    private EducationLevel educationLevel;

    @Property(name = "hobbies")
    private Set<String> hobbies;

    @Relationship(type = "BORN_IN", direction = Relationship.OUTGOING)
    private Birth birth;

    @Relationship(type = "HAS_ADDRESS", direction = Relationship.OUTGOING)
    private Set<Address> addresses = new HashSet<>();

    @Relationship(type = "HAS_REFERENCES", direction = Relationship.OUTGOING)
    private Set<Reference> references = new HashSet<>();

//    @Relationship(type = "GOT_MARRIED_AT", direction = Relationship.OUTGOING)
//    private Set<Wedding> weddings = new HashSet<>();

    @Relationship(type = "DEAD_IN", direction = Relationship.OUTGOING)
    private Death death;

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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

//    public List<Wedding> getWeddings() {
//        return weddings;
//    }
//
//    public void addWedding(Wedding wedding) {
//        this.weddings.add(wedding);
//    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Set<Reference> getReferences() {
        return references;
    }

    public void addReference(Reference reference) {
        this.references.add(reference);
    }

    public Death getDeath() {
        return death;
    }

    public void setDeath(Death death) {
        this.death = death;
    }
}