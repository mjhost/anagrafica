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
    public List<Map<String, Object>> findPeopleByName(@NotNull String query) throws PersonException {
//        TODO : MUST HANDLE DYNAMIC MESSAGES, EXCEPTIONS AND LOG
        try {
            ExecutionResult result = graphManager.getGraph().execute(URLDecoder.decode(query, "UTF-8"));
            List<GraphQLError> errors = result.getErrors();
            if (!CollectionUtils.isEmpty(errors)) {
                PersonException pe = new PersonException(env.getProperty("exception.graphql.query.execution"));
                pe.setErrors(errors);
                throw pe;
            } else {
                Map<String, Object> responseMap = (Map<String, Object>) result.getData();
                List<Map<String, Object>> people = (List<Map<String, Object>>) responseMap.get(PersonGraph.OUTPUT_KEY);

                return people;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}


//    private Map<String, Object> toD3Format(Collection<Movie> movies) {
//        List<Map<String, Object>> nodes = new ArrayList<>();
//        List<Map<String, Object>> rels = new ArrayList<>();
//        int i = 0;
//        Iterator<Movie> result = movies.iterator();
//        while (result.hasNext()) {
//            Movie movie = result.next();
//            nodes.add(map("title", movie.getTitle(), "label", "movie"));
//            int target = i;
//            i++;
//            for (Role role : movie.getRoles()) {
//                Map<String, Object> actor = map("title", role.getPerson().getName(), "label", "actor");
//                int source = nodes.indexOf(actor);
//                if (source == -1) {
//                    nodes.add(actor);
//                    source = i++;
//                }
//                rels.add(map("source", source, "target", target));
//            }
//        }
//        return map("nodes", nodes, "links", rels);
//    }

//    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
//        Map<String, Object> result = new HashMap<String, Object>(2);
//        result.put(key1, value1);
//        result.put(key2, value2);
//        return result;
//    }
