package org.jidentity;

/**
 * Represents a linked login for a user (i.e. a facebook/google account)
 */
public final class UserLoginInfo {
    private String loginProvider;
    private String providerKey;

    public UserLoginInfo(String loginProvider, String providerKey) {
        this.setLoginProvider(loginProvider);
        this.setProviderKey(providerKey);
    }

    /**
     * Gets the provider for the linked login, i.e. Facebook, Google, etc.
     *
     * @return
     */
    public String getLoginProvider() {
        return loginProvider;
    }

    /**
     * Sets the provider for the linked login, i.e. Facebook, Google, etc.
     *
     * @param loginProvider
     */
    public void setLoginProvider(String loginProvider) {
        this.loginProvider = loginProvider;
    }

    /**
     * Gets the user specific key for the login provider
     *
     * @return
     */
    public String getProviderKey() {
        return providerKey;
    }

    /**
     * Sets the user specific key for the login provider
     *
     * @param providerKey
     */
    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }
}
