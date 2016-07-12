package org.jidentity.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jidentity.IUserStore;
import org.junit.*;

import javax.sql.DataSource;

import static org.junit.Assert.*;

/**
 * Created by elio on 6/25/2016.
 */
public class UserStoreTest {
	private static DataSource dataSource;
	private IUserStore userStore;

	@BeforeClass
	public static void setUpClass() throws Exception {
		BasicDataSource ds = new BasicDataSource();

		ds.setDriverClassName("org.hsqldb.jdbcDriver");
//		ds.setUsername("jidentity_dba");
//		ds.setPassword("YloG4UUFzA");
		ds.setUrl("jdbc:hsqldb:file:F:\\workspace\\projects\\JIdentity\\core\\src\\main\\resources/jidentity");
		ds.setMaxIdle(5);
		ds.setInitialSize(5);

		dataSource = ds;
	}

	@Before
	public void setUp() throws Exception {
		this.userStore = new UserStore(dataSource, User.class);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void create() throws Exception {
		User newUser = new User();
		newUser.setUserName("NonspecificOd@maildrop.cc");

		userStore.create(newUser).get();
		assertNotNull(newUser.getId());
	}

	@Test
	public void update() throws Exception {
		User user = new User();
		user.setId(1);
		user.setUserName("disfranchised@maildrop.cc");
		user.setEmailConfirmed(true);
		user.setPhoneNumberConfirmed(true);
		user.setAccessFailedCount(1);
		userStore.update(user).get();
		assertNotNull(user.getId());
	}

	@Test
	public void delete() throws Exception {
		User user = new User();
		user.setId(1);
		userStore.delete(user).get();
	}

	@Test
	public void findById() throws Exception {
		assertNotNull(userStore.findById(1).get());
	}

	@Test
	public void findByName() throws Exception {
		assertNotNull(userStore.findByName("NonspecificOd@maildrop.cc").get());
	}
}