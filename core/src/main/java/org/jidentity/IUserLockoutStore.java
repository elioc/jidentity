package org.jidentity;

import java.sql.Timestamp;
import java.util.concurrent.Future;

/**
 * Stores information which can be used to implement account lockout, including access failures and lockout status
 */
public interface IUserLockoutStore extends IUserStore {

    /**
     * Returns the Timestamp that represents the end of a user's lockout, any time in the past should be considered not locked out.
     *
     * @param user
     * @return
     */
    Future<Timestamp> getLockoutEndDate(IUser user);

    /**
     * Locks a user out until the specified end date (set to a past date, to unlock a user)
     *
     * @param user
     * @param lockoutEnd
     * @return
     */
    Future<Void> setLockoutEndDate(IUser user, Timestamp lockoutEnd);

    /**
     * Used to record when an attempt to access the user has failed
     *
     * @param user
     * @return
     */
    Future<Integer> incrementAccessFailedCount(IUser user);

    /**
     * Used to reset the access failed count, typically after the account is successfully accessed
     *
     * @param user
     * @return
     */
    Future<Void> resetAccessFailedCount(IUser user);

    /**
     * Returns the current number of failed access attempts.  This number usually will be reset whenever the password is verified or the account is locked out.
     *
     * @param user
     * @return
     */
    Future<Integer> getAccessFailedCount(IUser user);

    /**
     * Returns whether the user can be locked out.
     *
     * @param user
     * @return
     */
    Future<Boolean> getLockoutEnabled(IUser user);

    /**
     * Sets whether the user can be locked out.
     *
     * @param user
     * @param enabled
     * @return
     */
    Future<Void> setLockoutEnabled(IUser user, boolean enabled);
}
