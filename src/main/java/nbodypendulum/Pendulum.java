package nbodypendulum;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Pendulum {
	
	public float length, mass;
	public float angPos, angVel, angAcc;
	
	public PVector basePos = new PVector(0, 0), absPos =  new PVector(0, 0);
	
	public Pendulum(float angPos, float length, float mass) {
		
		this.length = length;
		this.mass = mass;
		this.angPos = angPos;
		
	}
	
	public void  applyAcc(ArrayList<Pendulum> pList, int j) {
		angAcc = AccCalculator.calc(pList, j);
	}
	
	public void update(ArrayList<Pendulum> pList, int j) {
		
		angVel += angAcc / AccCalculator.steps;
		angPos += angVel / AccCalculator.steps;
	}
	
	
	public float calcPotEnergy(ArrayList<Pendulum> pList, int j) {
		
		basePos = new PVector(0, 0);
		if (j > 1) basePos = pList.get(j - 2).absPos;
		absPos = new PVector(0, 1).rotate(-1 * angPos).mult(length).add(basePos);
		
		float totalHeight = 0;
		for (int i = 1; i <= j; i++) totalHeight += pList.get(i - 1).length;
		
		return (totalHeight - absPos.y) * AccCalculator.g * mass;
		
	}
	
	public float calcKinEnergy() {
		
		return (float) (0.5 * mass * Math.pow(angVel * length, 2));
		
	}
	
	public void draw(PApplet p, ArrayList<Pendulum> pList, int j) {
		
		basePos = new PVector(0, 0);
		if (j > 1) basePos = pList.get(j-2).absPos;
		absPos = new PVector(0, 1).rotate(-angPos).mult(length).add(basePos);
		
		p.stroke(100);
		p.strokeWeight(10);
		p.line(basePos.x, basePos.y, absPos.x, absPos.y);
		
		float ratio = calcKinEnergy() / (calcPotEnergy(pList, j) + calcKinEnergy());
		
		p.fill(255 * ratio, 200, 255 * (1 - ratio));
		p.noStroke();
		p.ellipse(absPos.x, absPos.y, 50/15 * mass, 50/15 * mass);
		
	}
	
}
