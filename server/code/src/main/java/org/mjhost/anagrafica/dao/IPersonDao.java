package org.mjhost.anagrafica.dao;

import org.mjhost.anagrafica.exception.PersonException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface IPersonDao {

    List<Map<String, Object>> findPeople(@NotNull String queryName, @NotNull String query) throws PersonException;
}
