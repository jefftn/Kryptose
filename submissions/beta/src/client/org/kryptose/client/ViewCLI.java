package org.kryptose.client;

import org.kryptose.client.PasswordFile.BadBlobException;
import org.kryptose.exceptions.CryptoErrorException;

import java.util.Scanner;
import org.kryptose.exceptions.*;
import java.io.Console;

public class ViewCLI extends View {

    final static int CMD = 0;
    final static int USERNAME = 1;
    final static int PASSWORD = 2;
    final static int START = 3;
    final static int CREATEUSR = 4;
    final static int CREATEPASS = 5;

    Console console = System.console();
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
    void createUsername() {
        System.out.println("Enter new account user name");
        awaitInput(CREATEUSR);
    }

    @Override
    void createPass() {
        System.out.println("Enter new account password");
        String s;
        if (console != null) {
        	s = new String(console.readPassword());
        } else {
        	s = in.nextLine();
        }
        ctrl.handleCreatepass(s);

    }

    @Override
    void promptUserName() {
        System.out.println("Enter user name");
        awaitInput(USERNAME);

    }

    @Override
    void set(){
        System.out.print("Enter domain name\n> ");
        String dom = in.nextLine();
        System.out.print("Enter user name\n> ");
        String user = in.nextLine();
        System.out.print("Enter password\n> ");
//        String pass = new String(console.readPassword());
        String pass = in.nextLine();
        ctrl.handleSet(dom, user, pass);

    }
	
	@Override
	void promptPassword() {
		System.out.println("Enter master password");
//        String s = new String(console.readPassword());
        String s = in.nextLine();
        ctrl.handlePassword(s);
	}


    @Override
    void promptCmd() {
        awaitInput(CMD);
    }

    @Override
    void promptStart() {
        awaitInput(START);
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
//	        System.out.print("I'm awaiting input\n> ");
	    	System.out.print("> ");
	    	
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
//            else if (cmd == PASSWORD) {
//                ctrl.handlePassword(input);
//            }
            else if (cmd == START) {
                ctrl.handleStart(input);
            } else if (cmd == CREATEUSR) {
                ctrl.handleCreateuser(input);
            }
//            else if (cmd == CREATEPASS){
//                ctrl.handleCreatepass(input);
//            }
	    }

	}

}
