package org.mjhost.anagrafica;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.junit.Test;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class HelloGraphQLTest {

    @Test
    public void testHelloWorld() {
        GraphQLObjectType queryType = newObject()
            .name("helloWorldQuery")
            .field(
                newFieldDefinition()
                .type(GraphQLString)
                .name("hello")
                .staticValue("world")
            )
            .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
            .query(queryType)
            .build();

        Object result = new GraphQL(schema).execute("{hello}").getData();
        assertThat(result.toString(), equalTo("{hello=world}"));
    }
}