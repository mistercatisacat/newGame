package tbc.client;

import tbc.game.Jewel;

public class JewelClient {
	static boolean runnung = true;
	public static void main(String args[]) {
		NetworkStuff net = new NetworkStuff();
		while(runnung){
			net.processPackets();
		}
		(new Jewel()).loop();
	}
}
