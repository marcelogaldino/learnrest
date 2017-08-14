package com.learnrest.dao;

import com.learnrest.dao.impl.UserDAOImpl;
import com.learnrest.model.User;
import java.util.List;
import javax.inject.Inject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
    public void cdiInjectionTest() {
        Assert.assertNotNull(userDAO);
    }

    @Test
    @Override
    public void saveTest() {
        User root = new User("root", "senha10");
        userDAO.save(root);

        root = userDAO.findByUsername(root.getUsername());
        Assert.assertNotNull(root);
        Assert.assertNotNull(root.getId());
    }

    @Test
    @Override
    public void updateTest() {
        User admin = new User("admin", "senha10");
        userDAO.update(admin);

        admin = userDAO.findByUsername(admin.getUsername());
        Assert.assertNotNull(admin);
        Assert.assertNotNull(admin.getId());
    }

    @Test
    @Override
    public void mergeTest() {
        User fernando = new User("fernando", "senha10");
        fernando = userDAO.merge(fernando);
        Assert.assertNotNull(fernando);
        Assert.assertNotNull(fernando.getId());
    }

    @Test
    @Override
    public void deleteTest() {
        User alice = new User("alice", "papailindo");
        alice = userDAO.merge(alice);
        Assert.assertNotNull(alice.getId());

        userDAO.delete(alice);

        alice = userDAO.findByUsername(alice.getUsername());
        Assert.assertNull(alice);
    }

    @Test
    @Override
    public void findByIdTest() {
        User alice = new User("alice", "papailindo");
        alice = userDAO.merge(alice);
        alice = userDAO.findById(alice.getId());
        Assert.assertNotNull(alice);
    }

    @Test
    @Override
    public void findAllTest() {
        User alice = new User("alice", "papailindo");
        userDAO.save(alice);

        List<User> users = userDAO.findAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() >= 1);
    }

    @Before
    public void setUp() {
        userDAO.deleteAll();
    }

    @After
    public void tearDown() {
        userDAO.deleteAll();
        User root = new User("root", "senha10", User.UserType.ROOT);
        userDAO.save(root);
    }
}
