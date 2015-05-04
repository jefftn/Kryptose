package org.kryptose.server;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kryptose.requests.Blob;
import org.kryptose.requests.RequestChangePassword;
import org.kryptose.requests.Response;
import org.kryptose.requests.ResponseChangePassword;
import org.kryptose.requests.User;

public class ServerRequestsHandlingTest {

	public static byte[] TEST_BYTE_ARRAY = {0,15,12,1,1};
	public static Blob	TEST_BLOB = new Blob(TEST_BYTE_ARRAY, TEST_BYTE_ARRAY);
	public static User USER = new User("antonio",TEST_BYTE_ARRAY);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Server s = new Server();
		//s.start();
		Response r = s.handleRequest(new RequestChangePassword(USER, TEST_BYTE_ARRAY, TEST_BLOB, TEST_BYTE_ARRAY));
		assertTrue(r instanceof ResponseChangePassword);
	}

}
