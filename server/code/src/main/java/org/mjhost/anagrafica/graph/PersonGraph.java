package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class PersonGraph {

    public static final String OUTPUT_KEY = "people";

    public static final String FIRST_NAME_KEY = "fn";

    public static final String LAST_NAME_KEY = "ln";

    @Autowired
    private PersonRepository personRepository;

//    TODO: avoid hardcoded strings
    public GraphQLObjectType type() {
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
            .field(f -> f
                .name("sex")
                .type(new GraphQLNonNull(GraphQLString))
                .description("TODO")
            )
            .field(f -> f
                .name("birthDate")
                .type(GraphQLLong)
                .description("TODO")
            )
            .build();
    }

    public GraphQLObjectType findByName() {
        return newObject()
//            query name
            .name(getClass().getName() + "#findByName")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(OUTPUT_KEY)
                .description("The output of the query will be an instance of person type")
//                query must return a single person type
                .type(new GraphQLList(type()))
//                query must expect some input params
                .argument(a -> a
                    .name(FIRST_NAME_KEY)
                    .description("Person first_name as an input parameter")
                    .type(GraphQLString)
                )
                .argument(a -> a
                    .name(LAST_NAME_KEY)
                    .description("Person first_name as an input parameter")
                    .type(GraphQLString)
                )
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
//                    environment.getSource() is the value of the surrounding object. In this case described by objectType

                    Map<String, Object> arguments = environment.getArguments();
                    Object fn = arguments.get(FIRST_NAME_KEY);
                    Object ln = arguments.get(LAST_NAME_KEY);

//                    TODO: CAN WE HANDLE NULL PARAMETERS IN CYPHER?
                    if (fn != null && ln != null) {
                        return personRepository.findByName((String) fn, (String) ln);
                    } else if (fn != null) {
                        return personRepository.findByFirstName((String) fn);
                    } else if (ln != null) {
                        return personRepository.findByLastName((String) ln);
                    } else {
                        return null;
                    }
                })
            )
            .build();
    }
}