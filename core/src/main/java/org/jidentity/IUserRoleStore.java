package org.jidentity;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Interface that maps users to their roles
 */
public interface IUserRoleStore extends IUserStore {

    /**
     * Adds a user to a role
     *
     * @param user
     * @param roleName
     * @return
     */
    Future<Void> addToRole(IUser user, String roleName);

    /**
     * Removes the role for the user
     *
     * @param user
     * @param roleName
     * @return
     */
    Future<Void> removeFromRole(IUser user, String roleName);

    /**
     * Returns the roles for this user
     *
     * @param user
     * @return
     */
    Future<List<String>> getRoles(IUser user);

    /**
     * Returns true if a user is in the role
     *
     * @param user
     * @param roleName
     * @return
     */
    Future<Boolean> isInRole(IUser user, String roleName);
}
