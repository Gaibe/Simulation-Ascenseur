public class EssaiLoiDePoisson { 
    public static void main(String args[]) {
	LoiDePoisson loiDePoisson = new LoiDePoisson(7, 25);

	int i = 100;
	while ( i > 0 ) {
	    System.out.println(loiDePoisson.suivant());
	    i--;
	}
    }
}

