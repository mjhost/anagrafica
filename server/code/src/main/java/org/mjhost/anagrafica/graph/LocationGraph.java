package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLObjectType;
import org.springframework.stereotype.Component;

import static graphql.Scalars.GraphQLBigDecimal;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class LocationGraph {

    public GraphQLObjectType location() {
//        describe Location type
        return newObject()
            .name("Location")
            .description("TODO")
            .field(f -> f
                .name("name")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("street")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("zipCode")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("city")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("province")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("state")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("country")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("lat")
                .type(GraphQLBigDecimal)
                .description("TODO")
            )
            .field(f -> f
                .name("lon")
                .type(GraphQLBigDecimal)
                .description("TODO")
            )
            .build();
    }
}