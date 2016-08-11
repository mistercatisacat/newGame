package tbc.client;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.BufferedImageUtil;

import tbc.game.Jewel;
import tbc.game.World;

public class JewelClient extends StateBasedGame{
	public JewelClient(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	static Thread t;
	   public static final int WIDTH   = 640;
	    public static final int HEIGHT  = 480;
	    public static final int FPS     = 60;
	    public static final double VERSION = 1.0;
	static boolean runnung = true;
	  static NetworkStuff net;
	  static World world;
	  static ImageLoader load;
	  Image back;
	public static void main(String args[]) {
	//start thread to receive packets at all time
	world = new World();

	//startNetThread();	
	  try {
          AppGameContainer app = new AppGameContainer(new JewelClient("My Game v" + VERSION));
          app.setDisplayMode(WIDTH, HEIGHT, false);
          app.setTargetFrameRate(FPS);
          app.setShowFPS(true);
          app.start();
      } catch(SlickException e) {
          e.printStackTrace();
      }
		load = new ImageLoader("/home/carl/best/Jewel/assets/");
	net.stop();
	System.exit(0);
}

	public static void startNetThread(){
		net = new NetworkStuff();
	      t = new Thread(net);
	      t.start();
		

	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new Test());
	}
	}
