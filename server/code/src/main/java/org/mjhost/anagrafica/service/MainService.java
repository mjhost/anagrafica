package org.mjhost.anagrafica.service;

import org.mjhost.anagrafica.dao.IPersonDao;
import org.mjhost.anagrafica.dao.PersonDao;
import org.mjhost.anagrafica.exception.PersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@PropertySources({
    @PropertySource("classpath:exception_message.properties")
})
public class MainService implements IPersonDao {

    @Autowired
    private IPersonDao personDao;

//    people management

    @Override
    public List<Map<String, Object>> findPeople(String queryName, String query) throws PersonException {
        return personDao.findPeople(queryName, query);
    }
}