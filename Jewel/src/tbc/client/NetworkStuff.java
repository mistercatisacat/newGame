package tbc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import tbc.game.Jewel;
import tbc.packets.Packet;
import tbc.packets.TestPacket;

public class NetworkStuff implements Runnable{
  ObjectInputStream is;
  ObjectOutputStream os;
  Jewel game;
  boolean stop = false;

  Socket crox;
  public NetworkStuff(){

    init("localhost",9999);

  }

  public void run() {
    while(JewelClient.runnung){
      processPackets();
    }

  }

  public void init(String string, int port) {
    try {
      crox = new Socket(string,port);
    } catch (IOException e) {
      // TODO Auto-generated catch block
    	System.out.println("couldnt connect to server");
    }
    try {
      is = new ObjectInputStream(crox.getInputStream());
    } catch (IOException e) {
    	System.out.println("couldnt connect to server");
    	}
    try {
      os = new ObjectOutputStream(crox.getOutputStream());
    } catch (IOException e) {
      // TODO Auto-generated catch block
    	System.out.println("couldnt connect to server");
    }
    TestPacket cat = new TestPacket();
    sendPacket(cat);

  }

  public synchronized void sendPacket(Packet p){
    try {
      os.writeObject(p);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println("couldnt sent packet");
      System.exit(-1);
    }
  
  }
  public void stop(){
	  stop = true;
  }
  public void processPackets(){
	  while(!stop){
    try {
      Packet in = (Packet) is.readObject();
      in.onClient(game);

    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
    	System.out.println("couldnt connect to server");
    } catch (IOException e) {
      // TODO Auto-generated catch block
    	System.out.println("couldnt connect to server");
    }
	  }

  }
	



}
