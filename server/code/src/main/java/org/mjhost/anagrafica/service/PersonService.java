package org.mjhost.anagrafica.service;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import org.apache.commons.collections4.CollectionUtils;
import org.mjhost.anagrafica.exception.PersonException;
import org.mjhost.anagrafica.graph.GraphManager;
import org.mjhost.anagrafica.graph.PersonGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@PropertySources({
    @PropertySource("classpath:exception_message.properties")
})
public class PersonService {

    @Autowired
    private GraphManager graphManager;

    @Autowired
    private Environment env;

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findPeople(@NotNull String queryName, @NotNull String query) throws PersonException {
//        TODO : MUST HANDLE DYNAMIC MESSAGES, EXCEPTIONS AND LOG
        try {
            ExecutionResult result = graphManager.getPersonGraph(queryName).execute(URLDecoder.decode(query, "UTF-8"));
            List<GraphQLError> errors = result.getErrors();
            if (!CollectionUtils.isEmpty(errors)) {
                PersonException pe = new PersonException(env.getProperty("exception.graphql.query.execution"));
                pe.setErrors(errors);

                throw pe;
            } else {
                List<Map<String, Object>> people = null;
                Map<String, Object> responseMap = (Map<String, Object>) result.getData();
                Object payload = responseMap.get(PersonGraph.OUTPUT_KEY);
                if (payload instanceof Map) {
                    people = Arrays.asList((Map<String, Object>) payload);
                } else if (payload instanceof List) {
                    people = (List<Map<String, Object>>) payload;
                }

                return people;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}