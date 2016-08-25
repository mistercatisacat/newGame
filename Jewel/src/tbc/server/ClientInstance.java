package tbc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.rowset.spi.SyncResolver;

import tbc.game.Jewel;
import tbc.game.World;
import tbc.packets.Packet;

public class ClientInstance implements Runnable {

	JewelServer server;
	boolean stop = false;
	Socket soc;
	private World world;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	int id;
	boolean connected = true;
	Jewel game;

	public ClientInstance(JewelServer server, Jewel game, Socket cliSoc, World world) {
		this.world = world;
		this.server = server;
		soc = cliSoc;
		this.game = game;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				init();
				while (connected && server.running) {
					proccesPackets();
				}
			} catch (IOException e) {
				System.err.println("Client failed! ");
			}
		}
	}

	private void init() throws IOException {
		oos = new ObjectOutputStream(soc.getOutputStream());
		ois = new ObjectInputStream(soc.getInputStream());
		server.addClient(this);
	}

	void setID(int id) {
		this.id = id;
	}

	void proccesPackets() {
		try {
			Packet in = (Packet) ois.readObject();
			in.onServer(server, game, id);
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			System.out.println("client #: " + id + " disconnected with an error");
			e.printStackTrace();
			server.purge(id);
			// System.exit(-1);
		}
	}
	
	protected void stop(){
		stop = true;
		connected = false;
	}

	public synchronized void sendPacket(Packet p) {
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		return connected;
	}
}
