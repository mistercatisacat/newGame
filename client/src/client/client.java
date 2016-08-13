package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class client {
	public client(InetAddress address, int port){
		Socket myClient = null;
		DataInputStream input = null;
		DataOutputStream output = null;
		try {
			myClient = new Socket(address, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	try {
			input = new DataInputStream(myClient.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			output = new DataOutputStream(myClient.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(myClient.isConnected()){
		try {
			output.writeChar('v');
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

}
