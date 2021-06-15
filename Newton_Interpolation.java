
public class Newton_Interpolation {
	
	public static double iloraz_roznicowy(double v2, double v1, double a2, double a1)
	{
		return (v2-v1)/(a2-a1);
	}
	
	public static double newton(double[]a, double[]v, double x, int n)
	{
		double result;
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
				System.out.println("Ilorazy roznicowe "+i+" rzedu: ");	
				for(int k=0; k<n-i; k++)
				{
					tab[i][k] = iloraz_roznicowy(tab[i-1][k+1],tab[i-1][k],a[k+i],a[k]);
					System.out.println(tab[i][k]);
					//System.out.println("(i="+i+", k="+k+")");		
				}
				i++;
				System.out.println(System.lineSeparator());	
			}	
		}
		
		result = tab[0][0];

		double product;	
		for(int p=1; p<n; p++)
		{
			product = tab[p][0];
			for(int q=0; q<p; q++)
			{
				product*=(x-a[q]);
			}
			result+=product;
		}	

		System.out.println("Ostateczny wynik dla x = "+x+":");
		return result;
	}

	public static void main(String[] args) 
	{
		double [] arguments = {-3,0,3,6};//0,2,3,4,6 liczby z pdf na sprawdzenie
		double [] values = {-9, 0, 45, 288};//1,3,2,5,7 liczby z pdf na sprawdzenie
		
		//dla jakiego x oczekujesz wyniku?
		double x = 2;
		//
		
		if(arguments.length!=values.length)
		{
			System.out.println("Nieprawid³owe dane.");
			System.exit(0);
		}
		
		int n = values.length;
		System.out.println(newton(arguments,values,x,n));
	}

}
