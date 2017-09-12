package com.learnrest.service;

import com.learnrest.dao.DAO;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;

/**
 *
 * @author fernando
 * @param <T>
 */
public interface CRUDRestService<T> {
    
    Response getById(Long id);
    
    Response getAll();
    
    Response save(T entity);
    
    Response update(T entity, Long id);
    
    Response delete(Long id);
    
    DAO getDao();
    
    Logger getLogger();
}
