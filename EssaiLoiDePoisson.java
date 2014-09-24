public class EssaiLoiDePoisson { 
    public static void main(String args[]) {
	LoiDePoisson loiDePoisson = new LoiDePoisson(10, 30);

	int i = 1000;
	while ( i > 0 ) {
	    System.out.println(loiDePoisson.suivant());
	    i--;
	}
    }
}