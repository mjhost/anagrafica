package org.mjhost.anagrafica.dao;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import org.mjhost.anagrafica.exception.PersonException;
import org.mjhost.anagrafica.exception.SystemException;
import org.mjhost.anagrafica.graph.DashboardGraph;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
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
public class DashboardDao extends AbstractDao implements IDashboardDao {

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> systemDashboard(String queryName, String query) throws SystemException {
//        TODO: externalize to properties file
        String qn = "getDashboard";
//        TODO: externalize to properties file
        String q =
            "{dashboard{" +
                "birthdays{id firstName lastName age} " +
                "weddings{" +
                    "husband{id firstName lastName} " +
                    "wife{id firstName lastName} " +
                    "age " +
                "} " +
                 "deaths{id firstName lastName dead consort{id firstName lastName} children{id firstName lastName} parents{id firstName lastName} siblings{id firstName lastName}}" +
            "}}";

//        TODO : MUST HANDLE DYNAMIC MESSAGES, EXCEPTIONS AND LOG
        try {
            ExecutionResult result = getGraphManager().getDashboardGraph(qn).execute(URLDecoder.decode(q, "UTF-8"));
            List<GraphQLError> errors = result.getErrors();
            if (!CollectionUtils.isEmpty(errors)) {
                PersonException pe = new PersonException(getEnv().getProperty("exception.graphql.query.execution"));
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
