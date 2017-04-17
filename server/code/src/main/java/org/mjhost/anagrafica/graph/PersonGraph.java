package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.mjhost.anagrafica.utils.TrimUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class PersonGraph {

    public static final String OUTPUT_KEY = "person";

    public static final String NAME_KEY = "name";

    public static final String HOBBY_KEY = "hobby";

    @Autowired
    private PersonRepository personRepository;

    private Map<String, GraphQLObjectType> nameToQueryMap;

    @PostConstruct
    public void init() {
        nameToQueryMap = new HashMap<>();
        nameToQueryMap.put("findByName", findByName());
        nameToQueryMap.put("findByHobby", findByHobby());
    }

    public GraphQLObjectType getQuery(String name) {
        return nameToQueryMap.get(name);
    }

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
                .name("title")
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
                .dataFetcher(
                    environment -> {
//                        environment.getSource() is the value of the surrounding object. In this case described by objectType
                        List<String> hobbies = new LinkedList<>();
                        ((Person) environment.getSource()).getHobbies().stream().forEach(h -> hobbies.add(h.getSubject().getName()));
                        return hobbies;
                    }
                )
            )
            .build();

//        WARN: WHAT ABOUT RELATIONSHIPS???
    }

    public GraphQLObjectType findByName() {
        return newObject()
//            query name
            .name("findByName")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(OUTPUT_KEY)
                .description("The output of the query will be an instance of person type")
//                query must return many person type
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
                        return personRepository.findByName(TrimUtils.smartTrim((String) name));
                    } else {
                        return null;
                    }
                })
            )
            .build();
    }

    public GraphQLObjectType findByHobby() {
        return newObject()
//            query name
            .name("findByHobby")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(OUTPUT_KEY)
                .description("The output of the query will be an instance of person type")
//                query must return a single person type
                .type(new GraphQLList(person()))
//                query must expect some input params
                .argument(a -> a
                    .name(HOBBY_KEY)
                    .description("Person hobby name as an input parameter")
                    .type(GraphQLString)
                )
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
                    Map<String, Object> arguments = environment.getArguments();
                    Object hobby = arguments.get(HOBBY_KEY);
                    if(!StringUtils.isEmpty(hobby)) {
                        return personRepository.findByHobby(TrimUtils.smartTrim((String) hobby));
                    } else {
                        return null;
                    }
                })
            )
            .build();
    }
}