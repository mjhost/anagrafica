package org.mjhost.anagrafica.model.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.mjhost.anagrafica.model.relationship.Address;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.mjhost.anagrafica.model.relationship.Death;
import org.mjhost.anagrafica.model.relationship.Wedding;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedList;
import java.util.List;

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

    @Relationship(type = "BORN_IN", direction = Relationship.OUTGOING)
    private Birth birth;

    @Relationship(type = "HAS_ADDRESS", direction = Relationship.OUTGOING)
    private List<Address> addresses = new LinkedList<>();

    @Relationship(type = "GOT_MARRIED_AT", direction = Relationship.OUTGOING)
    private List<Wedding> weddings = new LinkedList<>();

    @Relationship(type = "DEAD_IN", direction = Relationship.OUTGOING)
    private Death death;










//    job_title : "Electrical Engineer",
//    education_level : "et magnis dis parturient montes",
//    hobbies : "modellismo",
//    home_phone : "62-(383)111-2888"











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

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public List<Wedding> getWeddings() {
        return weddings;
    }

    public void addWedding(Wedding wedding) {
        this.weddings.add(wedding);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Death getDeath() {
        return death;
    }

    public void setDeath(Death death) {
        this.death = death;
    }
}
