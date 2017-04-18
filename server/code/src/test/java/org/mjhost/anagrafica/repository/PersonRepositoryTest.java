package org.mjhost.anagrafica.repository;

import org.junit.Test;
import org.mjhost.anagrafica.model.node.Person;
import org.mjhost.anagrafica.utils.test.MassimoManfredinoBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class PersonRepositoryTest extends AbstractRepositoryTest {

    protected final static String NAME_QUERY = "nne ru";

    @Test
    public void testFindByFirstName() {
        Person mm = MassimoManfredinoBuilder.build();
        List<Person> people = personRepository.findByFirstName(mm.getFirstName());

        assertThat(org.apache.commons.collections4.CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));

        Person bride = people.get(0);
        assertThat(bride, notNullValue());
        assertThat(bride.getLastName(), equalTo(mm.getLastName()));
        assertThat(bride.getSex(), equalTo(mm.getSex()));
    }

    @Test
    public void findByFullName() {
        Person mm = MassimoManfredinoBuilder.build();
        List<Person> people = personRepository.findByFullName(mm.getFirstName(), mm.getLastName());
        assertThat(CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));
        Person p = people.get(0);
        assertThat(p, notNullValue());
        assertThat(p.getSex(), equalTo(mm.getSex()));
    }

    @Test
    public void testFindByName() {
        List<Person> people = personRepository.findByName(NAME_QUERY);

        assertThat(CollectionUtils.isEmpty(people), is(false));
        assertThat(people.size(), is(1));

        Person p = people.get(0);
        assertThat(p, notNullValue());

        Person mm = MassimoManfredinoBuilder.build();
        assertThat(p.getFirstName(), equalTo(mm.getFirstName()));
        assertThat(p.getLastName(), equalTo(mm.getLastName()));
        assertThat(p.getSex(), equalTo(mm.getSex()));
    }

    @Override
    protected void initDatabase() {
//        populate Neo4J

//        nodes
        Person mm = MassimoManfredinoBuilder.build();
        getPersonRepository().save(mm);
    }
}
