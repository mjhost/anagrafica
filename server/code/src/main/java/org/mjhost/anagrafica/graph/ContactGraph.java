package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLObjectType;
import org.springframework.stereotype.Component;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class ContactGraph {

    public GraphQLObjectType contact() {
//        describe Contact type
        return newObject()
            .name("Contact")
            .description("TODO")
            .field(f -> f
                .name("phone")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("email")
                .type(GraphQLString)
                .description("TODO")
            )
            .build();
    }
}
