package nbodypendulum;

import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet  {
	
	public static ClosedSystem[] systems;
	
	public static final int WIDTH = 800, HEIGHT = 600;
	

    public static void main(String[] args) {
        PApplet.main("nbodypendulum.Main");
    }

    public void settings(){
    
    	size(WIDTH, HEIGHT);
    	
    	
    }

    public void setup(){
    	
    	frameRate((float) 50);
    	
    	systems = new ClosedSystem[8];
    	for (int i = 0; i < systems.length; i++) {
    		
    		systems[i] = new ClosedSystem();
    		
    		for (int j = 0; j < 4; j++) {
    			systems[i].pList.add(new Pendulum((float) (PI * 2/5 + j/2 + 0.0000001*i), (float) (150 * Math.pow(0.9, j)), (float) (10 * Math.pow(0.9, j))));
    		}
    		
    	}
    	
    	
    	
    }

    public void draw(){
    	
    	fill(255, 122);
    	rect(0, 0, WIDTH, HEIGHT);
    	
    	pushMatrix();
    	
    	translate(WIDTH/2, HEIGHT/ 8);
    	
    	for (int i = 0; i < systems.length; i++) {
    		
    		systems[i].draw(this);
    	}
    	
    	popMatrix();
    
    }
    	
}