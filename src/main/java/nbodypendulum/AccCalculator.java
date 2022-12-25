package nbodypendulum;

import java.util.ArrayList;

public class AccCalculator {

	public static float g = (float) 0.25;
	public static int steps = 300;
	
	
	public static float calc(ArrayList<Pendulum> pList, int j) {
		
		int n = pList.size();
		
		float topSum = 0;
		for (int k = 1; k <= n; k++) {
			
			topSum += g * pList.get(j - 1).length
				* Math.sin(pList.get(j - 1).angPos) * 
				pList.get(k - 1).mass * sigma(j, k);
			
			
			float subSum = 0;
			for (int q = k; q <= n; q++) {
				subSum += pList.get(q - 1).mass * sigma(j, q);
			}
			
			topSum += subSum 
				* pList.get(j - 1).length * pList.get(k - 1).length
				* Math.sin(pList.get(j - 1).angPos - pList.get(k - 1).angPos)
				* pList.get(j - 1).angVel * pList.get(k - 1).angVel;
			
			topSum += subSum
				* pList.get(j - 1).length * pList.get(k - 1).length
				* 	(Math.sin(pList.get(k - 1).angPos - pList.get(j - 1).angPos)
					* (pList.get(j - 1).angVel - pList.get(k - 1).angVel)
					* pList.get(k - 1).angVel
					+	Math.cos(pList.get(j - 1).angPos - pList.get(k - 1).angPos)
						* pList.get(k - 1).angAcc * phi(j, k)
					);
			
		}
		
		float bottomSum = 0;
		for (int k = 1; k <= n; k++) { 
			
			bottomSum += pList.get(k - 1).mass
				* Math.pow(pList.get(j - 1).length, 2)
				* sigma(j, k);
		
		}
		
		return (float) (-topSum/bottomSum);
		
	}
	
	public static int sigma(int j, int k) {
		
		if (j > k) return 0;
		else return 1;
		
	}
	
	public static int phi(int j, int k) {
		
		if (j == k) return 0;
		else return 1;
		
	}
	
	public static float cut(float num) {
		
		return (float) (num * (Math.pow(0.3, num) / Math.pow(1.01, num)));
	
	}
	
}
