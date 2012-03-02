package org.rhok.service;

import org.rhok.domain.DomainObject;
import java.util.List;
import java.util.Map;

public interface DomainObjectService
{
    public <T extends DomainObject> T get(Class<T> persistentClass, Long id);

    public <T> T getByQuery(String queryString);

    public <T> T getByQuery(String queryString, Map<String, ?> params);

    public <T> T getByNativeQuery(Class persistentClass, String queryString);

    public <T> T getByNativeQuery(Class persistentClass, String queryString, Map<String, ?> params);

    public <T> List<T> getAll(Class<T> persistentClass);

    public <T> List<T> getAllByQuery(String queryString);

    public <T> List<T> getAllByQuery(String queryString, Map<String, ?> params);

    public <T> List<T> getAllByQuery(String queryString, Map<String, ?> params, Integer maxResults);

    public <T> List<T> getAllByQuery(String queryString, Map<String, ?> params, Integer maxResults, Integer firstResult);

    public <T> List<T> getAllByNamedQuery(String name);

    public <T> List<T> getAllByNamedQuery(String name, Map<String, ?> params);

    public <T> List<T> getAllByNamedQuery(String name, Map<String, ?> params, Integer maxResults);

    public <T> List<T> getAllByNamedQuery(String name, Map<String, ?> params, Integer maxResults, Integer firstResult);

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString);

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString, Map<String, ?> params);

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString, Map<String, ?> params, Integer maxResults);

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString, Map<String, ?> params, Integer maxResults, Integer firstResult);

    public Long getCount(Class persistentClass);

    public Long getCountByQuery(String queryString);

    public Long getCountByQuery(String queryString, Map<String, ?> params);

    public Long getCountByNativeQuery(String queryString);

    public Long getCountByNativeQuery(String queryString, Map<String, ?> params);

    public <T extends DomainObject> void save(T domainObject);

    public <T extends DomainObject> void saveAll(List<T> domainObjects);

    public <T extends DomainObject> void saveAll(T... domainObjects);

    public <T extends DomainObject> void remove(Class<T> persistentClass, Long id);
}
