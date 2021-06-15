public class Uklady_rownan_liniowych {
	
	static int iteracje = 0;
    static final double epsilon = 0.01;
	
	static double[][] uklad = {
    		{2, -2, 4, -2, -8},
    		{8, -5, 14, -7, -32},
    		{8, -2, 13, -7, -35},
    		{-8, 5, -12, 3, 20}
    };
	
	static int w = uklad.length;
	static int k = uklad[0].length;
    
    static double[] temp1 = new double[w];
    static double[] temp2 = new double[w];
    static double[] divisors = new double[w];
	
	public static void metoda_iteracji_prostych() 
	{
		int recursion = 0;
		iteracje++;
		
		
		for (int i = 0; i < w; i++) 
		{
			double temp = 0;
			
			for (int j = 0; j < k; j++) 
			{
				if (j != w) 
					temp += uklad[i][j] * temp2[j];
				else 
					temp += uklad[i][j];
			}
			
			temp2[i] = temp;
			
			if (Math.abs(temp2[i] - temp1[i]) < epsilon) 
				recursion -= 1;
		}
		
		if (recursion == 0) 
		{
			System.out.println("Wyniki: ");
			
			for (int i = 0; i < w; i++) 
				System.out.println("x"+i+" = "+temp2[i]);
			
			//System.out.println("x0 = "+(-2.0)+System.lineSeparator()+"x1 = " +(-1.0)+System.lineSeparator()+"x2 = "+ +0.0+System.lineSeparator()+"x3 = "+ +3.0);
			System.out.println("Liczba iteracji: "+iteracje);
		}
		else 
		{
			for (int i = 0; i < w; i++)
				temp1[i] = temp2[i];
			
			metoda_iteracji_prostych();
		}		
	}  
    
    public static void main(String[] args) 
    {
    	for (int i = 0; i < w; i++) 
    		divisors[i] = uklad[i][i];
    	
    	for (int i = 0; i < w; i++) 
    	{
    		for (int j = 0; j < k; j++) 
    		{
    			uklad[i][j] = uklad[i][j] / divisors[i];
    			uklad[i][i] = 0;
    		}
    	}
    	
    	for (int i = 0; i < w; i++) 
    	{
    		for (int j = 0; j < w; j++) 
    		{
    			uklad[i][j] *= -1;
    		}
    	}
 
    	metoda_iteracji_prostych();   
    }
}