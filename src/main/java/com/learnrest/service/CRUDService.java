package com.learnrest.service;

import com.learnrest.dao.DAO;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernando
 */
public interface CRUDService<T> {
    
    Response getById(Long id);
    
    Response getAll();
    
    DAO getDao();
}
