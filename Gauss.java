
public class Gauss {
	
	private static double[][] problem = {
	        { 1,  2, 3, 14 },  // 1x + 2y + 3z = 14
	        { 1, -1, 1,  2 },  // 1x - 1y + 1z = 2
	        { 4, -2, 1,  3 }   // 4x - 2y + 1z = 3
	};

	public static void solve(double[][] d) 
	{
		 int rows = d.length;
	        for (int row=0; row<rows; row++)
	            solve(d,row);
	}
	
	public static void solve(double[][] d, int row) 
	{
		int rows = d.length;
        int cols = rows + 1;
        
        // 1. zmiana wartosci komórki c[row][row] na 1
        double factor = d[row][row];
        for (int col=0; col<cols; col++)
        {
        	d[row][col] /= factor;
        }   

        // 2. zmiana wartosci komórki c[row][row2] na 0
        for (int row2=0; row2<rows; row2++)
        {
            if (row2 != row) 
            {
                factor = -d[row2][row];
                for (int col=0; col<cols; col++)
                    d[row2][col] += factor * d[row][col];
            }
        }
	}
	
	public static void print(double[][] d) 
	{
		int r = d.length;
        int c = r + 1;
        for (int row=0; row<r; row++) 
        {
            for (int col=0; col<c; col++)
            {
                System.out.printf("%5.1f ",d[row][col]);
            }
            System.out.println();
        }
        System.out.println();
	}
	
	public static void do_problem(double[][] problem) 
	{
		System.out.println("Przyk³ad:");
		print(problem);
        solve(problem);
        System.out.println("Rozwi¹zanie:");
        print(problem);
	}
	
	
	public static void main(String[] args) 
	{
		do_problem(problem);

	}

}
