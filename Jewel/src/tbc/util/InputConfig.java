package tbc.util;

import java.util.HashMap;

public class InputConfig {

	private static HashMap<String, Integer> keyConfig = new HashMap<String, Integer>();
	
	public static int getKey(String controlName){
		return keyConfig.get(controlName);
	}
	
}
