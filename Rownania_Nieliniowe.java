
public class Rownania_Nieliniowe {

	public static void main(String[] args) 
	{
		double a = -3;
		double b = 5;
		
		System.out.println(Metoda.value(-1.76)+System.lineSeparator());
		System.out.println(Metoda.derivative_I(-0.92)+System.lineSeparator());
		
		
		Metoda m = new Metoda(a,b);
		
		// warunek konieczny
		if(!Metoda.warunek_konieczny(m.a, m.b))
			System.out.println("Warunek konieczny nie zosta³ spe³niony.");
		else
		{
			// metoda bisekcji
			double result = Metoda.metoda_bisekcji(m.a,m.b);
			System.out.println("Wynik metody bisekcji: " + result);
			System.out.println("Wartosc funkcji dla rozwi¹zania x: " + Metoda.value(result));
			System.out.println("Liczba iteracji: " + Metoda.iteracje + System.lineSeparator());
			
			Metoda.clear();
			
			// metoda stycznych
			result = Metoda.metoda_stycznych(m.a,m.b);
			System.out.println("Wynik metody stycznych: " + result);
			System.out.println("Wartosc funkcji dla rozwi¹zania x: " + Metoda.value(result));
			System.out.println("Liczba iteracji: " + Metoda.iteracje + System.lineSeparator());
			
			Metoda.clear();
			
			// metoda siecznych
			result = Metoda.metoda_siecznych(m.a,m.b);
			System.out.println("Wynik metody siecznych: " + result);
			System.out.println("Wartosc funkcji dla rozwi¹zania x: " + Metoda.value(result));
			System.out.println("Liczba iteracji: " + Metoda.iteracje + System.lineSeparator());
		}
	}
}
