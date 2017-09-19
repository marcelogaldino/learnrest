package com.learnrest.dao;

import com.learnrest.dao.impl.PersonDAOImpl;
import com.learnrest.dao.impl.UserDAOImpl;
import com.learnrest.model.Person;
import com.learnrest.model.User;
import com.learnrest.util.DateUtils;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author fernando
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({PersonDAOImpl.class, UserDAOImpl.class})
public class PersonTest extends AbstractDAOTest {

    @Inject
    private PersonDAO personDAO;

    @Inject
    private UserDAO userDAO;

    @Test
    @Override
    public void cdiInjectionTest() {
        Assert.assertNotNull(personDAO);
        Assert.assertNotNull(userDAO);
    }

    @Test
    @Override
    public void saveTest() {
        Person fernando = new Person("Fernando Paschualetto", DateUtils.newDate("30/07/1988"), new User("fernandopaschualetto", "paschua"));
        personDAO.save(fernando);

        fernando = personDAO.findByName(fernando.getName()).get(0);
        Assert.assertNotNull(fernando);
        Assert.assertNotNull(fernando.getId());

        User fernandoUser = userDAO.findByUsername(fernando.getUser().getUsername());
        Assert.assertNotNull(fernandoUser);
        Assert.assertNotNull(fernandoUser.getId());
    }

    @Test
    @Override
    public void updateTest() {
        Person toUpdate = new Person("To Update", new Date(), new User("toUpdatePerson", "toUpdatePerson"));
        personDAO.save(toUpdate);

        Person toChange = new Person("To Change", DateUtils.newDate("01/01/2017"), new User("toChangePerson", "toChangePerson"));

        toUpdate = personDAO.findByName(toUpdate.getName()).get(0);
        toUpdate.updateParameters(toChange);
        personDAO.update(toUpdate);

        toUpdate = personDAO.findByName(toUpdate.getName()).get(0);
        Assert.assertNotNull(toUpdate);
        Assert.assertNotNull(toUpdate.getId());
        Assert.assertEquals(toUpdate, toChange);
    }

    @Test
    @Override
    public void mergeTest() {
        Person toMerge = new Person("To Merge", new Date(), new User("toMergePerson", "toMergePerson"));
        toMerge = personDAO.merge(toMerge);
        Assert.assertNotNull(toMerge);
        Assert.assertNotNull(toMerge.getId());
        Assert.assertNotNull(toMerge.getUser());
    }

    @Test
    @Override
    public void deleteTest() {
        Person toDelete = new Person("To Delete", new Date(), new User("toDeletePerson", "toDeletePerson"));
        toDelete = personDAO.merge(toDelete);
        Assert.assertNotNull(toDelete.getId());

        personDAO.delete(toDelete);

        toDelete = personDAO.findById(toDelete.getId());
        Assert.assertNull(toDelete);
    }

    @Test
    @Override
    public void findByIdTest() {
        Person toFindbyId = new Person("To Find", new Date(), new User("ToFindPerson", "ToFindPerson"));
        toFindbyId = personDAO.merge(toFindbyId);
        Assert.assertNotNull(toFindbyId.getId());

        Person recover = personDAO.findById(toFindbyId.getId());
        Assert.assertNotNull(recover);
    }

    @Test
    @Override
    public void findAllTest() {
        Person lessOne = new Person("Less One", new Date(), new User("lessOnePerson", "lessOnePerson"));
        personDAO.save(lessOne);
        
        List<Person> persons = personDAO.findAll();
        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
    }

}
