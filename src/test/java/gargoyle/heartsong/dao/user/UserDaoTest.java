package gargoyle.heartsong.dao.user;

import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.test.HSTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserDaoTest extends HSTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Before
    public void setUp() {
        userDao.save(userDao.create("user", "password", "USER"));
    }

    @Test
    public void findAll() {
        List<UserDetails> all = userDao.findAll();
        assertFalse("no data", null == all || all.isEmpty());
    }

    @Test
    public void countAll() {
        assertNotEquals("countAll", 0, userDao.countAll());
    }

    @Test
    public void findByUsername() {
        UserDetails user = userDao.findAll().get(0);
        String username = user.getUsername();
        assertEquals("findById", userDao.findById(username).orElse(null), user);
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByUid() {
    }

    @Test
    public void existsByUsername() {
        UserDetails band = userDao.findAll().get(0);
        String username = band.getUsername();
        assertTrue("existsById", userDao.existsById(username));
    }

    @Test
    public void existsById() {
    }

    @Test
    public void changePassword() {
        String password1 = "password1";
        String password2 = "password2";
        String username = "userChange";
        UserDetails user = userDao.create(username, password1, "USER");
        assertTrue("password", passwordEncoder.matches(password1, user.getPassword()));
        userDao.save(user);
        userDao.changePassword(username, password2);
        user = userDao.findByUsername(username).orElse(null);
        assertTrue("password", passwordEncoder.matches(password2, user.getPassword()));
    }

    @Test
    public void save() {
        UserDetails user = userDao.create("user title", "user description", "USER");
        userDao.save(user);
        UserDetails saved = userDao.findByUid(user.getUsername()).orElse(null);
        assertEquals("equality", saved, user);
        assertNotNull("null", saved);
    }

    @Test
    public void search() {
        List<SearchResult<UserDetails>> resultList = userDao.search("user", (user -> ""));
        for (SearchResult<UserDetails> userSearchResult : resultList) {
            assertSame("search", UserDetails.class, userSearchResult.getType());
        }
    }

    @Test
    public void create() {
        String username = "user";
        String password = "password";
        UserDetails user = userDao.create(username, password, "USER");
        assertEquals("username", username, user.getUsername());
        assertTrue("password", passwordEncoder.matches(password, user.getPassword()));
        assertEquals("roles", "[ROLE_USER]", user.getAuthorities().toString());

    }
}
