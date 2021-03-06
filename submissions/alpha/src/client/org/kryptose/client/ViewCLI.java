package org.kryptose.client;

import org.kryptose.client.PasswordFile.BadBlobException;
import org.kryptose.requests.CryptoErrorException;

import java.util.Scanner;

public class ViewCLI extends View {

    final static int CMD = 0;
    final static int USERNAME = 1;
	
	ClientController ctrl;
    Scanner in;

	public ViewCLI(ClientController c) {
		this.ctrl = c;
        in = new Scanner( System.in );
	}
	
	private void awaitInput(int cmd) {
        (new InputThread(cmd)).start();
	}


    @Override
    void promptUserName() {
        System.out.println("Enter user name");
        awaitInput(USERNAME);

    }
	
	@Override
	void promptPassword() {
		System.out.println("Enter master password");
		
	}

    @Override
    void promptCmd() {
        awaitInput(CMD);
    }

	@Override
	void promptCmd(String s) {
        System.out.println(s);
        awaitInput(CMD);
	}

    @Override
    void displayMessage(String s) {
        System.out.println(s);
    }

	@Override
	void logout() {
        System.out.println("Logging out!");
	}

	public class InputThread extends Thread {

        int cmd;
        public InputThread(int cmd){
            this.cmd = cmd;
        }

	    public void run() {
	        System.out.print("I'm awaiting input\n> ");
	        String input = in.nextLine();

            if (cmd == CMD) {
                try {
                    ctrl.handleRequest(input);
                } catch (CryptoErrorException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BadBlobException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (cmd == USERNAME) {
                ctrl.handleUserName(input);
            }
	    }

	}

}
