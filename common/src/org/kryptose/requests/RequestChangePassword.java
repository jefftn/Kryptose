package org.kryptose.requests;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * A request to get the blob stored by this user.
 * <p>
 * Created by alexguziel on 3/15/15.
 */
public final class RequestChangePassword extends Request {

    private byte[] newAuthkey;
    private Blob newBlob;
    private byte[] oldDigest;

    public RequestChangePassword(User u, byte[] newAuthkey, Blob newBlob, byte[] oldDigest) {
        super(u);
        this.newAuthkey = newAuthkey;
        this.validateInstance();
        this.newBlob = newBlob;
        this.oldDigest = oldDigest;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        // Check that our invariants are satisfied
        this.validateInstance();
    }

    public byte[] getNewAuthkey() {
        return newAuthkey;
    }

    public Blob getNewBlob() {
        return newBlob;
    }

    public byte[] getOldDigest() {
        return oldDigest;
    }

    @Override
    public void validateInstance() {
        super.validateInstance();
    }


    @Override
    public String logEntry() {
        return String.format("REQUEST: Change password from %s\n", super.getUser().getUsername());
    }
}