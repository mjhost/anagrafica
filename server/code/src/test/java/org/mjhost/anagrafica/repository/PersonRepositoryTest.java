package org.mjhost.anagrafica.repository;

import org.junit.Test;
import org.mjhost.anagrafica.model.node.Location;
import org.mjhost.anagrafica.model.node.Person;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class PersonRepositoryTest extends AbstractRepositoryTest {

    protected final static String FULL_NAME_QUERY = "nne ru";

    @Test
    public void testFindByFullName() {
        List<Person> people = personRepository.findByFullName(FULL_NAME_QUERY);

        assertThat(CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));

        Person p = people.get(0);
        assertThat(p, notNullValue());

        Person lady = getLady();
        assertThat(p.getFirstName(), equalTo(lady.getFirstName()));
        assertThat(p.getLastName(), equalTo(lady.getLastName()));
        assertThat(p.getSex(), equalTo(lady.getSex()));
    }

    @Test
    public void testFindByFirstName() {
        Person lady = getLady();
        List<Person> people = personRepository.findByFirstName(lady.getFirstName());

        assertThat(org.apache.commons.collections4.CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));

        Person bride = people.get(0);
        assertThat(bride, notNullValue());
        assertThat(bride.getLastName(), equalTo(lady.getLastName()));
        assertThat(bride.getSex(), equalTo(lady.getSex()));
    }

    @Test
    public void testFindByFirstAndLastName() {
        Person lady = getLady();
        List<Person> people = personRepository.findByName(lady.getFirstName(), lady.getLastName());
        assertThat(CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));
        Person p = people.get(0);
        assertThat(p, notNullValue());
        assertThat(p.getSex(), equalTo(lady.getSex()));
    }

    @Override
    protected void initDatabase() {
//        populate Neo4J

//        nodes
        Location home = getHomeLocation();
        getLocationRepository().save(home);

        Person lady = getLady();
        getPersonRepository().save(lady);
    }
}
