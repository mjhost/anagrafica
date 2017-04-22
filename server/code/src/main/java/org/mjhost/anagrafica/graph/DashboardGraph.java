package org.mjhost.anagrafica.graph;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;
import org.mjhost.anagrafica.model.pojo.Dashboard;
import org.mjhost.anagrafica.repository.PersonRepository;
import org.mjhost.anagrafica.utils.test.GaioGraccoBuilder;
import org.mjhost.anagrafica.utils.test.SempronioGraccoBuilder;
import org.mjhost.anagrafica.utils.test.TiberioGraccoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class DashboardGraph {

    public static final String OUTPUT_KEY = "dashboard";

    @Autowired
    private PersonRepository personRepository;

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
                .type(new GraphQLList(new GraphQLTypeReference("Person")))
                .description("TODO")
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

                    return dashboard;
                })
            )
            .build();
    }
}
