package connections.service;
//IlyaL

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connections.dto.Connection;

class ConnectionPoolTests {
private static final int LIMIT = 5;
private static final int ID1 = 1;
private static final int ID2 = 2;
private static final int ID3 = 3;
private static final int ID4 = 4;
private static final int ID5 = 5;
private static final int ID101 = 101;
private static final int ID102 = 102;
private static final int ID103 = 103;
private static final int ID104 = 104;
private static final int ID105 = 105;
private static String IP_ADRESS = "192.192.192.192";
private static int PORT = 1234;
Connection conn1 = new Connection(ID1, IP_ADRESS, PORT);
Connection conn2 = new Connection(ID2, IP_ADRESS, PORT);
Connection conn3 = new Connection(ID3, IP_ADRESS, PORT);
Connection conn4 = new Connection(ID4, IP_ADRESS, PORT);
Connection conn5 = new Connection(ID5, IP_ADRESS, PORT);
Connection conn101 = new Connection(ID101, IP_ADRESS, PORT);
Connection conn102 = new Connection(ID102, IP_ADRESS, PORT);
Connection conn103 = new Connection(ID103, IP_ADRESS, PORT);
Connection conn104 = new Connection(ID104, IP_ADRESS, PORT);
Connection conn105 = new Connection(ID105, IP_ADRESS, PORT);


ConnectionsPool pool = new ConnectionsPoolImpl(LIMIT);

	@BeforeEach
	void setUp() throws Exception {
		pool.addConnection(conn1);
		pool.addConnection(conn2);
		pool.addConnection(conn3);
		pool.addConnection(conn4);
		pool.addConnection(conn5);		
	}
/*
To test for the newest one, we will show that a 
connection will still be in the pool after 4 new
 connections have been added, and removed from the 
 pool only after the fifth connection has been added.
 
 A sign of a connection in the pool - 
 returning false when trying to add it there.
 
 A sign of the absence of a connection in the pool - 
 get returns null or work out the truth of the 
 function to add this connection.
 */
	@Test
	void testAddConnection() {
	//newest= conn5 - conn4 - conn3 - conn2 - conn1 = oldest
		assertFalse(pool.addConnection(conn1));
	//newest= conn5 - conn4 - conn3 - conn2 - conn1 = oldest
		assertTrue(pool.addConnection(new Connection(ID101, IP_ADRESS, PORT)));
		assertFalse(pool.addConnection(conn101));
		assertFalse(pool.addConnection(conn5));
		assertFalse(pool.addConnection(conn4));
		assertFalse(pool.addConnection(conn3));
		assertFalse(pool.addConnection(conn2));
	//newest= conn101 - conn5 - conn4 - conn3 - conn2 = oldest
		assertTrue(pool.addConnection(new Connection(ID102, IP_ADRESS, PORT)));
		assertFalse(pool.addConnection(conn101));
		assertFalse(pool.addConnection(conn102));
		assertFalse(pool.addConnection(conn5));
		assertFalse(pool.addConnection(conn4));
		assertFalse(pool.addConnection(conn3));
	//newest= conn102 - conn101 - conn5 - conn4 - conn3 = oldest
		assertTrue(pool.addConnection(new Connection(ID103, IP_ADRESS, PORT)));
		assertFalse(pool.addConnection(conn101));
		assertFalse(pool.addConnection(conn102));
		assertFalse(pool.addConnection(conn103));
		assertFalse(pool.addConnection(conn5));
		assertFalse(pool.addConnection(conn4));
	//newest= conn103 - conn102 - conn101 - conn5 - conn4 = oldest
		assertTrue(pool.addConnection(new Connection(ID104, IP_ADRESS, PORT)));
		assertFalse(pool.addConnection(conn101));
		assertFalse(pool.addConnection(conn102));
		assertFalse(pool.addConnection(conn103));
		assertFalse(pool.addConnection(conn104));
		assertFalse(pool.addConnection(conn5));
//newest= conn104 - conn103 - conn102 - conn101 - conn5 = oldest
		assertTrue(pool.addConnection(new Connection(ID105, IP_ADRESS, PORT)));
		assertFalse(pool.addConnection(conn101));
		assertFalse(pool.addConnection(conn102));
		assertFalse(pool.addConnection(conn103));
		assertFalse(pool.addConnection(conn104));
		assertFalse(pool.addConnection(conn105));
//newest= conn105 - conn104 - conn103 - conn102 - conn101 = oldest
	}

	@Test
	void testGetConnection() {
//newest= conn5 - conn4 - conn3 - conn2 - conn1 = oldest
		assertNull(pool.getConnection(ID101));
//newest= conn5 - conn4 - conn3 - conn2 - conn1 = oldest
		assertEquals(conn1, pool.getConnection(ID1));
//newest= conn1 - conn5 - conn4 - conn3 - conn2 = oldest
		assertTrue(pool.addConnection(new Connection(ID101, IP_ADRESS, PORT)));
		assertTrue(pool.addConnection(new Connection(ID102, IP_ADRESS, PORT)));
		assertTrue(pool.addConnection(new Connection(ID103, IP_ADRESS, PORT)));
		assertTrue(pool.addConnection(new Connection(ID104, IP_ADRESS, PORT)));
//newest= conn104 - conn103 - conn102 - conn101 - conn1 = oldest
		assertFalse(pool.addConnection(conn1));
//newest= conn104 - conn103 - conn102 - conn101 - conn1 = oldest
		assertTrue(pool.addConnection(new Connection(ID105, IP_ADRESS, PORT)));
//newest= conn105 - conn104 - conn103 - conn102 - conn101 = oldest
		assertNull(pool.getConnection(ID1));
//newest= conn105 - conn104 - conn103 - conn102 - conn101 = oldest

		
	}

}
