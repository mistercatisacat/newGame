package tbc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import tbc.packets.PacketExit;
import tbc.packets.PacketNewEntity;
import tbc.packets.PacketSendWorldInfo;
import tbc.packets.PacketSpawnClientPlayer;
import tbc.game.states.Game;
import tbc.packets.Packet;
import tbc.packets.TestPacket;

public class NetworkStuff implements Runnable {
	ObjectInputStream is;
	ObjectOutputStream os;
	boolean stop = false;
	private boolean serverLoaded = false;
	Socket crox;
	Game game;

	public NetworkStuff(String ip, int port, Game game) {
		init(ip, port);
		this.game = game;
	}

	public void run() {
		processPackets();
	}

	private void init(String string, int port) {
		try {
			crox = new Socket(string, port);
			is = new ObjectInputStream(crox.getInputStream());
			os = new ObjectOutputStream(crox.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("couldnt connect to server");
			stop();
		}
		TestPacket cat = new TestPacket();
		sendPacket(cat);
	}

	public synchronized void sendPacket(Packet p) {
		try {
			os.writeObject(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("couldnt sent packet");
			System.exit(-1);
		}

	}

	public void stop() {		
	//	System.out.println("exiting game....");
		Packet exit = new PacketExit();
		sendPacket(exit);		
	}

	public void processPackets() {
		int count = 0;
		while (!stop) {
			try {
				Packet in = (Packet) is.readObject();
				if (in instanceof PacketSpawnClientPlayer || in instanceof PacketSendWorldInfo) {
					count++;
					serverLoaded = count >= 2;
				}
				in.onClient(this, game);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("couldnt connect to server");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("couldnt connect to server");
			}
		}

	}
	
	synchronized public boolean serverLoaded(){
		return serverLoaded;
	}

	public void exit() {
		try {
			os.close();
			is.close();
			crox.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		stop = true;
	}
}
