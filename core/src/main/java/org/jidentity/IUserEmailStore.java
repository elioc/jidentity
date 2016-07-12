package org.jidentity;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * Stores a user's email
 */
public interface IUserEmailStore<TUser extends IUser<ID>, ID extends Serializable> extends IUserStore<TUser, ID> {

    /**
     * Set the user email
     *
     * @param user
     * @param email
     * @return
     */
    Future<Void> setEmail(IUser user, String email);

    /**
     * Get the user email
     *
     * @param user
     * @return
     */
    Future<String> getEmail(IUser user);

    /**
     * Returns true if the user email is confirmed
     *
     * @param user
     * @return
     */
    Future<Boolean> getEmailConfirmed(IUser user);

    /**
     * Sets whether the user email is confirmed
     *
     * @param user
     * @param confirmed
     * @return
     */
    Future<Void> setEmailConfirmed(IUser user, boolean confirmed);

    /**
     * Returns the user associated with this email
     *
     * @param email
     * @return
     */
    Future<IUser> findByEmail(String email);
}
