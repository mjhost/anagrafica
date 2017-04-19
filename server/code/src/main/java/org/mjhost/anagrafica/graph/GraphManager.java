package org.mjhost.anagrafica.graph;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static graphql.schema.GraphQLSchema.newSchema;

@Component
public class GraphManager {

    @Autowired
    private PersonGraph personGraph;

    @Autowired
    private LocationGraph locationGraph;

    @Autowired
    private ContactGraph contactGraph;

    @Autowired
    private DashboardGraph dashboardGraph;

    private Set<GraphQLType> dictionary;
    @PostConstruct
    public void init() {
        dictionary = new HashSet<>(Arrays.asList(
            dashboardGraph.dashboard(), contactGraph.contact(), locationGraph.location(), personGraph.person()
        ));
    }

    public GraphQL getPersonGraph(String queryName) {
//        TODO: externalize constants
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
////            core pool size 2 thread
//            1,
////            max pool size 2 threads
//            10,
//            10,
//            TimeUnit.SECONDS,
//            new LinkedBlockingQueue<Runnable>(),
//            new ThreadPoolExecutor.CallerRunsPolicy()
//        );

        GraphQLSchema schema = newSchema()
            .query(personGraph.getQuery(queryName))
            .build(dictionary);

//        WARN: THIS DOES NOT WORK, DON'T KNOW WHY
//        return new GraphQL(schema, new ExecutorServiceExecutionStrategy(threadPoolExecutor), new SimpleExecutionStrategy());

        return new GraphQL(schema);
    }

    public GraphQL getDashboardGraph(String queryName) {
        GraphQLSchema schema = newSchema()
            .query(dashboardGraph.getQuery(queryName))
            .build(dictionary);

        return new GraphQL(schema);
    }
}