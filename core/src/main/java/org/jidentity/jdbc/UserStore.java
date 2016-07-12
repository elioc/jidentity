package org.jidentity.jdbc;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;
import org.jidentity.IUser;
import org.jidentity.IUserEmailStore;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * JDBC based user store implementation that supports IUserStore, IUserLoginStore and IUserRoleStore
 */
public class UserStore<TUser extends User<ID>, ID extends Serializable> implements IUserEmailStore<TUser, ID> {

	private boolean closed;
	private final DataSource dataSource;
	private final ExecutorService executorService = Executors.newFixedThreadPool(10);
	private final Class<TUser> userClass;

	public UserStore(DataSource dataSource, Class<TUser> userClass) {
		this.closed = false;
		this.dataSource = dataSource;
		this.userClass = userClass;
	}

	public Future<Void> create(final TUser user) throws Exception {
		throwIfClosed();
//		Validate.notNull(user);
//		Validate.notEmpty(user.getUserName());

		return executorService.submit(new Callable<Void>() {
			public Void call() throws Exception {
				QueryRunner qr = new QueryRunner(dataSource);
				ID id = qr.insert("INSERT INTO users (username, email, email_confirmed, password_hash, security_stamp, phone_number, phone_number_confirmed, lockout_end_date_utc, lockout_enabled, access_failed_count) VALUES (?,?,?,?,?,?,?,?,?,?)",
						new ScalarHandler<ID>(),
						user.getUserName(),
						user.getEmail(),
						user.isEmailConfirmed(),
						user.getPasswordHash(),
						user.getSecurityStamp(),
						user.getPhoneNumber(),
						user.isPhoneNumberConfirmed(),
						user.getLockoutEndDateUtc(),
						user.isLockoutEnabled(),
						user.getAccessFailedCount());

				user.setId(id);

				return null;
			}
		});
	}

	public Future<Void> update(final TUser user) throws Exception {
		throwIfClosed();

		return executorService.submit(new Callable<Void>() {
			public Void call() throws Exception {
				QueryRunner qr = new QueryRunner(dataSource);
				qr.update("UPDATE users SET username=?, email=?, email_confirmed=?, password_hash=?, security_stamp=?, phone_number=?, phone_number_confirmed=?, lockout_end_date_utc=?, lockout_enabled=?, access_failed_count=? WHERE user_id=?",
						user.getUserName(),
						user.getEmail(),
						user.isEmailConfirmed(),
						user.getPasswordHash(),
						user.getSecurityStamp(),
						user.getPhoneNumber(),
						user.isPhoneNumberConfirmed(),
						user.getLockoutEndDateUtc(),
						user.isLockoutEnabled(),
						user.getAccessFailedCount(),
						user.getId());

				return null;
			}
		});
	}

	public Future<Void> delete(TUser user) throws Exception {
		throwIfClosed();
		final ID userId = user.getId();
		return executorService.submit(new Callable<Void>() {
			public Void call() throws Exception {
				QueryRunner qr = new QueryRunner(dataSource);
				qr.update("DELETE FROM users WHERE user_id=?", userId);
				return null;
			}
		});
	}

	public Future<TUser> findById(ID userId) throws Exception {
		throwIfClosed();
		AsyncQueryRunner run = new AsyncQueryRunner(executorService, new QueryRunner(dataSource));
		return run.query("SELECT * FROM users WHERE user_id=?", new BeanHandler<TUser>(userClass), userId);
	}

	public Future<TUser> findByName(String userName) throws Exception {
		throwIfClosed();
		AsyncQueryRunner run = new AsyncQueryRunner(executorService, new QueryRunner(dataSource));
		return run.query("SELECT * FROM users WHERE username=?", new BeanHandler<TUser>(userClass), userName);
	}

	public boolean isClosed() {
		return closed;
	}

	public Future<Void> setEmail(IUser user, String email) {
		throw new NotImplementedException("This has not been implemented");
	}

	public Future<String> getEmail(IUser user) {
		throw new NotImplementedException("This has not been implemented");
	}

	public Future<Boolean> getEmailConfirmed(IUser user) {
		throw new NotImplementedException("This has not been implemented");
	}

	public Future<Void> setEmailConfirmed(IUser user, boolean confirmed) {
		throw new NotImplementedException("This has not been implemented");
	}

	public Future<IUser> findByEmail(String email) {
		throw new NotImplementedException("This has not been implemented");
	}

	public void close() throws Exception {
		if (!isClosed()) {
			closed = true;
		}
	}

	private void throwIfClosed() throws Exception {
		if (isClosed())
			throw new Exception("user store is already closed.");
	}
}
