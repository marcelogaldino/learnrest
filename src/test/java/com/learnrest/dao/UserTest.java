package com.learnrest.dao;

import com.learnrest.dao.impl.UserDAOImpl;
import com.learnrest.model.User;
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
@AdditionalClasses(UserDAOImpl.class)
public class UserTest extends AbstractDAOTest {

    @Inject
    private UserDAO userDAO;

    @Test
    @Override
    public void cdiInjectionTest() {
        Assert.assertNotNull(userDAO);
    }

    @Test
    @Override
    public void saveTest() {
        User toSave = new User("tosave", "tosave");
        userDAO.save(toSave);

        User saved = userDAO.findByUsername(toSave.getUsername());
        Assert.assertNotNull(saved);
        Assert.assertNotNull(saved.getId());
    }

    @Test
    @Override
    public void updateTest() {
        User user = new User("toUpdate", "toUpdate");
        userDAO.save(user);

        User userSaved = userDAO.findByUsername(user.getUsername());
        Assert.assertNotNull(userSaved);
        Assert.assertNotNull(userSaved.getId());

        User change = new User("toChange", "toChange");

        userSaved.updateParameters(change);
        userDAO.update(userSaved);

        userSaved = userDAO.findByUsername(change.getUsername());
        Assert.assertNotNull(userSaved);
        Assert.assertNotNull(userSaved.getId());
        Assert.assertEquals(userSaved, change);
    }

    @Test
    @Override
    public void mergeTest() {
        User fernando = userDAO.merge(new User("fernando", "fernando"));
        Assert.assertNotNull(fernando);
        Assert.assertNotNull(fernando.getId());
    }

    @Test
    @Override
    public void deleteTest() {
        User toDelete = new User("delete", "todelete");
        toDelete = userDAO.merge(toDelete);
        Assert.assertNotNull(toDelete.getId());

        userDAO.delete(toDelete);

        toDelete = userDAO.findByUsername(toDelete.getUsername());
        Assert.assertNull(toDelete);
    }

    @Test
    @Override
    public void findByIdTest() {
        User alice = userDAO.merge(new User("alice", "alice"));
        alice = userDAO.findById(alice.getId());
        Assert.assertNotNull(alice);
    }

    @Test
    @Override
    public void findAllTest() {
        User lessOne = new User("lessone", "lessone");
        userDAO.save(lessOne);

        List<User> users = userDAO.findAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() >= 1);
    }
}
