

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public server(int port){
		ServerSocket myserver = null;
		Socket client = null;
		DataInputStream input = null;
		PrintStream output = null;
		char io = 0;
		try {
			myserver = new ServerSocket(port);
			client = myserver.accept();
			input = new DataInputStream(client.getInputStream());
			output = new PrintStream(client.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true){
			try {
				io = input.readChar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//e.printStackTrace(); 
		
		System.out.println(io);
		}
	}

}
