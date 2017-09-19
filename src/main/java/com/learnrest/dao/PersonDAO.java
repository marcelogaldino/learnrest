package com.learnrest.dao;

import com.learnrest.model.Person;
import java.util.List;

/**
 *
 * @author fernando
 */
public interface PersonDAO extends DAO<Person, Long> {

    List<Person> findByName(String name);
    
}
