package org.mjhost.anagrafica.persistence.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedList;
import java.util.List;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

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

    @Relationship(type = "GOT_MARRIED_AT", direction = Relationship.OUTGOING)
    private List<Wedding> weddings = new LinkedList<>();

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

    public List<Wedding> getWeddings() {
        return weddings;
    }

    public void addWedding(Wedding wedding) {
        this.weddings.add(wedding);
    }

//    TODO: use a better design pattern
    public static GraphQLObjectType type() {
//        describe Person output
        return newObject()
            .name("Person")
            .description("TODO")
            .field(f -> f
                .name("firstName")
                .type(new GraphQLNonNull(GraphQLString))
                .description("TODO")
            )
            .field(f -> f
                .name("lastName")
                .type(new GraphQLNonNull(GraphQLString))
                .description("TODO")
            )
            .build();
    }
}
