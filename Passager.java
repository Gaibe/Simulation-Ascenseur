public class Passager {
    
    private int numeroDeCreation;
    
    private long dateDepart;
    
    private Etage etageDepart;
    
    private Etage etageDestination;	
    
    public long dateDepart() {
	return this.dateDepart;
    }
    
    public Etage etageDepart() {
	return this.etageDepart;
    }
    
    public int numeroDepart() {
	return this.etageDepart.numero();
    }
    
    public Etage etageDestination() {
	return this.etageDestination;
    }
    
    public int numeroDestination() {
	return this.etageDestination.numero();
    }
    
	
    public Passager (long dateDeDepart, Etage etageDeDepart, Immeuble immeuble) {
	Etage niveauDuSol = immeuble.getNiveauDuSol();
	int nbEtages = immeuble.getEtageMax() - immeuble.getEtageMin() + 1;
	etageDepart = etageDeDepart;
	dateDepart = dateDeDepart;
	compteurGlobalDeCreationDesPassagers++;
	numeroDeCreation = compteurGlobalDeCreationDesPassagers;
	//VErification de la hauteur de la cabine
	if ( etageDepart == niveauDuSol ) {
	    etageDestination = niveauDuSol;
	    while ( etageDestination == niveauDuSol ) {
		int auPif = randomGenerator.intSuivant(nbEtages);
		etageDestination = immeuble.etage(auPif + immeuble.getEtageMin() - 1);
	    }
	} else {
	    etageDestination = niveauDuSol;
	}
    }
    
	//Affichage du passager
    public void afficher() {
	System.out.print('#');
	System.out.print(numeroDeCreation);
	System.out.print(':');
	int numeroDepart = etageDepart.numero();
	int numeroDestination = etageDestination.numero();
	System.out.print(numeroDepart);
	if (numeroDepart > numeroDestination) {
	    System.out.print('v');
	}
	else {
	    System.out.print('^');
	}
	System.out.print(numeroDestination);
	System.out.print(':');
	System.out.print(dateDepart);
    }
    
    public char sens() {
        if (etageDepart.numero() > etageDestination.numero()) {
            return 'v';
        } else {
            return '^';
        }
    }
    
    private static int compteurGlobalDeCreationDesPassagers = 0;
    
    private static final PressRandomNumberGenerator 
	randomGenerator	= new PressRandomNumberGenerator(34);
    
}
