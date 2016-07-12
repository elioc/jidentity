package org.jidentity.jdbc;

import org.jidentity.IUser;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Default JDBC IUser implementation
 */
public class User<ID extends Serializable> implements IUser<ID> {

	private ID id;
	private String userName;
	private String email;
	private boolean emailConfirmed;
	private String passwordHash;
	private String securityStamp;
	private String phoneNumber;
	private boolean phoneNumberConfirmed;
	//--twoFactorEnabled     BOOLEAN               NOT NULL,
	private Timestamp lockoutEndDateUtc;
	private boolean lockoutEnabled;
	private int accessFailedCount;

	public User() {
		emailConfirmed = false;
		phoneNumberConfirmed = false;
		lockoutEndDateUtc = null;
		lockoutEnabled = false;
		accessFailedCount = 0;
	}

	@Override
	public boolean equals(final Object that) {
		boolean isEqual = false;
		if (this == that) {
			isEqual = true;
		} else if (that != null && getClass() == that.getClass()) {
			final User user = (User) that;
			if (getId() == user.getId()) {
				isEqual = true;
			}
		}
		return isEqual;
	}

	public ID getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getSecurityStamp() {
		return securityStamp;
	}

	public void setSecurityStamp(String securityStamp) {
		this.securityStamp = securityStamp;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isPhoneNumberConfirmed() {
		return phoneNumberConfirmed;
	}

	public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
		this.phoneNumberConfirmed = phoneNumberConfirmed;
	}

	public Timestamp getLockoutEndDateUtc() {
		return lockoutEndDateUtc;
	}

	public void setLockoutEndDateUtc(Timestamp lockoutEndDateUtc) {
		this.lockoutEndDateUtc = lockoutEndDateUtc;
	}

	public boolean isLockoutEnabled() {
		return lockoutEnabled;
	}

	public void setLockoutEnabled(boolean lockoutEnabled) {
		this.lockoutEnabled = lockoutEnabled;
	}

	public int getAccessFailedCount() {
		return accessFailedCount;
	}

	public void setAccessFailedCount(int accessFailedCount) {
		this.accessFailedCount = accessFailedCount;
	}
}
