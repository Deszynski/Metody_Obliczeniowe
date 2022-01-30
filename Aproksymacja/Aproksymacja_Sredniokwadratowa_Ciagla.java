public class Aproksymacja_Sredniokwadratowa_Ciagla {
	
	static final int weight = 1;
	
	public static double determinant(double[][] matrix, int current_i, int current_j) 
	{
		 double[][] new_matrix = new double[matrix.length - 1][matrix.length - 1];
		 
		 for (int i = 0; i < current_i; i++) 
		 {
			 for (int j = 0; j < matrix.length; j++) 
			 {
				 if (j < current_j) 
				 {
					 new_matrix[i][j] = matrix[i][j];
				 }
				 else if (j > current_j) 
				 {
					 new_matrix[i][j - 1] = matrix[i][j];
				 }
			 }
		 }
		 
		 for (int i = current_i + 1; i < matrix.length; i++) 
		 {
			 for (int j = 0; j < matrix.length; j++)
			 {
				 if (j < current_j) 
				 {
					 new_matrix[i - 1][j] = matrix[i][j];
				 }
				 else if (j > current_j) 
				 {
					 new_matrix[i - 1][j - 1] = matrix[i][j];
				 }
			 }
		 }
		 
		 return determinant(new_matrix);
	}
	
	public static double determinant(double[][] matrix) 
	{
		 if (matrix.length == 2 & matrix[0].length == 2) 
		 {
			 return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
		 }
		 else 
		 {
			 double sum = 0;
			 
			 for (int i = 0; i < matrix.length; i++) 
			 {
				 sum += Math.pow(-1, i + 0) * matrix[i][0] * determinant(matrix, i, 0);
			 }
			 
			 return sum;
		 }
	}
	
	public static double[][] complement(double[][] matrix) 
	{
		 double[][] complement_matrix = new double[matrix.length][matrix[0].length];
		 for (int i = 0; i < matrix.length; i++) 
		 {
			 for (int j = 0; j < matrix[0].length; j++) 
			 {
				 complement_matrix[i][j] = Math.pow(-1, i+j) * determinant(matrix, i, j);
			 }
		 }
		 
		 return complement_matrix;
	}
		 
	public static double[][] transpose(double[][] matrix) 
	{
		 double[][] transposed_matrix = new double[matrix.length][matrix[0].length];
		 
		 for (int i = 0; i < matrix.length; i++) 
		 {
			 for (int j = 0; j < matrix[0].length; j++) 
			 {
				 transposed_matrix[i][j] = matrix[j][i];
			 }
		 }
		 
		 return transposed_matrix;
	}
		 
	public static double[][] reverseMatrix(double[][] matrix) 
	{
		 double[][] transposed_matrix;
		 transposed_matrix = transpose(complement(matrix));
		 double[][] reversed = new double[matrix.length][matrix[0].length];
		 
		 for (int i = 0; i < matrix.length; i++) 
		 {
			 for (int j = 0; j < matrix[0].length; j++) 
			 {
				 reversed[i][j] = (transposed_matrix[i][j] * (1 / determinant(matrix)));
			 }
		 }
		 
		 return reversed;
	}
		 
	public static double[][] multiplication(double[][] matrix_1, double[][] matrix_2) 
	{
		 double[][] new_matrix = new double[matrix_1.length][matrix_2[0].length];
		 
		 for (int i = 0; i < matrix_1.length; i++) 
		 {
			 for (int j = 0; j < matrix_2[0].length; j++) 
			 {
				 double sum = 0;
				 
				 for (int k = 0; k < matrix_1[0].length; k++) 
				 {
					 sum += matrix_1[i][k] * matrix_2[k][j];
				 }
				 
				 new_matrix[i][j] = sum;
			 }
		 }
		 
		 return new_matrix;
	}
		 
	public static double value(double x) 
	{
		return Math.sqrt(x*x+2*x+4); // wzór funkcji
	}
		 
	public static double fi(double x, int y) 
	{
		 return Math.pow(x, y);
	}
		 
	public static double integerFunctionOfMatrix(int y, double x) 
	{
		 return weight * fi(x,y);
	}
	
	public static double integerFunctionOfResults(int y, double x) 
	{
		 return value(x) * weight * fi(x,y);
	}
		 
	public static double simpson_matrix(int y, int n, double a, double h, double b) 
	{
		 double sum_x = 0; 
		 double sum_t = 0;
		 
		 double x;
		 
		 for (int i = 1; i < n + 1; i++) 
		 {
			 x = a + i * h;
			 sum_t += integerFunctionOfMatrix(y, x - h / 2);
			 
			 if (i < n) 
			 {
				 sum_x += integerFunctionOfMatrix(y, x);
			 }
		 }
		 
		 return h / 6 * (integerFunctionOfMatrix(y, a) + integerFunctionOfMatrix(y, b) + 2 * sum_x + 4 * sum_t);		 
	}
		 
	public static double simpson_results(int y, int n, double h, double a, double b) 
	{
		 double sum_x = 0;
		 double sum_t = 0;
		 
		 double x;
		 
		 for (int i = 1; i < n + 1; i++) 
		 {
			 x = a + i * h;
			 sum_t += integerFunctionOfResults(y,x - h / 2);
			 
			 if (i < n) 
			 {
				 sum_x += integerFunctionOfResults(y,x);
			 }
		 }
		 
		 return h / 6 * (integerFunctionOfResults(y,a) + integerFunctionOfResults(y, b) + 2 * sum_x + 4 * sum_t);
	}

	public static double calculate(int n, double[][] matrix, double[][] results, double[][] c, double a, double h, double b, double z) 
	{
		 for (int i = 0; i < n; i++) 
		 {
			 results[i][0] = simpson_results(i, n, h, a, b);
			 
			 for (int j = 0; j < n; j++) 
			 {
				 matrix[i][j] = simpson_matrix(i + j, n, a, h, b);
			 }
		 }
		 
		 c = multiplication(reverseMatrix(matrix),results);
		 
		 double result = 0;
		 
		 for (int i = 0; i < n; i++) 
		 {
			 result += c[i][0] * Math.pow(z, i);
		 }
		 
		 return result;
	}
		 
	public static void main(String args[]) 
	{
		double a = -1;
		double b = 1;
		int n = 2;
		double h = (b - a) / n;
		double x = -0.25;
		 
		double[][] c = new double[n][1];
		double[][] matrix = new double[n][n];
		double[][] results = new double[n][1];
		 
		System.out.println("Przewidywany wynik: " + value(x));
		System.out.println("Ostateczny wynik: " + calculate(n, matrix, results, c, a, h, b, x));
	}
}
