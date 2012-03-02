package org.rhok.service;

import org.rhok.domain.DomainObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class DomainObjectServiceImpl implements DomainObjectService
{
    @PersistenceContext
    protected EntityManager entityManager;

    public <T extends DomainObject> T get(Class<T> persistentClass, Long id)
    {
        return entityManager.find(persistentClass, id);
    }

    @SuppressWarnings({"unchecked"})
    public <T> T getByQuery(String queryString)
    {
        return (T)getByQuery(queryString, null);
    }

    @SuppressWarnings({"unchecked"})
    public <T> T getByQuery(String queryString, Map<String, ?> params)
    {
        return (T)get(entityManager.createQuery(queryString), params);
    }

    @SuppressWarnings({"unchecked"})
    public <T> T getByNativeQuery(Class persistentClass, String queryString)
    {
        return (T)getByNativeQuery(persistentClass, queryString, null);
    }

    @SuppressWarnings({"unchecked"})
    public <T> T getByNativeQuery(Class persistentClass, String queryString, Map<String, ?> params)
    {
        return (T)get(entityManager.createNativeQuery(queryString, persistentClass), params);
    }

    public <T> List<T> getAll(Class<T> persistentClass)
    {
        return getAll(entityManager.createQuery("from " + persistentClass.getSimpleName()), null, null, null);
    }

    public <T> List<T> getAllByQuery(String queryString)
    {
        return getAllByQuery(queryString, null);
    }

    public <T> List<T> getAllByQuery(String queryString, Map<String, ?> params)
    {
        return getAllByQuery(queryString, params, null);
    }

    public <T> List<T> getAllByQuery(String queryString, Map<String, ?> params, Integer maxResults)
    {
        return getAllByQuery(queryString, params, maxResults, null);
    }

    public <T> List<T> getAllByQuery(String queryString, Map<String, ?> params, Integer maxResults, Integer firstResult)
    {
        return getAll(entityManager.createQuery(queryString), params, maxResults, firstResult);
    }

    public <T> List<T> getAllByNamedQuery(String name)
    {
        return getAllByNamedQuery(name, null);
    }

    public <T> List<T> getAllByNamedQuery(String name, Map<String, ?> params)
    {
        return getAllByNamedQuery(name, params, null);
    }

    public <T> List<T> getAllByNamedQuery(String name, Map<String, ?> params, Integer maxResults)
    {
        return getAllByNamedQuery(name, params, maxResults, null);
    }

    public <T> List<T> getAllByNamedQuery(String name, Map<String, ?> params, Integer maxResults, Integer firstResult)
    {
        return getAll(entityManager.createNamedQuery(name), params, maxResults, firstResult);
    }

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString)
    {
        return getAllByNativeQuery(persistentClass, queryString, null);
    }

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString, Map<String, ?> params)
    {
        return getAllByNativeQuery(persistentClass, queryString, params, null);
    }

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString, Map<String, ?> params, Integer maxResults)
    {
        return getAllByNativeQuery(persistentClass, queryString, params, maxResults, null);
    }

    public <T> List<T> getAllByNativeQuery(Class<T> persistentClass, String queryString, Map<String, ?> params, Integer maxResults, Integer firstResult)
    {
        return getAll(this.entityManager.createNativeQuery(queryString, persistentClass), params, maxResults, firstResult);
    }

    public Long getCount(Class persistentClass)
    {
        return (Long)entityManager.createQuery("select count(u) from " + persistentClass.getSimpleName() + " u").getSingleResult();
    }

    public Long getCountByQuery(String queryString)
    {
        return getCountByQuery(queryString, null);
    }

    public Long getCountByQuery(String queryString, Map<String, ?> params)
    {
        Query query = entityManager.createQuery(queryString);
        replaceParameters(query, params);
        return (Long)query.getSingleResult();
    }

    public Long getCountByNativeQuery(String queryString)
    {
        return getCountByNativeQuery(queryString, null);
    }

    public Long getCountByNativeQuery(String queryString, Map<String, ?> params)
    {
        Query query = entityManager.createNativeQuery(queryString);
        replaceParameters(query, params);
        return (Long)query.getSingleResult();
    }

    @Transactional(readOnly = false)
    public <T extends DomainObject> void save(T domainObject)
    {
        if(domainObject.getId() == null)
        {
            this.entityManager.persist(domainObject);
        }
        else
        {
            // work around crappy hibernate bug
            if(!this.entityManager.contains(domainObject))
            {
                this.entityManager.merge(domainObject);
            }
        }
    }

    @Transactional(readOnly = false)
    public <T extends DomainObject> void saveAll(T... domainObjects)
    {
        for(T domainObject : domainObjects)
        {
            save(domainObject);
        }
    }

    @Transactional(readOnly = false)
    public <T extends DomainObject> void saveAll(List<T> domainObjects)
    {
        for(T domainObject : domainObjects)
        {
            save(domainObject);
        }
    }

    @Transactional(readOnly = false)
    public <T extends DomainObject> void remove(Class<T> persistentClass, Long id)
    {
        this.entityManager.remove(get(persistentClass, id));
    }

    @SuppressWarnings({"unchecked"})
    private <T> T get(Query query, Map<String, ?> params)
    {
        replaceParameters(query, params);
        T result;
        try
        {
            result = (T)query.getSingleResult();
        }
        catch(NoResultException e)
        {
            result = null;
        }
        return result;
    }

    @SuppressWarnings({"unchecked"})
    private <T> List<T> getAll(Query query, Map<String, ?> params, Integer maxResults, Integer firstResult)
    {
        replaceParameters(query, params);
        if(maxResults != null)
        {
            query.setMaxResults(maxResults);
        }
        if(firstResult != null)
        {
            query.setFirstResult(firstResult);
        }
        return query.getResultList();
    }

    private void replaceParameters(Query query, Map<String, ?> params)
    {
        if(params != null)
        {
            for(String key : params.keySet())
            {
                query.setParameter(key, params.get(key));
            }
        }
    }
}
