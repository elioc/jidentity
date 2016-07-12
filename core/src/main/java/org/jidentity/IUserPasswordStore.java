package org.jidentity;

import java.util.concurrent.Future;

/**
 * Stores a user's password hash
 */
public interface IUserPasswordStore extends IUserStore {

    /**
     * Set the user password hash
     *
     * @param user
     * @param passwordHash
     * @return
     */
    Future<Void> setPasswordHash(IUser user, String passwordHash);

    /**
     * Get the user password hash
     *
     * @param user
     * @return
     */
    Future<String> getPasswordHash(IUser user);

    /**
     * Returns true if a user has a password set
     *
     * @param user
     * @return
     */
    Future<Boolean> hasPassword(IUser user);
}
