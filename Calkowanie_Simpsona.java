import java.util.Scanner;

public class Calkowanie_Simpsona {
	
	public static double value(double x)
	{
		return Math.sqrt(x*x+0.5)/(1.2+Math.sqrt(0.8*x*x+1.1)); // wzór funkcji
		//return x*x*Math.sqrt(1+x)/(1+x*x); // wzór z pdf na sprawdzenie
	}

	public static void main(String[] args) 
	{
		double a = 1.3;// 0
		double b = 2.5;// 2
		
		int n;
		Scanner s = new Scanner(System.in);
		System.out.println("Podaj n: ");
		n = s.nextInt();
		s.close();
		
		double[] x = new double[n+1];
		double[] t = new double[n];
		double result = 0;
		
		for(int i=0; i<=n; i++)
		{	
			x[i]=a+((double)i/(double)n)*(b-a);
			
			if(i==0 || i==n)
			{
				result += value(x[i]); // + skrajne x[i]
			}
			else
			{
				result += 2*value(x[i]); // + suma 2*x[i] 
			}
		}
		
		for(int j=0; j<n; j++)
		{
			t[j]=(x[j+1]+x[j])/2;
			result += 4*value(t[j]); // + suma 4*t[j]
		}
		
		double h = (x[1]-x[0])/2; // h = (x(i+1) - x(i)) /2
		result *= h/3; // wynik = nawias*h/3
		System.out.println("Wynik: "+result);
	}
}
