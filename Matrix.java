
public class Matrix 
{
	double[][] tab;
    String[] text;
    int lenght;

    public Matrix(int lenght) 
    {
        this.lenght = lenght;
        this.tab = new double[lenght+2][lenght+3];
        this.text = new String[lenght+2];
    }

    public void solveGauss() 
    {
        for (int i = 0, j = 0; i < tab.length; i++, j++) 
        {
            if (tab[i][j] != 1) 
            {
                double division = tab[i][j];
                for (int k = 0; k < tab[i].length; k++) 
                {
                    tab[i][k] /= division;
                }
            }
            for (int k = i + 1; k < tab.length; k++) 
            {
                if (tab[k][j] != 0) 
                {
                    double reverse = -tab[k][j];
                    for (int l = 0; l < tab[k].length; l++) 
                    {
                        tab[k][l] += tab[i][l] * reverse;
                    }
                }
            }
            for (int k = i - 1; k >= 0; k--) 
            {
                if (tab[k][j] != 0) 
                {
                    double reverse = -tab[k][j];
                    for (int l = 0; l < tab[k].length; l++) 
                    {
                        tab[k][l] += tab[i][l] * reverse;
                    }
                }
            }
        }
    }

    @Override
    public String toString() 
    {
        String temp = "";
        for (int i = 0; i < lenght+2; i++) 
        {
            temp += "|";
            for (int j = 0; j < lenght+2; j++) 
            {
                temp += (int)tab[i][j] + "\t";
            }
            temp += "|" + (float)tab[i][tab.length] + "|" + System.lineSeparator();
        }
        
        return temp;
    }
}
