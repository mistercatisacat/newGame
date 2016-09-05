package tbc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.rowset.spi.SyncResolver;

import tbc.game.World;
import tbc.packets.Packet;
import tbc.packets.PacketUpdateEntity;

public class ClientInstance implements Runnable {

	private JewelServer server;
	boolean stop = false;
	private Socket soc;
	private World world;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private int id;
	boolean connected = true;
	private ServerGame game;

	public ClientInstance(JewelServer server, ServerGame game, Socket cliSoc,
			World world) {
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
		} catch (IOException e) {
			System.out
					.println("client #: " + id + " disconnected with an error");
			e.printStackTrace();
			server.purge(id);
			// System.exit(-1);
		}
	}
	
	/*public void response(Packet p){
		if (p instanceof PacketUpdateVelocity){
			server.broadcastPacket(p);;
		}
	}
*/
	
	protected void stop() {
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
	
	public int getID(){
		return id;
	}
}
