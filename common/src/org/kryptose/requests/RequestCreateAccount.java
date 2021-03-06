package org.kryptose.requests;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * A request to get the blob stored by this user.
 * <p>
 * Created by alexguziel on 3/15/15.
 */
public final class RequestCreateAccount extends Request {
	private static final long serialVersionUID = -4177944517246478600L;


	public RequestCreateAccount(User u) {
        super(u);
        this.validateInstance();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        // Check that our invariants are satisfied
        this.validateInstance();
    }

    @Override
    public void validateInstance() {
        super.validateInstance();
    }


    @Override
    public String logEntry() {
        return String.format("REQUEST: Create account from %s%n", super.getUser().getUsername());
    }
}
