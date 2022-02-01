package connections.service;
//IlyaL
import java.util.HashMap;

import connections.dto.Connection;

public class ConnectionsPoolImpl implements ConnectionsPool {
	private static class Node {
		 Connection connection;
		 Node prev;
		 Node next;
	}
	private static class ConnectionsList {
		Node head;
		Node tail;
	}
	ConnectionsList list = new ConnectionsList();
	HashMap<Integer, Node> mapConnections = new HashMap<>();
	int connectionsPoolLimit;
	public ConnectionsPoolImpl(int limit) {
		this.connectionsPoolLimit = limit;
	}

	@Override
	public boolean addConnection(Connection connection) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
