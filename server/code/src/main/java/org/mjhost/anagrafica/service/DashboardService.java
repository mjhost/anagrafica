package org.mjhost.anagrafica.service;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import org.mjhost.anagrafica.exception.PersonException;
import org.mjhost.anagrafica.graph.DashboardGraph;
import org.mjhost.anagrafica.graph.GraphManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@Service
@PropertySources({
    @PropertySource("classpath:exception_message.properties")
})
public class DashboardService {

    @Autowired
    private GraphManager graphManager;

    @Autowired
    private Environment env;

    @Transactional(readOnly = true)
    public Map<String, Object> getDashboard() {
        String queryName = "getDashboard";
//        TODO: externalize to properties file
        String query =
            "{dashboard{" +
                "birthdays{id firstName lastName age} " +
                "weddings{" +
                    "husband{id firstName lastName} " +
                    "wife{id firstName lastName} " +
                    "age " +
                "}" +
            "}}";

//        TODO : MUST HANDLE DYNAMIC MESSAGES, EXCEPTIONS AND LOG
        try {
            ExecutionResult result = graphManager.getDashboardGraph(queryName).execute(URLDecoder.decode(query, "UTF-8"));
            List<GraphQLError> errors = result.getErrors();
            if (!CollectionUtils.isEmpty(errors)) {
                PersonException pe = new PersonException(env.getProperty("exception.graphql.query.execution"));
                pe.setErrors(errors);

                throw pe;
            } else {
                Map<String, Object> responseMap = (Map<String, Object>) result.getData();
                Map<String, Object> dashboard = (Map<String, Object>) responseMap.get(DashboardGraph.OUTPUT_KEY);

                return dashboard;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
