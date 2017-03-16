package org.mjhost;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLSchema.newSchema;
import static org.junit.Assert.fail;

public class PersonGraphQLTest {

    @Test
    public void testPersonQuery() {
//        create output type
        GraphQLObjectType person = newObject()
            .name("Person")
            .description("TODO")
            .field(f ->
                f.name("firstName")
                .type(new GraphQLNonNull(GraphQLString))
                .description("TODO")
            )
            .field(f ->
                f.name("lastName")
                .type(new GraphQLNonNull(GraphQLString))
                .description("TODO")
            )
            .build();

//        create query
        GraphQLObjectType findGroomByBride = newObject()
//            query name
            .name("findGroomByBrideName")
//            field is what the query has to return
            .field(f ->
                f.name("person")
//                query must return a single person type
                .type(person)
//                query must expect an input param
                .argument(a ->
                    a.name("bfn").description("Bride first_name as a non-null input parameter")
                    .type(new GraphQLNonNull(GraphQLString))
                )
                .argument(a ->
                    a.name("bln").description("Bride last as a non-null input parameter")
                    .type(new GraphQLNonNull(GraphQLString))
                )
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
//                    environment.getSource()

                    Map<String, Object> result = new HashMap<>();
                    result.put("firstName", "Massimo");
                    result.put("lastName", "Manfredino");

                    return result;
                })
            )
            .build();
//
//            create schema
            GraphQLSchema schema = newSchema()
                .query(findGroomByBride)
                .build();

//            create graph
            GraphQL graphQL = new GraphQL(schema);

            try {
                ExecutionResult result = graphQL.execute("{person(bfn:\"Foo\" bln:\"Bar\"){lastName}}");
                if (!CollectionUtils.isEmpty(result.getErrors())) {
                    result.getErrors().forEach(
                        ge -> {
                            System.out.println(ge.getMessage());
                            System.out.println(ge.getErrorType());
                            ge.getLocations().forEach( l -> System.out.println(l.toString()) );
                        }
                    );
                    fail();
                } else {
                    if (result.getData() instanceof Map) {
                        System.out.println("is map");
                    }
                    System.out.println(result.getData());
                }
            } catch (Exception e) {
                fail(e.getLocalizedMessage());
            }
    }
}
