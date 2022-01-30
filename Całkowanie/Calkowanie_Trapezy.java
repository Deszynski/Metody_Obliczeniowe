import java.util.Scanner;

public class Calkowanie_Trapezy {
	
	public static double value(double x)
	{
		return (2*Math.pow(x, 5)-Math.pow(x, 4)+5*Math.pow(x, 3))/(3*Math.pow(x, 4)+5);
		//return Math.sin(0.5*x+0.1)/(2.2+Math.sqrt(0.7*x*x+1.4)); // wzór funkcji
		//return x*x*Math.sqrt(1+x)/(1+x*x); // wzór z pdf na sprawdzenie
		//   return Math.sqrt(x*x+0.5)/(1.2+Math.sqrt(0.8*x*x+1.1));
	}
	
	public static void main(String[] args) 
	{
		double a = -1;// 0
		double b = 5;// 2
		System.out.println("f("+a+")="+value(a)+" "+"f("+b+")="+value(b));
		
		int n;
		Scanner s = new Scanner(System.in);
		System.out.println("Podaj n: ");
		n = s.nextInt();
		s.close();
		
		double h = (b-a)/n;
		double value = 0, result = 0;
		
		for(double i=a; i<=b; i+=h) // wyznaczanie kolejnych xi
		{
			value = value(i); 
			System.out.println(i);
			//System.out.println("f("+i+")="+value+" ");
			result+=value; // dodajemy kolejno wartoœci tworz¹c nawias (f(a) + f(x1)+ ... + f(xn-i) + f(b))
		}
		
		// odejmujemy nadmiarowe wartoœci z pêtli
		result -= (value(a)/2);
		result -= (value(b)/2);
		
		
		result *= h; // ca³ka = h * (f(a)/2 + f(x1)+ ... + f(xn-i) + f(b)/2)
		
		System.out.println("Wynik: "+result);
	}
}
