package org.mjhost.anagrafica.graph;

import graphql.ExecutionResult;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.repository.PersonRepositoryTest;
import org.mjhost.anagrafica.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonGraphTest extends PersonRepositoryTest {

    @Autowired
    private GraphManager graphManager;

//    @Test
//    public void testFindByFirstName() {
//        Person bride = getLady();
//
////        execute query
//        try {
//            String graphQLQuery;
//            ExecutionResult result;
//            Object data;
//            Map<String, Object> map;
//            List<Map<String, Object>> list;
//
////            TODO: any API for building the query???
//            graphQLQuery = String.format(
//                "{%s(%s:\"%s\"){firstName,lastName}}",
//                PersonGraph.OUTPUT_KEY,
//                PersonGraph.FIRST_NAME_KEY, bride.getFirstName()
//            );
//            result = graphManager.getGraph().execute(graphQLQuery);
//
//            assertThat(CollectionUtils.isEmpty(result.getErrors()), is(true));
//            assertThat(result.getData(), notNullValue());
//
//            data = result.getData();
//            assertThat(data instanceof Map, is(true));
//
//            map = (Map<String, Object>) data;
//            assertThat(map.get(PersonGraph.OUTPUT_KEY), notNullValue());
//
//            data = map.get(PersonGraph.OUTPUT_KEY);
//            assertThat(data instanceof List, is(true));
//
//            list = (List<Map<String, Object>>) data;
//            assertThat(CollectionUtils.isEmpty(list), is(false));
//            assertThat(list.size(), equalTo(1));
//
//            map = list.get(0);
//            assertThat(map.get("firstName"), equalTo(bride.getFirstName()));
//            assertThat(map.get("lastName"), equalTo(bride.getLastName()));
//        } catch (Exception e) {
//            fail(e.getLocalizedMessage());
//        }
//    }
//
//    @Test
//    public void testFindByLastName() {
//        Person bride = getLady();
//
////        execute query
//        try {
//            String graphQLQuery;
//            ExecutionResult result;
//            Object data;
//            Map<String, Object> map;
//            List<Map<String, Object>> list;
//
////            TODO: any API for building the query???
//            graphQLQuery = String.format(
//                "{%s(%s:\"%s\"){firstName,lastName}}",
//                PersonGraph.OUTPUT_KEY,
//                PersonGraph.LAST_NAME_KEY, bride.getLastName()
//            );
//            result = graphManager.getGraph().execute(graphQLQuery);
//
//            assertThat(CollectionUtils.isEmpty(result.getErrors()), is(true));
//            assertThat(result.getData(), notNullValue());
//
//            data = result.getData();
//            assertThat(data instanceof Map, is(true));
//
//            map = (Map<String, Object>) data;
//            assertThat(map.get(PersonGraph.OUTPUT_KEY), notNullValue());
//
//            data = map.get(PersonGraph.OUTPUT_KEY);
//            assertThat(data instanceof List, is(true));
//
//            list = (List<Map<String, Object>>) data;
//            assertThat(CollectionUtils.isEmpty(list), is(false));
//            assertThat(list.size(), equalTo(1));
//
//            map = list.get(0);
//            assertThat(map.get("firstName"), equalTo(bride.getFirstName()));
//            assertThat(map.get("lastName"), equalTo(bride.getLastName()));
//        } catch (Exception e) {
//            fail(e.getLocalizedMessage());
//        }
//    }

    @Test
    public void testFindByName() {
        Person lady = ModelUtils.getLady();

//        execute query
        try {
            String graphQLQuery;
            ExecutionResult result;
            Object data;
            Map<String, Object> map;
            List<Map<String, Object>> list;

//            first ask both fistName and lastName
//            TODO: any API for building the query???
            graphQLQuery = String.format(
                "{%s(%s:\"%s\"){firstName,lastName}}",
                PersonGraph.OUTPUT_KEY,
                PersonGraph.NAME_KEY, lady.getLastName()
            );
            result = graphManager.getGraph("findByName").execute(graphQLQuery);

            assertThat(CollectionUtils.isEmpty(result.getErrors()), is(true));
            assertThat(result.getData(), notNullValue());

            data = result.getData();
            assertThat(data instanceof Map, is(true));

            map = (Map<String, Object>) data;
            assertThat(map.get(PersonGraph.OUTPUT_KEY), notNullValue());

            data = map.get(PersonGraph.OUTPUT_KEY);
            assertThat(data instanceof List, is(true));

            list = (List<Map<String, Object>>) data;
            assertThat(CollectionUtils.isEmpty(list), is(false));
            assertThat(list.size(), equalTo(1));

            map = list.get(0);
            assertThat(map.get("firstName"), equalTo(lady.getFirstName()));
            assertThat(map.get("lastName"), equalTo(lady.getLastName()));

//            then ask firstName only
            graphQLQuery = String.format(
                "{%s(%s:\"%s\"){firstName}}",
                PersonGraph.OUTPUT_KEY,
                PersonGraph.NAME_KEY, lady.getLastName()
            );
            result = graphManager.getGraph("findByName").execute(graphQLQuery);

            assertThat(CollectionUtils.isEmpty(result.getErrors()), is(true));
            assertThat(result.getData(), notNullValue());

            data = result.getData();
            assertThat(data instanceof Map, is(true));

            map = (Map<String, Object>) data;
            assertThat(map.get(PersonGraph.OUTPUT_KEY), notNullValue());

            data = map.get(PersonGraph.OUTPUT_KEY);
            assertThat(data instanceof List, is(true));

            list = (List<Map<String, Object>>) data;
            assertThat(CollectionUtils.isEmpty(list), is(false));
            assertThat(list.size(), equalTo(1));

            map = list.get(0);
            assertThat(map.get("firstName"), equalTo(lady.getFirstName()));
            assertThat(map.get("lastName"), nullValue());
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}