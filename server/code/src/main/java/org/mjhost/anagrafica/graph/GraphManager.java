package org.mjhost.anagrafica.graph;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

import static graphql.schema.GraphQLSchema.newSchema;
import static org.mjhost.anagrafica.enumeration.RequestHandler.*;
import static org.mjhost.anagrafica.enumeration.RequestUrlContext.*;
import static org.mjhost.anagrafica.enumeration.RequestUrlQuery.*;

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

    @Autowired
    private WeddingGraph weddingGraph;

    private Set<GraphQLType> dictionary;

    private Map<Map.Entry<String, String>, String> requestHandlerMap;

    @PostConstruct
    public void init() {
//        WARN: EXTERNALIZE TO ROUTE PROPERTIES FILE?
        requestHandlerMap = new HashMap<>();
        requestHandlerMap.put(
            new AbstractMap.SimpleEntry<String, String>(PEOPLE.toString(), FIND_BY_ID.toString()),
            FIND_PEOPLE.toString()
        );
        requestHandlerMap.put(
            new AbstractMap.SimpleEntry<String, String>(PEOPLE.toString(), FIND_BY_NAME.toString()),
            FIND_PEOPLE.toString()
        );
        requestHandlerMap.put(
            new AbstractMap.SimpleEntry<String, String>(PEOPLE.toString(), FIND_BY_HOBBY.toString()),
            FIND_PEOPLE.toString()
        );
        requestHandlerMap.put(
            new AbstractMap.SimpleEntry<String, String>(PEOPLE.toString(), FIND_BY_EMPLOYMENT.toString()),
            FIND_PEOPLE.toString()
        );
        requestHandlerMap.put(
            new AbstractMap.SimpleEntry<String, String>(SYSTEM.toString(), DASHBOARD.toString()),
            SYSTEM_DASHBOARD.toString()
        );

        dictionary = new HashSet<>(Arrays.asList(
            dashboardGraph.dashboard(), contactGraph.contact(), locationGraph.location(), personGraph.person(),
            weddingGraph.wedding()
        ));
    }

    public String getRequestHandler(String context, String query) {
        return requestHandlerMap.get(new AbstractMap.SimpleEntry<String, String>(context, query));
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