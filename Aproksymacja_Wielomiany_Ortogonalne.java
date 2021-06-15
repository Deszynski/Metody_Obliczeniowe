public class Aproksymacja_Wielomiany_Ortogonalne {
	
	static final int acurracy = 10000; 
	
	public static double value(double x) 
	{
		return Math.sqrt(Math.pow(x, 2)+2*x+4); // wzór funkcji
	}
	
	public static double legendre(int index, double x) 
	{
		 switch (index) 
		 {
		 	case 0:
		 		return 1;
		 	case 1:
		 		return x;
		 	default:
		 		return ((2*(index-1)+1)*x*legendre(index-1,x)-(index-1)*legendre(index-2,x))/index;		 		
		 }
	}
		 
	public static double lambda(int index, double a, double b) 
	{
		 double integral = 0;
		 
		 double h = (b - a) / acurracy;

		 for (int i = 1; i <= acurracy; i++) 
		 {
			 integral += legendre(index, a + i * h) * legendre(index, a + i * h);
		 }
		 
		 integral *= h;
		 
		 return integral;
	}
	
	public static double c(int index, double a, double b) 
	{
		 double integral = 0;
		 
		 double h = (b - a) / acurracy;
		 
		 for (int i = 1; i <= acurracy; i++) 
		 {
			 integral += legendre(index, a + i * h) * value(a + i * h);
		 }
		 
		 integral *= h;
		 
		 return (1 / lambda(index, a, b)) * integral;
	}
		 
	public static void main(String[] args) 
	{
		 double result = 0;
		 double x = -0.25;
		 double a = -1;
		 double b = 1;
		 int n = 20;
		 
		 for (int index = 0; index <= n; index++) 
		 {
			 result += c(index, a, b) * legendre(index, x);
		 }

		 System.out.println("Przewidywany wynik: " + value(x));
		 System.out.println("Ostateczny wynik: " + result);
	}

}
