package com.learnrest.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author fernando
 * @param <T> EntityClass to manage
 * @param <K> Datatype from ID
 */
public interface DAO<T, K> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    T merge(T entity);

    T findById(K id);

    List<T> findAll();

    EntityManager getEntityManager();

    Class<T> getEntityClass();
}
