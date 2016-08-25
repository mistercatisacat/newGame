package tbc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import tbc.game.Jewel;
import tbc.packets.ExitPacket;
import tbc.packets.Packet;
import tbc.packets.TestPacket;

public class NetworkStuff implements Runnable {
	ObjectInputStream is;
	ObjectOutputStream os;
	Jewel game;
	boolean stop = false;

	Socket crox;

	public NetworkStuff(String ip, int port) {

		init(ip, port);

	}

	public void run() {
		while (JewelClient.runnung) {
			processPackets();
		}

	}

	public void init(String string, int port) {
		try {
			crox = new Socket(string, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("couldnt connect to server");
		}
		try {
			is = new ObjectInputStream(crox.getInputStream());
		} catch (IOException e) {
			System.out.println("couldnt connect to server");
		}
		try {
			os = new ObjectOutputStream(crox.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("couldnt connect to server");
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
		stop = true;
		System.out.println("exiting game....");
		Packet exit = new ExitPacket();
		sendPacket(exit);
		// Wait a while and if it doesn't receive the exit packet exit anyways
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();				
		}
		
	}

	public void processPackets() {
		while (!stop) {
			try {
				Packet in = (Packet) is.readObject();
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
	public void exit() {
		try {
			os.close();
			is.close();
			crox.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
