package org.jidentity;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Interface that maps users to login providers, i.e. Google, Facebook, Twitter, Microsoft
 */
public interface IUserLoginStore extends IUserStore {

    /**
     * Adds a user login with the specified provider and key
     *
     * @param user
     * @param login
     * @return
     */
    Future<Void> addLogin(IUser user, UserLoginInfo login);

    /**
     * Removes the user login with the specified combination if it exists
     *
     * @param user
     * @param login
     * @return
     */
    Future<Void> removeLogin(IUser user, UserLoginInfo login);

    /**
     * Returns the linked accounts for this user
     *
     * @param user
     * @return
     */
    Future<List<UserLoginInfo>> getLogins(IUser user);

    /**
     * Returns the user associated with this login
     *
     * @param login
     * @return
     */
    Future<IUser> find(UserLoginInfo login);

}
