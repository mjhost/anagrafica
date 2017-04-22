package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Wedding;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class WeddingGraph {

    public GraphQLObjectType wedding() {
//        describe Contact type
        return newObject()
            .name("Wedding")
            .description("TODO")
            .field(f -> f
                .name("husband")
                .description("TODO")
                .type(new GraphQLNonNull(new GraphQLTypeReference("Person")))
                .dataFetcher(
                    environment -> {
                        Wedding wedding = ((Wedding) environment.getSource());
                        Person p = wedding.getPerson();
                        if (p.getSex().equals("M")) {
                            return p;
                        } else {
                            return wedding.getConsort();
                        }
                    }
                )
            )
            .field(f -> f
                .name("wife")
                .description("TODO")
                .type(new GraphQLNonNull(new GraphQLTypeReference("Person")))
                .dataFetcher(
                    environment -> {
                        Wedding wedding = ((Wedding) environment.getSource());
                        Person p = wedding.getPerson();
                        if (p.getSex().equals("F")) {
                            return p;
                        } else {
                            return wedding.getConsort();
                        }
                    }
                )
            )
            .field(f -> f
                .name("age")
                .description("TODO")
                .type(GraphQLString)
                .dataFetcher(
                    environment -> {
                        LocalDateTime date = ((Wedding) environment.getSource()).getDate();
                        Period p = Period.between(date.toLocalDate(), LocalDate.now());
                        return String.valueOf(p.getYears());
                    }
                )
            )
            .build();
    }
}
