package tbc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class NetworkStuff {
	ObjectInputStream is;
	ObjectOutputStream os;

	Socket crox;
	public NetworkStuff(){
	try {
		init(InetAddress.getByName("127.0.0.1"),9999);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	
	public void init(InetAddress address, int port) {
		try {
		crox = new Socket(address,port);
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

	}

}
