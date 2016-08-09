package tbc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ClientInstance implements Runnable{

	
	JewelServer server;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	int id;
	
	public ClientInstance(JewelServer server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		init();
		
	}
	
	private void init(){
		
	}
	
	void setID(int id){
		this.id = id;
	}

}
