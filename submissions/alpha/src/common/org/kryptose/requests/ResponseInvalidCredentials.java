package org.kryptose.requests;

/**
 * Created by alexguziel on 3/15/15.
 */
public final class ResponseInvalidCredentials extends Response {
    private final User user;

    public ResponseInvalidCredentials(User u) {
        user = u;
    }

    public User getUser() {
        return user;
    }

    public String logEntry() {
        return String.format("RESPONSE: Invalid credentials for user %s\n", user.getUsername());
    }
}
