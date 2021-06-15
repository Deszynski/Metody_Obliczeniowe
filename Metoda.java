
public class Metoda {
	static int iteracje = 1;
	static double epsilon = 0.1;
	double a;
	double b;
	
	public Metoda(double a, double b)
	{
		this.a = a;
		this.b = b;
	}
	
	public static double value(double x)
	{
		return 1.8*Math.pow(x, 2)+12*x+2.2;
	}
	
	public static double derivative_I(double x)
	{
		return 3.6*x+12; 
	}
	
	public static double derivative_II(double x)
	{
		return 6;
	}

	public static boolean warunek_konieczny(double x, double y)
	{
		if(value(x) * value(y) < 0)
			return true;
		return false;
	}
	
	public static boolean warunek_stopu(double x)
	{
		if(Math.abs(value(x)) < epsilon)
			return true;		
		return false;
	}
	
	public static boolean warunki_zbieznosci(double x, double y)
	{
		if((derivative_I(x) * derivative_I(y) >= 0) && (derivative_II(x) * derivative_II(y) >= 0))
			return true;		
		return false;
	}
	
	public static boolean ruchome(double x)
	{
		if(!(value(x) * derivative_II(x) >= 0))
			return true;		
		return false;
	}
	
	public static double metoda_bisekcji(double a, double b)
	{
		double xsr = 0.5*(a+b);
		
		if(warunek_stopu(xsr))
			return xsr;			
			
		if(value(xsr)==0)
			return xsr;
		else
		{
			iteracje++;
			
			if(warunek_konieczny(xsr,a))
				return metoda_bisekcji(a,xsr);
			else
				return metoda_bisekcji(xsr,b);
		}
	}
	
	public static double metoda_stycznych(double a, double b)
	{		
		if(!warunki_zbieznosci(a,b))
		{
			System.out.println("Warunek konieczny nie zosta³ spe³niony." + System.lineSeparator());
			return 0;
		}		
		else
		{
			double [] x = new double[100];
			if(derivative_I(a) * derivative_II(a) >= 0)
				x[0] = a;
			else
				x[0] = b;
			
			for(int i=1; i<100; i++)
			{
				x[i] = x[i-1] - (value(x[i-1])/derivative_I(x[i-1]));
				iteracje++;
				if(warunek_stopu(x[i]) || warunek_stopu(x[i]-x[i-1]))
					return x[i];
			}
			
			return 0;
		}
	}
	
	public static double metoda_siecznych(double a, double b)
	{
		double [] x = new double[100];
		
		if(!ruchome(a))
		{
			x[0] = b;
			
			for(int i=1; i<100; i++)
			{
				x[i] = x[i-1] - (value(x[i-1])/(value(x[i-1])-value(a))) * (x[i-1]-a);
				iteracje++;
				if(warunek_stopu(x[i]) || warunek_stopu(x[i]-x[i-1]))
					return x[i];
			}
			
			return 0;
		}
		
		if(!ruchome(b))
		{
			x[0] = a;
			
			for(int i=1; i<100; i++)
			{
				x[i] = x[i-1] - (value(x[i-1])/(value(b)-value(x[i-1]))) * (b-x[i-1]);
				iteracje++;
				if(warunek_stopu(x[i]) || warunek_stopu(x[i]-x[i-1]))
					return x[i];
			}
			
			return 0;
		}
		
		return 0;
	}
	
	public static void clear()
	{
		iteracje = 1;
	}
}
