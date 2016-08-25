package tbc.util;

import java.util.HashMap;

import org.newdawn.slick.Input;

public class InputConfig {

	private static HashMap<String, Integer> keyConfig = new HashMap<String, Integer>(){{
		 put("UP", Input.KEY_W);
	     put("DOWN", Input.KEY_S);
	     put("LEFT", Input.KEY_A);
	     put("RIGHT", Input.KEY_D);
	}};	
	public static int getKey(String controlName){
		return keyConfig.get(controlName);
	}
	
}
