package client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class main {

	public static void main(String[] args) {
		try {
			client Client = new client(InetAddress.getByName("192.168.1.4"), 9999);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
