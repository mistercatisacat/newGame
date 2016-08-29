package tbc.util;

public class Velocity {
	
	public int vx, vy;
	
	public Velocity(int vx, int vy){
		this.vx = vx;
		this.vy = vy;
	}
	
	public static Velocity PolarVelocity(int r, float theta){
		return new Velocity((int)(r * Math.cos(theta)), (int)(r * Math.sin(theta)));
	}
	
	public double getAngle(){
		return Math.atan(vy / (double)vx);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Velocity){
			return ((Velocity) obj).vx == vx && ((Velocity) obj).vy == vy;
		}
		return false;
	}

}
