package org.mjhost.anagrafica;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.persistence.model.Person;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLSchema.newSchema;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
//        (
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
@Import(Neo4jDataAutoConfiguration.class)
@Transactional
public class WeddingGraphQLTest extends WeddingSpringNeo4JTest {

    private static final String BRIDE_FIRST_NAME_ARGUMENT = "bfn";

    private static final String BRIDE_OUTPUT_KEY_NAME = "bride";

    private GraphQLObjectType personType = Person.type();

    @Test
    public void testFindBrideByFirstName() {
        GraphQLObjectType queryType = createFindBrideByFirstNameQueryType();

//        create schema
        GraphQLSchema schema = newSchema().query(queryType).build();

//        create graph
        GraphQL graphQL = new GraphQL(schema);

//        execute query
        try {
            String graphQLQuery;
            ExecutionResult result;
            Object data;
            Map<String, String> map;

//            first ask both fistName and lastName
//            TODO: any AP for building the query???
             graphQLQuery = String.format(
                "{%s(%s:\"%s\"){firstName, lastName}}", BRIDE_OUTPUT_KEY_NAME, BRIDE_FIRST_NAME_ARGUMENT, BRIDE_FIRST_NAME
            );
            result = graphQL.execute(graphQLQuery);

            assertThat(CollectionUtils.isEmpty(result.getErrors()), is(true));
            assertThat(result.getData(), notNullValue());

            data = result.getData();
            assertThat(data instanceof Map, is(true));

            map = (Map<String, String>) data;
            assertThat(map.get(BRIDE_OUTPUT_KEY_NAME), notNullValue());

            data = map.get(BRIDE_OUTPUT_KEY_NAME);
            assertThat(data instanceof Map, is(true));

            map = (Map<String, String>) data;
            assertThat(map.get("firstName"), notNullValue());
            assertThat(map.get("firstName"), equalTo(BRIDE_FIRST_NAME));
            assertThat(map.get("lastName"), notNullValue());
            assertThat(map.get("lastName"), equalTo(BRIDE_LAST_NAME));

//            then ask firstName only
            graphQLQuery = String.format(
                "{%s(%s:\"%s\"){firstName}}", BRIDE_OUTPUT_KEY_NAME, BRIDE_FIRST_NAME_ARGUMENT, BRIDE_FIRST_NAME
            );
            result = graphQL.execute(graphQLQuery);

            assertThat(CollectionUtils.isEmpty(result.getErrors()), is(true));
            assertThat(result.getData(), notNullValue());

            data = result.getData();
            assertThat(data instanceof Map, is(true));

            map = (Map<String, String>) data;
            assertThat(map.get(BRIDE_OUTPUT_KEY_NAME), notNullValue());

            data = map.get(BRIDE_OUTPUT_KEY_NAME);
            assertThat(data instanceof Map, is(true));

            map = (Map<String, String>) data;
            assertThat(map.get("firstName"), notNullValue());
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

    private GraphQLObjectType createFindBrideByFirstNameQueryType() {
        return newObject()
//            query name
            .name("findBrideByFirstName")
//            field is what the query has to return
            .field(f -> f
//                the name of the key in the output map
                .name(BRIDE_OUTPUT_KEY_NAME)
                .description("The output of the query will be an instance of person type")
//                query must return a single person type
                .type(personType)
//                query must expect an input param
                .argument(a -> a
                    .name(BRIDE_FIRST_NAME_ARGUMENT)
                    .description("Bride first_name as a non-null input parameter")
                    .type(new GraphQLNonNull(GraphQLString))
                )
//                data fetcher specifies the component in charge of retrieving the actual data to be returned
                .dataFetcher(environment -> {
//                    environment.getSource() is the value of the surrounding object. In this case described by objectType

                    Map<String, Object> arguments = environment.getArguments();
                    Object bfn = arguments.get(BRIDE_FIRST_NAME_ARGUMENT);
                    if (bfn != null && bfn instanceof String && !StringUtils.isEmpty((String) bfn)) {
                        return personRepository.findByFirstName((String) bfn);
                    } else {
                        return null;
                    }
                })
            )
            .build();
    }
}