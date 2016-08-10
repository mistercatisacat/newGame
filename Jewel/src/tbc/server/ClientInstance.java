package tbc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientInstance implements Runnable{

	
	JewelServer server;
	Socket soc;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	int id;
	boolean connected = true;
	
	public ClientInstance(JewelServer server, Socket cliSoc) {
		this.server = server;
		soc = cliSoc;
	}
	
	@Override
	public void run() {
		try{
			init();
			while(connected && server.running){
				
			}
			
			
		}catch(IOException e){
			e.printStackTrace();
			System.err.println("Client failed! ");
		}
	}
	
	private void init() throws IOException{ 
		
		oos = new ObjectOutputStream(soc.getOutputStream());
		ois = new ObjectInputStream(soc.getInputStream());
		
		server.addClient(this);
	}
	
	void setID(int id){
		this.id = id;
	}

}
