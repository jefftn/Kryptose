package org.kryptose.server;

import org.kryptose.requests.Request;
import org.kryptose.requests.Response;
import org.kryptose.requests.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Runs a thread that listens for requests from a client, and sends back responses when available.
 *
 */
class ClientHandler implements Runnable {

    private Server server;
    private Socket sock;
    private User user; // TODO: user authentication
    
    ClientHandler(Server server, Socket clientSocket) {
    	this.server = server;
        this.sock = clientSocket;
    }
    
    public void run() {
    	// TODO: user authentication.
    	try {
//    		while (true) {
    			// Receive a request.
    			Request request = listen();
//    			if (request == null) break;

    			// Process request.
				Future<Response> future = this.server.addToWorkQueue(this.user, request);
				Response resp;
    			try {
    				// TODO: set a more reasonable timeout.
    				resp = future.get(1, TimeUnit.HOURS);
    			} catch (InterruptedException e) {
    				// Something presumably wants this thread to stop.
 //   				break;
    				resp=null;
    			} catch (ExecutionException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				resp = null;
    			} catch (TimeoutException e) {
    				future.cancel(false); // TODO: timeout... cancel if running?
    				resp = null; // TODO: response in case of timeout
				}
    			
    			// Send back the response.
    			if (resp != null) speak(resp);
//    		}
    	}
    	finally {
    		try {
				if (!sock.isClosed()) sock.close();
			} catch (IOException e) {
				// Give up.
				// TODO: log this error?
				e.printStackTrace();
			}
    	}
    	
    }
    
    /**
     * Listen for a request from the client.
     * @return The request heard, or {@literal null} if the connection is no longer valid.
     */
    private Request listen() {
    	ObjectInputStream in=null;
    	try {
        	in = new ObjectInputStream(sock.getInputStream());
            return (Request)in.readObject();
        } catch (IOException ex) {
        	// TODO catch block
        	ex.printStackTrace();
        	return null;
        } catch (ClassNotFoundException | ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
/*    	finally{
			if(in!=null)
				try {
//					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}*/
    }
    
    /**
     * Sends a response back to the client.
     * @param response The response to send.
     */
    private void speak(Response response) {
        try {
        	ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
        	out.writeObject(response);
//        	out.close();
        } catch (IOException ex) {
        	// TODO catch block
        	ex.printStackTrace();
        }
    }
    
}