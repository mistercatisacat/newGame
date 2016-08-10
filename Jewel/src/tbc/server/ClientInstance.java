package tbc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.rowset.spi.SyncResolver;

import tbc.game.Jewel;
import tbc.packets.Packet;

public class ClientInstance implements Runnable{

	
	JewelServer server;
	Socket soc;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	int id;
	boolean connected = true;
	Jewel game;
	
	public ClientInstance(JewelServer server, Jewel game, Socket cliSoc) {
		this.server = server;
		soc = cliSoc;
		this.game = game;
	}
	
	@Override
	public void run() {
		try{
			init();
			while(connected && server.running){
				proccesPackets();
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
	
	void proccesPackets(){
		try {
			Packet in = (Packet) ois.readObject();
			in.onServer(server,game);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void sendPacket(Packet p){
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
