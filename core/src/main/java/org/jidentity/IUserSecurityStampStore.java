package org.jidentity;

import java.util.concurrent.Future;

/**
 * Stores a user's security stamp
 */
public interface IUserSecurityStampStore extends IUserStore {

    /**
     * Set the security stamp for the user
     *
     * @param user
     * @param stamp
     * @return
     */
    Future<Void> setSecurityStamp(IUser user, String stamp);

    /**
     * Get the user security stamp
     *
     * @param user
     * @return
     */
    Future<String> getSecurityStamp(IUser user);
}
