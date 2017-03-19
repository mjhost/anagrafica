package org.mjhost.anagrafica.service;

import org.mjhost.anagrafica.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

//    @Autowired
//    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findPeopleByName(@NotNull String firstName, @NotNull String lastName) {
//        MUST USE GRAPHQL HERE!!!!
//        List personRepository.findByName(firstName, lastName);




//        Collection<Movie> result = movieRepository.graph(limit);
//        return toD3Format(result);

//        TODO: implement
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
