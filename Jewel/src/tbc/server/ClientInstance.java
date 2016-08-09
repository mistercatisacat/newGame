package tbc.server;

public class ClientInstance implements Runnable{

	
	JewelServer server;
	
	public ClientInstance(JewelServer server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		init();
		
	}
	
	private void init(){
		
	}

}
