package tbc.server;

import java.util.HashMap;

public class JewelServer {

	HashMap<Integer, ClientInstance> clients = new HashMap<Integer, ClientInstance>();

	public static void main(String[] args) {
		new JewelServer();
	}

	public JewelServer() {

	}
	
	synchronized void addClient(int id,ClientInstance ci){
		clients.put(id, ci);
	}

}
