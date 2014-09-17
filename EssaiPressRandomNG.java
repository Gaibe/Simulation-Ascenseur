
public class EssaiPressRandomNG { 
    public static void main(String args[]) {
	PressRandomNumberGenerator random = new PressRandomNumberGenerator(80);

	// Tirage aleatoire de double plus petits que 1.0
	int i = 10;
	while ( i > 0 ) {
	    System.out.println(random.doubleSuivant());
	    i--;
	}

	// Tirage aleatoire de int dans l'intervalle [1 .. 50]
	i = 50;
	while ( i > 0 ) {
	    System.out.println(random.intSuivant(50));
	    i--;
	}

    }
}

