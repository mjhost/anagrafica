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

    public GraphQL getGraph(String queryName) {
//        TODO: externalize constants
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//            core pool size 2 thread
            2,
//            max pool size 2 threads
            2,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy()
        );

        GraphQLSchema schema = newSchema()
            .query(personGraph.getQuery(queryName))
            .build(new HashSet<>(Arrays.asList(
                locationGraph.location(), personGraph.person()))
            );

        return new GraphQL(schema, new ExecutorServiceExecutionStrategy(threadPoolExecutor), new SimpleExecutionStrategy());
    }
}