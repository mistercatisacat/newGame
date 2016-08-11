package tbc.client;

import tbc.game.Jewel;

public class JewelClient {
	static boolean runnung = true;
	public static void main(String args[]) {
		JewelClient client = new JewelClient(new Jewel());
	}
	
	public JewelClient(Jewel game){
	  NetworkStuff net = new NetworkStuff();
      Thread t = new Thread(net);
      t.start();
      (new Jewel()).loop();
	}
}
