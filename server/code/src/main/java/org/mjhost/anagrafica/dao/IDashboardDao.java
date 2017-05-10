package org.mjhost.anagrafica.dao;

import org.mjhost.anagrafica.exception.SystemException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface IDashboardDao {

    Map<String, Object> systemDashboard(String queryName, String query) throws SystemException;
}
