package org.kryptose.requests;

import org.kryptose.exceptions.ServerException;

/**
 * Created by alexguziel on 3/15/15.
 */
public final class ResponseGet extends Response {

    private final Blob blob;

    public ResponseGet(Blob b) {
    	super();
        this.blob = b;
    }
    
    public ResponseGet(ServerException exception) {
    	super(exception);
    	this.blob = null;
    }

    public Blob getBlob() throws ServerException {
    	// Is this an exception response? Throw exception if so.
    	if (this.getException() != null) {
    		throw this.getException();
    	}
    	// Return blob.
        return blob;
    }

    public String logEntry() {
        return "RESPONSE: Get Request Successful\n";
    }

}
