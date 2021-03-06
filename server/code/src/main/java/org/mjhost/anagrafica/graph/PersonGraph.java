package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;
import org.mjhost.anagrafica.model.node.Contact;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.relationship.Birth;
import org.mjhost.anagrafica.model.relationship.Death;
import org.mjhost.anagrafica.model.relationship.Wedding;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.mjhost.anagrafica.utils.TrimUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static graphql.Scalars.GraphQLBigInteger;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class PersonGraph {

    public static final String OUTPUT_KEY = "person";

    public static final String ID_KEY = "id";

    public static final String NAME_KEY = "name";

    public static final String HOBBY_KEY = "hobby";

    public static final String EMPLOYMENT_KEY = "employment";

    @Autowired
    private PersonRepository personRepository;

    private Map<String, GraphQLObjectType> nameToQueryMap;

    @PostConstruct
    public void init() {
        nameToQueryMap = new HashMap<>();
        nameToQueryMap.put("findById", findById());
        nameToQueryMap.put("findByName", findByName());
        nameToQueryMap.put("findByHobby", findByHobby());
        nameToQueryMap.put("findByEmployment", findByEmployment());
    }

    public GraphQLObjectType getQuery(String name) {
        return nameToQueryMap.get(name);
    }

//    TODO: avoid hardcoded strings
    public GraphQLObjectType person() {
//        describe Person type
        return newObject()
            .name("Person")
            .description("TODO")
            .field(f -> f
                .name("id")
//                TODO: make it non nullable
                .type(new GraphQLNonNull(GraphQLBigInteger))
                .description("TODO")
            )
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
//            this is a computed field
            .field(f -> f
                .name("age")
                .type(GraphQLString)
                .description("TODO")
                .dataFetcher(
                    environment -> {
                        Birth birth = ((Person) environment.getSource()).getBirth();
                        if (birth != null) {
                            LocalDateTime bd = birth.getDate();
                            Period p = Period.between(bd.toLocalDate(), LocalDate.now());
                            return String.valueOf(p.getYears());
                        } else {
                            return null;
                        }
                    }
                )
            )
//            this is a computed field
            .field(f -> f
                .name("dead")
                .type(GraphQLString)
                .description("TODO")
                .dataFetcher(
                    environment -> {
                        Death death = ((Person) environment.getSource()).getDeath();
                        if (death != null) {
                            LocalDateTime dd = death.getDate();
                            Period p = Period.between(dd.toLocalDate(), LocalDate.now());
                            return String.valueOf(p.getYears());
                        } else {
                            return null;
                        }
                    }
                )
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
                .dataFetcher(
                    environment -> {
                        return ((Person) environment.getSource()).getEducationLevel().getValue();
                    }
                )
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
            .field(f -> f
                .name("employments")
                .type(new GraphQLList(GraphQLString))
                .description("TODO")
                .dataFetcher(
                    environment -> {
//                        environment.getSource() is the value of the surrounding object. In this case described by objectType
                        List<String> employments = new LinkedList<>();
                        ((Person) environment.getSource()).getEmployments().stream().forEach(e -> employments.add(e.getJob().getName()));
                        return employments;
                    }
                )
            )
            .field(f -> f
                .name("references")
                .type(new GraphQLList(new GraphQLTypeReference("Contact")))
                .description("TODO")
                .dataFetcher(
                    environment -> {
//                        environment.getSource() is the value of the surrounding object. In this case described by objectType
                        List<Contact> references = new LinkedList<>();
                        ((Person) environment.getSource()).getReferences().stream().forEach(r -> references.add(r.getContact()));
                        return references;
                    }
                )
            )
            .field(f -> f
                .name("addresses")
                .type(new GraphQLList(new GraphQLTypeReference("Location")))
                .description("TODO")
                .dataFetcher(
                    environment -> {
//                        environment.getSource() is the value of the surrounding object. In this case described by objectType
                        List<Location> addresses = new LinkedList<>();
                        ((Person) environment.getSource()).getAddresses().stream().forEach(a -> addresses.add(a.getLocation()));
                        return addresses;
                    }
                )
            )
            .field(f -> f
                .name("consort")
                .type(new GraphQLTypeReference("Person"))
                .description("TODO")
                .dataFetcher(
                    environment -> {
                        Wedding wedding = ((Person) environment.getSource()).getWedding();
                        if (wedding != null) {
                            return wedding.getConsort();
                        } else {
                            return null;
                        }
                    }
                )
            )
            .field(f -> f
                .name("children")
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
                .description("TODO")
                .dataFetcher(
                    environment -> {
                        List<Person> children = new LinkedList<>();
                        ((Person) environment.getSource()).getChildren().stream().forEach(c -> children.add(c));
                        return children;
                    }
                )
            )
            .field(f -> f
                .name("parents")
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
                .description("TODO")
                .dataFetcher(
                    environment -> {
                        List<Person> parents = new LinkedList<>();
                        ((Person) environment.getSource()).getParents().stream().forEach(p -> parents.add(p));
                        return parents;
                    }
                )
            )
            .field(f -> f
                .name("siblings")
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
                .description("TODO")
                .dataFetcher(
                    environment -> {
                        List<Person> siblings = new LinkedList<>();
                        ((Person) environment.getSource()).getSiblings().stream().forEach(s -> siblings.add(s));
                        return siblings;
                    }
                )
            )
            .build();
    }

    public GraphQLObjectType findById() {
        return newObject()
//            query name
            .name("findById")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(OUTPUT_KEY)
                .description("The output of the query will be an instance of person type")
//                query must return a list of person type
                .type(new GraphQLTypeReference("Person"))
                .argument(a -> a
                    .name(ID_KEY)
                    .description("Person id as an input parameter")
                    .type(GraphQLBigInteger)
                )
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
                    Map<String, Object> arguments = environment.getArguments();
                    Object id = arguments.get(ID_KEY);
                    if(id != null) {
                        return personRepository.findById(Long.valueOf(id.toString()));
                    } else {
                        return null;
                    }
                })
            )
            .build();
    }

    public GraphQLObjectType findByName() {
        return newObject()
//            query name
            .name("findByName")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(OUTPUT_KEY)
                .description("The output of the query will be a list of instances of person type")
//                query must return a list of person type
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
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
//                query must return a list of person type
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
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

    public GraphQLObjectType findByEmployment() {
        return newObject()
//            query name
            .name("findByEmployment")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(OUTPUT_KEY)
                .description("The output of the query will be an instance of person type")
//                query must return a list of person type
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
//                query must expect some input params
                .argument(a -> a
                    .name(EMPLOYMENT_KEY)
                    .description("Person employment name as an input parameter")
                    .type(GraphQLString)
                )
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
                    Map<String, Object> arguments = environment.getArguments();
                    Object employment = arguments.get(EMPLOYMENT_KEY);
                    if(!StringUtils.isEmpty(employment)) {
                        return personRepository.findByEmployment(TrimUtils.smartTrim((String) employment));
                    } else {
                        return null;
                    }
                })
            )
            .build();
    }
}