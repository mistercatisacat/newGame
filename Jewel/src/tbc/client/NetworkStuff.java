package tbc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import tbc.game.Jewel;
import tbc.packets.Packet;
import tbc.packets.TestPacket;

public class NetworkStuff {
	ObjectInputStream is;
	ObjectOutputStream os;
	Jewel game;

	Socket crox;
	public NetworkStuff(){

		init("localhost",9999);
		
	}
	
	
	public void init(String string, int port) {
		try {
		crox = new Socket(string,port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is = new ObjectInputStream(crox.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os = new ObjectOutputStream(crox.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestPacket cat = new TestPacket();
		sendPacket(cat);
		
	}

	public synchronized void sendPacket(Packet p){
		try {
			os.writeObject(p);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("couldnt sent packet");
			System.exit(-1);
		}
	}
	public void processPackets(){
		try {
			System.out.println("stuff...");
			Packet in = (Packet) is.readObject();
			in.onClient(game);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
