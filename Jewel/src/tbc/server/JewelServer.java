package tbc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import tbc.game.Jewel;

public class JewelServer {

	HashMap<Integer, ClientInstance> clients = new HashMap<Integer, ClientInstance>();
	ServerSocket server;
	boolean running = true;
	Jewel game;
	
	public static void main(String[] args) {
		new JewelServer();
	}

	public JewelServer() {
		game = new Jewel();
		try {
			server = new ServerSocket(9999);
			while(running){
				Socket cliSoc = server.accept();
				ClientInstance ci = new ClientInstance(this,game, cliSoc);
				(new Thread(ci)).start();
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
	synchronized void addClient(ClientInstance ci){
		int id = genID();
		clients.put(id, ci);
		ci.setID(id);
		//Add to game world
		//Send packets to everyone about the new client
	}
	
	private int genID(){
		int id = 0;
		while(clients.containsKey(id)){
			id++;
		}
		return id;
	}
}
