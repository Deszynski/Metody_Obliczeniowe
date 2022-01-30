
public class Lagrange_Interpolation {

	public static double lagrange(double[]a, double[]v, double x, int n)
	{
		double result=0, temp=0, nom=1, denom=1;
		for(int i=0; i<n; i++)
		{
			nom = v[i];
			denom = (x-a[i]);
			
			for(int j=0; j<n; j++)
			{
				nom*=(x-a[j]);
				if(i!=j) denom*=(a[i]-a[j]);
			}
			
			temp=nom/denom;
			result+=temp;
			
		}
		System.out.println("Ostateczny wynik dla x = "+x+":");
		return result;
	}
	
	public static void main(String[] args) 
	{
		double [] arguments = {-4,-2,0,2,4};//-5,-4,-1,3,5 przyklad z pdf
		double [] values = {861, 45, -3, 93, 1245};//975,433,7,-1,235 przyklad z pdf

		//dla jakiego x oczekujesz wyniku?
		double x = 1;
		//
		if(arguments.length!=values.length)
		{
			System.out.println("Nieprawid³owe dane.");
			System.exit(0);
		}
		int n = values.length;
		System.out.println(lagrange(arguments,values,x,n));
	}
}
