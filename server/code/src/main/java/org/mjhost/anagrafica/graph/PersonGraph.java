package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class PersonGraph {

    public static final String OUTPUT_KEY = "person";

    public static final String NAME_KEY = "name";

    @Autowired
    private PersonRepository personRepository;

//    TODO: avoid hardcoded strings
    public GraphQLObjectType person() {
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
                .name("jobTitle")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("educationLevel")
                .type(GraphQLString)
                .description("TODO")
            )
            .field(f -> f
                .name("hobbies")
                .type(new GraphQLList(GraphQLString))
                .description("TODO")
            )
            .build();

//        WARN: WHAT ABOUT RELATIONSHIPS???
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
                .type(new GraphQLList(person()))
//                query must expect some input params
                .argument(a -> a
                    .name(NAME_KEY)
                    .description("Person first and last name as an input parameter")
                    .type(GraphQLString)
                )
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
//                    environment.getSource() is the value of the surrounding object. In this case described by objectType

                    Map<String, Object> arguments = environment.getArguments();
                    Object name = arguments.get(NAME_KEY);
                    if(!StringUtils.isEmpty(name)) {
//                        remove extra spaces
                        StringBuilder sb = new StringBuilder();
                        Arrays.stream(((String) name).split("\\s{1,}")).forEach(t -> sb.append(" ".concat(t)));
                        return personRepository.findByName(sb.substring(1));
                    } else {
                        return null;
                    }
                })
            )
            .build();
    }
}