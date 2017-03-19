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

    private GraphQLSchema schema;

    private GraphQL graph;

    public GraphQLSchema getSchema() {
        if (schema == null) {
            schema = newSchema()
//                person graph queries
                .query(personGraph.findByName())
                .build();
        }

        return schema;
    }

    public GraphQL getGraph() {
        if (graph == null) {
            graph = new GraphQL(getSchema());
        }

        return graph;
    }
}
