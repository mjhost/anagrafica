package org.mjhost.anagrafica.dao;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import org.apache.commons.collections4.CollectionUtils;
import org.mjhost.anagrafica.exception.PersonException;
import org.mjhost.anagrafica.graph.PersonGraph;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class PersonDao extends AbstractDao implements IPersonDao {

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findPeople(@NotNull String queryName, @NotNull String query) throws PersonException {
//        TODO : MUST HANDLE DYNAMIC MESSAGES, EXCEPTIONS AND LOG
        try {
            ExecutionResult result = getGraphManager().getPersonGraph(queryName).execute(URLDecoder.decode(query, "UTF-8"));
            List<GraphQLError> errors = result.getErrors();
            if (!CollectionUtils.isEmpty(errors)) {
                PersonException pe = new PersonException(getEnv().getProperty("exception.graphql.query.execution"));
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
