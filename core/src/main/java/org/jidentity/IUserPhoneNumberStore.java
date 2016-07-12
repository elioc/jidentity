package org.jidentity;

import java.util.concurrent.Future;

/**
 * Stores a user's phone number
 */
public interface IUserPhoneNumberStore extends IUserStore {

    /**
     * Set the user's phone number
     *
     * @param user
     * @param phoneNumber
     * @return
     */
    Future<Void> setPhoneNumber(IUser user, String phoneNumber);

    /**
     * Get the user phone number
     *
     * @param user
     * @return
     */
    Future<String> getPhoneNumber(IUser user);

    /**
     * Returns true if the user phone number is confirmed
     *
     * @param user
     * @return
     */
    Future<Boolean> getPhoneNumberConfirmed(IUser user);

    /**
     * Sets whether the user phone number is confirmed
     *
     * @param user
     * @param confirmed
     * @return
     */
    Future<Void> setPhoneNumberConfirmed(IUser user, Boolean confirmed);
}
