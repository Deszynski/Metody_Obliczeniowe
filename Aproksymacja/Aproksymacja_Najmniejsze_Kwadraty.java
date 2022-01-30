
public class Aproksymacja_Najmniejsze_Kwadraty {
	
	public static double value(double x)
	{
		return Math.sqrt(x*x+2*x+4); // wzór funkcji		
	}
	
	public static double wielomian(double x, double [] wspolczynniki)
	{
		double result = 0;
		
		for(int i=0; i<wspolczynniki.length; i++)
		{		
			result+=wspolczynniki[i]*Math.pow(x, i);
		}
		
		return result;	
	}

	public static void main(String[] args) 
	{
		double [] arg = {-1, -0.5, 0, 0.5, 1};
		double [] val = new double[5];
		double x = -0.25;
		
		System.out.println("Przewidywany wynik: "+value(x)+System.lineSeparator());
		
		for(int i=0; i<arg.length; i++)
		{
			val[i]=value(arg[i]);
			//System.out.println(val[i]+System.lineSeparator());
		}
	
		// liczba punktów n
		if(arg.length!=val.length)
			System.exit(13);
		int n = arg.length;
		
		// stopien wielomianu m
		int m = 10;	
		
		// tablica sum S i T
		double [] sum = new double[2*m+m+2];	
		
		// wyznaczanie S
		for(int i=0; i<=2*m; i++)
		{			
			for(int j=0; j<n; j++)
			{
				sum[i]+=Math.pow(arg[j],i);
			}	
			
			//System.out.println("sum["+i+"]="+sum[i]);
		}
		
		// wyznaczanie T
		for(int i=0; i<m+1; i++)
		{		
			for(int j=0; j<n; j++)
			{
				sum[i+1+2*m]+=Math.pow(arg[j], i)*val[j];
			}
			
			//System.out.println("sum["+(i+1+2*m)+"]="+sum[i+1+2*m]);
		}
		
		// tworzenie macierzy
		Matrix matrix = new Matrix(m-1);
		for(int i=0; i<m+1; i++)
		{		
			for(int j=0; j<m+1; j++)
			{
				matrix.tab[i][j]=sum[i+j];
			}	
		}
		for(int i=0; i<sum.length-2*m-1; i++)
		{		
			matrix.tab[i][matrix.tab.length]=sum[i+1+2*m];
		}
		
		//macierz poczatkowa
        System.out.println("Start matrix:");
        System.out.println(matrix);


        //macierz po rozwiazaniu
        matrix.solveGauss();
        System.out.println("Solved matrix:");
        System.out.println(matrix);
        
        // tabela ze wspolczynnikami wielomianu
        double [] wspolczynniki = new double[m+1];        
        for(int i=0; i<sum.length-2*m-1; i++)
		{		
			wspolczynniki[i] = matrix.tab[i][matrix.tab.length];
		}
        
        System.out.println("Ostateczny wynik: "+wielomian(x,wspolczynniki));
	}
}
