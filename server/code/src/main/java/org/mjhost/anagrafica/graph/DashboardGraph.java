package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.model.pojo.Dashboard;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.mjhost.anagrafica.repository.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class DashboardGraph {

    public static final String OUTPUT_KEY = "dashboard";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WeddingRepository weddingRepository;

    private Map<String, GraphQLObjectType> nameToQueryMap;

    @PostConstruct
    public void init() {
        nameToQueryMap = new HashMap<>();
        nameToQueryMap.put("getDashboard", getDashboard());
    }

    public GraphQLObjectType getQuery(String name) {
        return nameToQueryMap.get(name);
    }

    public GraphQLObjectType dashboard() {
//        describe Dashboard type
        return newObject()
            .name("Dashboard")
            .description("TODO")
            .field(f -> f
                .name("birthdays")
                .description("TODO")
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
            )
            .field(f -> f
                .name("weddings")
                .description("TODO")
                .type(new GraphQLList(new GraphQLTypeReference("Wedding")))
            )
            .field(f -> f
                .name("deaths")
                .description("TODO")
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
            )
            .build();
    }

    public GraphQLObjectType getDashboard() {
        return newObject()
//            query name
            .name("getDashboard")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(OUTPUT_KEY)
                .description("The output of the query will be an instance of Dashboard type")
//                query must return a sigle instance of Dashboard type
                .type(new GraphQLTypeReference("Dashboard"))
//                query do not expect some input params
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
                    Dashboard dashboard = new Dashboard();

//                    WARN: THIS IS A MOCK
                    dashboard.setBirthdays(personRepository.findByName("Gracco"));
//                    WARN: THIS IS A MOCK
                    dashboard.setWeddings(weddingRepository.findAll());

//                    TODO: THIS MUST BE FIXED
                    List<Person> dead = personRepository.findDead();
                    dashboard.setDeaths(dead);

                    return dashboard;
                })
            )
            .build();
    }
}
