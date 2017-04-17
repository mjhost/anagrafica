package org.mjhost.anagrafica.graph;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.schema.GraphQLSchema.newSchema;

@Component
public class GraphManager {

    @Autowired
    private PersonGraph personGraph;

    public GraphQL getGraph(String queryName) {
        GraphQLSchema schema = newSchema().query(personGraph.getQuery(queryName)).build();

        return new GraphQL(schema);
    }
}
