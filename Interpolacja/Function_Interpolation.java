public class Function_Interpolation 
{
	public static double funkcje_sklejane(double[][]points, double[] pochodne_x, double[] pochodne_y, double x, int n)
	{
		Matrix matrix = new Matrix(n);
		double result = 0;
        
        //wypisanie podstawowych równań
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j <= 3; j++) 
            {
                if (j == 0) 
                {
                    System.out.print("a" + j);
                }
                else 
                {
                    System.out.print(" + a" + j + "*" + Math.pow(points[i][0], j));
                }
                matrix.tab[i][j] = Math.pow(points[i][0], j);
            }
            
            for (int k = 0; k < i; k++) 
            {
                try 
                {
                    System.out.print(" + " + "a" + (k + 1) + "*" + Math.pow(points[i][0] - points[k + 1][0], 3));
                    matrix.tab[i][k+4] = Math.pow((points[i][0]) - points[k + 1][0], 3);
                }
                catch (Exception e) {}
            }
            
            matrix.tab[i][n+2] = points[i][1];
            System.out.print(" = " + points[i][1]);
            matrix.text[i] = "a"+i;
            System.out.println();
        }
        
        
        //rownania z pochodnych
        for (int i = 0; i < 2; i++) 
        {
            for (int j = 1; j <= 3; j++) 
            {
                if (j == 1) 
                {
                    System.out.print("a" + j);
                }
                else 
                {
                    System.out.print(" + " + j*Math.pow(pochodne_x[i], j-1) + "*a" + j);
                }
                matrix.tab[n+i][j] = j*Math.pow(pochodne_x[i], j-1);
            }
            if (i==0) 
            {
                matrix.tab[n+i][n+2] = pochodne_y[0];
                System.out.print(" = " + pochodne_y[0]);
                matrix.text[n] = "a1";
                System.out.println();
            }
        }
        for (int i = 1; i < n; i++) 
        {
            try 
            {
                System.out.print(" + " + 3 * Math.pow(pochodne_x[1] - points[i][0], 2) + "*a" + i);
                matrix.tab[n+1][i+3] = 3 * Math.pow(pochodne_x[1] - points[i][0], 2);
            }
            catch(Exception e) {}
        }
        matrix.tab[n+1][n+2] = pochodne_y[1];
        System.out.print(" = " + pochodne_y[1]);
        matrix.text[n+1] = "a2";


        //macierz poczatkowa
        System.out.println(System.lineSeparator());
        System.out.println("Start matrix:");
        System.out.println(matrix);


        //macierz po rozwiazaniu
        matrix.solveGauss();
        System.out.println("Solved matrix:");
        System.out.println(matrix);

        //alfy
        for (int i = 0; i < matrix.text.length; i++)
        {
            System.out.println(matrix.text[i] + " = " + (float)matrix.tab[i][matrix.tab.length]);
        }

        System.out.println(System.lineSeparator());

        //ostateczne dzialania
        for (int i = 0; i < 4; i++) 
        {
            result += (matrix.tab[i][matrix.tab.length] * Math.pow(x, i));
        }
        for (int i = 1; i < 3; i++) 
        {
            if (points[i][0] > x)
            {
            	break;
            }
            
            result += matrix.tab[i+n-1][matrix.tab.length] * Math.pow(x-points[i][0], 3);
        }
        
        System.out.println("Ostateczny wynik dla x = " + x + ":");
        return result;
	}
	
	
	
	public static void main(String[] args) 
	{
        double[][] points = {{-4, 861},{-2, 45},{0, -3},{2, 93},{4, 1245}
        };
        //dla jakiego x oczekujesz wyniku?
        double x = 1;
        //
        double[] pochodne_x = {-4, 4};
        double[] pochodne_y = {-896, 1184};
        int n = points.length;
        
        System.out.println(funkcje_sklejane(points,pochodne_x,pochodne_y,x,n));   
    }
}
