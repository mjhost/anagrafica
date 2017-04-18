package org.mjhost.anagrafica.graph;

import graphql.GraphQL;
import graphql.execution.ExecutorServiceExecutionStrategy;
import graphql.execution.SimpleExecutionStrategy;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static graphql.schema.GraphQLSchema.newSchema;

@Component
public class GraphManager {

    @Autowired
    private PersonGraph personGraph;

    @Autowired
    private LocationGraph locationGraph;

    @Autowired
    private ContactGraph contactGraph;

    public GraphQL getGraph(String queryName) {
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
            .build(new HashSet<>(Arrays.asList(
                contactGraph.contact(), locationGraph.location(), personGraph.person()))
            );

//        WARN: THIS DOES NOT WORK, DON'T KNOW WHY
//        return new GraphQL(schema, new ExecutorServiceExecutionStrategy(threadPoolExecutor), new SimpleExecutionStrategy());

        return new GraphQL(schema);
    }
}