package org.jidentity;

import java.io.Serializable;

/**
 * Minimal interface for a user with id and username
 */
public interface IUser<ID extends Serializable> {

    /**
     * Gets the unique key for the user.
     *
     * @return the key for the user
     */
    ID getId();

    /**
     * Gets the unique username.
     *
     * @return the username
     */
    String getUserName();

    /**
     * Sets the username.
     *
     * @param userName the new username
     */
    void setUserName(String userName);
}
