package org.jidentity;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * Interface that exposes basic user management apis
 */
public interface IUserStore<TUser extends IUser<ID>, ID extends Serializable> extends AutoCloseable {

    /**
     * Insert a new user
     *
     * @param user
     * @return
     */
    Future<Void> create(TUser user) throws Exception;

    /**
     * Update a user
     *
     * @param user
     * @return
     */
    Future<Void> update(TUser user) throws Exception;

    /**
     * Delete a user
     *
     * @param user
     * @return
     */
    Future<Void> delete(TUser user) throws Exception;

    /**
     * Finds a user
     *
     * @param userId
     * @return
     */
    Future<TUser> findById(ID userId) throws Exception;

    /**
     * Find a user by name
     *
     * @param userName
     * @return
     */
    Future<TUser> findByName(String userName) throws Exception;

    /**
     *
     * @return
     */
    boolean isClosed();
}
