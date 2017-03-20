package org.mjhost.anagrafica.graph;

import graphql.ExecutionResult;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.repository.WeddingRepositoryTest;
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
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@Transactional
public class WeddingGraphTest extends WeddingRepositoryTest {

    @Autowired
    private GraphManager graphManager;

    @Test
    public void testFindBrideByFirstName() {
//        execute query
        try {
            String graphQLQuery;
            ExecutionResult result;
            Object data;
            Map<String, Object> map;
            List<Map<String, Object>> list;

//            TODO: any API for building the query???
            graphQLQuery = String.format(
                "{%s(%s:\"%s\"){firstName,lastName}}",
                PersonGraph.OUTPUT_KEY,
                PersonGraph.FIRST_NAME_KEY, BRIDE_FIRST_NAME
            );
            result = graphManager.getGraph().execute(graphQLQuery);

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
            assertThat(map.get("firstName"), equalTo(BRIDE_FIRST_NAME));
            assertThat(map.get("lastName"), equalTo(BRIDE_LAST_NAME));
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testFindBrideByLastName() {
//        execute query
        try {
            String graphQLQuery;
            ExecutionResult result;
            Object data;
            Map<String, Object> map;
            List<Map<String, Object>> list;

//            TODO: any API for building the query???
            graphQLQuery = String.format(
                "{%s(%s:\"%s\"){firstName,lastName}}",
                PersonGraph.OUTPUT_KEY,
                PersonGraph.LAST_NAME_KEY, BRIDE_LAST_NAME
            );
            result = graphManager.getGraph().execute(graphQLQuery);

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
            assertThat(map.get("firstName"), equalTo(BRIDE_FIRST_NAME));
            assertThat(map.get("lastName"), equalTo(BRIDE_LAST_NAME));
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testFindBrideByName() {
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
                "{%s(%s:\"%s\",%s:\"%s\"){firstName,lastName}}",
                 PersonGraph.OUTPUT_KEY,
                 PersonGraph.FIRST_NAME_KEY, BRIDE_FIRST_NAME,
                 PersonGraph.LAST_NAME_KEY, BRIDE_LAST_NAME
            );
            result = graphManager.getGraph().execute(graphQLQuery);

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
            assertThat(map.get("firstName"), equalTo(BRIDE_FIRST_NAME));
            assertThat(map.get("lastName"), equalTo(BRIDE_LAST_NAME));

//            then ask firstName only
            graphQLQuery = String.format(
                "{%s(%s:\"%s\",%s:\"%s\"){firstName}}",
                PersonGraph.OUTPUT_KEY,
                PersonGraph.FIRST_NAME_KEY, BRIDE_FIRST_NAME,
                PersonGraph.LAST_NAME_KEY, BRIDE_LAST_NAME
            );
            result = graphManager.getGraph().execute(graphQLQuery);

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
            assertThat(map.get("firstName"), equalTo(BRIDE_FIRST_NAME));
            assertThat(map.get("lastName"), nullValue());
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

//    @Test
//    public void testFindGroomByBrideName() {
//        Person groom = personRepository.findGroomByBrideName(BRIDE_FIRST_NAME, BRIDE_LAST_NAME);
//
//        assertThat(groom, notNullValue());
//        assertThat(groom.getFirstName(), equalTo(GROOM_FIRST_NAME));
//        assertThat(groom.getLastName(), equalTo(GROOM_LAST_NAME));
//        assertThat(groom.getSex(), equalTo(MALE));
//
//        assertThat(groom.getWeddings(), notNullValue());
//        assertThat(groom.getWeddings().size(), equalTo(1));
//
//        Wedding wedding = groom.getWeddings().get(0);
//        assertThat(wedding.getDate(), equalTo(WEEDING_DATE));
//        assertThat(wedding.getDocumentRecord(), equalTo(WEEDING_DOCUMENT_RECORD));
//    }
}