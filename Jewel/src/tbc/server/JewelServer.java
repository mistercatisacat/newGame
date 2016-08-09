package tbc.server;

import java.net.Socket;
import java.util.HashMap;

public class JewelServer {

	HashMap<Integer, ClientInstance> clients = new HashMap<Integer, ClientInstance>();
	Socket server;
	
	public static void main(String[] args) {
		new JewelServer();
	}

	public JewelServer() {

	}
	
	synchronized void addClient(int id,ClientInstance ci){
		clients.put(id, ci);
		
	}
	
	private void genID(){
		int id = 0;
		while(clients.containsKey(id)){
			id++;
		}
		
	}


}
