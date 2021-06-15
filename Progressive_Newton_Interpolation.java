
public class Progressive_Newton_Interpolation {
	
	static int silnia(int x)
	{
	    int temp = 1;
	    for (int i = 2; i <= x; i++)
	    {
	    	temp *= i;
	    }
	    
	    return temp;
	}
	
	public static double newton(double[]a, double[]v, double x, int n, double h)
	{
		double result = v[0]; //zaczynamy od pierwszej wartosci f(x0)
		double [][] tab = new double [n][n];

		int i = 0;
		while(i<n)
		{
			if(i==0)
			{	
				for(int j=0; j<n; j++)
				{
					tab[i][j]=v[j];
				}
				i++;
			}
			else
			{
				System.out.println("Roznice progresywne "+i+" rzedu: ");	
				for(int k=0; k<n-i; k++)
				{
					tab[i][k] = tab[i-1][k+1]-tab[i-1][k];
					System.out.println(tab[i][k]);
					//System.out.println("(i="+i+", k="+k+")");		
				}
				i++;
				System.out.println(System.lineSeparator());	
			}	
		}
		
		double product;	
		for(int p=1; p<n; p++)
		{
			product = tab[p][0]/(silnia(p)*Math.pow(h, p));
			
			for(int q=0; q<p; q++)
			{
				product*=(x-a[q]); // mnozymy u³amek przez kolejne nawiasy (x-xi)
			}
			result+=product; // dodajemy powsta³e iloczyny uzyskuj¹c wynik koncowy
		}
		
		return result;
	}

	public static void main(String[] args) 
	{
		double [] arguments = {-4,-2,0,2,4};//1,2,3,4 przyklad z pdf
		double [] values = {861, 45, -3, 93, 1245};//3,7,8,15 przyklad z pdf
		//dla jakiego x oczekujesz wyniku?
		double x = 1;
		//
		
		if(arguments.length!=values.length)
		{
			System.out.println("Nieprawid³owe dane.");
			System.exit(0);
		}
		
		int n = values.length;
		double h = arguments[1]-arguments[0]; // zak³adamy ¿e wêz³y s¹ równoleg³e
		System.out.println(newton(arguments,values,x,n,h));

	}
}
