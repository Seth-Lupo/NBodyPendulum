package nbodypendulum;
import java.util.ArrayList;

import processing.core.PApplet;

public class ClosedSystem {
	
	public float totalEnergy;
	public ArrayList<Pendulum> pList;
	
	
	public ClosedSystem() {
		
		pList = new ArrayList<Pendulum>();
		
	}
	
	public void setInitialEnergy() {
		
		for (int j = 1; j <= pList.size(); j++) {
			totalEnergy += pList.get(j - 1).calcPotEnergy(pList, j);
		}
		
	}
	
//	public void checkEnergy() {
//		
//		float currentEnergy = 0;
//		for (int j = 1; j <= pList.size(); j++) {
//			currentEnergy += pList.get(j - 1).calcPotEnergy(pList, j);
//			currentEnergy += pList.get(j - 1).calcKinEnergy();
//		}
//		
//		float kineticEnergy = 0;
//		for (int j = 1; j <= pList.size(); j++) {
//			kineticEnergy += pList.get(j - 1).calcKinEnergy();
//		}
//		
//		if (kineticEnergy > 1) {
//			
//			float scale = (float) Math.sqrt(1 - (currentEnergy - totalEnergy)/kineticEnergy);
//			
//			for (int j = 1; j <= pList.size(); j++) {
//				pList.get(j - 1).angVelRev *= scale;
//			}
//
//		}
//		
//		//totalEnergy *= 0.99;
//		
//		float adjustedEnergy = 0;
//		for (int j = 1; j <= pList.size(); j++) {
//			adjustedEnergy += pList.get(j - 1).calcPotEnergy(pList, j);
//			adjustedEnergy += pList.get(j - 1).calcKinEnergy();
//		}
//		
////		System.out.println(kineticEnergy * 100/adjustedEnergy);
//		
//		
//		
//	}
	
	public void draw(PApplet p) {
		
		
		
		for (int i = 0; i < AccCalculator.steps; i++) {
			for (int j = 1; j <= pList.size(); j++) pList.get(j - 1).applyAcc(pList, j);  	
    		for (int j = 1; j <= pList.size(); j++) pList.get(j - 1).update(pList, j);  	
		}

    	for (int j = pList.size(); j > 0; j--) pList.get(j - 1).draw(p, pList, j);
//    	checkEnergy();
    	
		
	}
	
	
	
	
}
