package com.learnrest.dao.impl;

import com.learnrest.dao.GenericDAO;
import com.learnrest.dao.PersonDAO;
import com.learnrest.model.Person;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fernando
 */
@ApplicationScoped
public class PersonDAOImpl extends GenericDAO<Person, Long> implements PersonDAO {

    private static final Logger LOG = LoggerFactory.getLogger(PersonDAOImpl.class);

    public PersonDAOImpl() {
        super(Person.class);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public List<Person> findByName(String name) {
        Query query = getEntityManager().createNamedQuery("Person.findByName", Person.class);
        query.setParameter("name", name);

        List<Person> persons = query.getResultList();
        if (persons == null || persons.isEmpty()) {
            return null;
        } else {
            return persons;
        }
    }

}
