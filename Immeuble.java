public class Immeuble extends Constantes {
    
    public Cabine cabine;
    // La `cabine' de l'ascenseur.
    
    private int niveauDuSol;
    // le niveau 0 en general.
    
    private  int etageMin;
    // numero de l'etage le plus bas.
    
    private Etage[] tableauDesEtages;
    // Le tableau des etages desservis par l'ascenseur. 
        
    public long cumulDesTempsDeTransport = 0;
    
    public int nombreTotalDesPassagersSortis = 0;
    
    public Immeuble (Echeancier echeancier) {
		int i = 0;
		
		etageMin = -1; // Numero de l'etage inferieur (le plus bas)
		tableauDesEtages = new Etage[8];
		
		if ( getEtageMin() > 0 ) {
		    niveauDuSol = getEtageMin();
		}
		int fa;
		
		i = getEtageMax();
		while (	i >= getEtageMin() ) {
		    // Une personnne toutes les 3 secondes:
		    fa = 30;
		    if ( i != niveauDuSol ) {
			fa = fa * (tableauDesEtages.length - 1);
		    }
		    changerEtage(new Etage(fa, this), i);
		    i = i - 1;
		}
		cabine = new Cabine(etage(this.getEtageMax()));
		// Initialisation des premiers EvenementArrivee a chaque etage:
		
		for (int j=getEtageMax(); j >= getEtageMin(); j--){
		    new EvenementArriveePassagerPalier(etage(j).arriveeSuivant(), etage(j), echeancier);
		}
		
		new EvenementPassageCabinePalier(0, etage(getEtageMax() - 1), echeancier);
    }
    
    public int numeroDe(Etage e){
    	int res = getEtageMin();
    	while (e != etage(res)){
	    res ++;
    	}
    	return res;
    }
    
    public void afficheLaSituation () {
	System.out.print("Immeuble (mode ");
	if ( Constantes.isModeParfait() ) {
	    System.out.print("parfait");
	}
	else {
	    System.out.print("infernal");
	}
	System.out.println("):");
	int i = getEtageMax();
	while ( i >= getEtageMin() ) {
	    etage(i).afficheLaSituation();
	    i--;
	}
	cabine.afficheLaSituation();
	System.out.print("Cumul des temps de transport: ");
	System.out.println(cumulDesTempsDeTransport);
	System.out.print("Nombre total des passagers sortis: ");
	System.out.println(nombreTotalDesPassagersSortis);
	
    }
    
    public Etage etage (int i) {
    	 
    	Etage ret = tableauDesEtages[ i - getEtageMin() ];
    	assert ret != null;
    	return ret;
    }
    
    private void changerEtage (Etage e, int i) {
    	tableauDesEtages[ i - getEtageMin() ] = e;
    }
    

    
    public Etage getNiveauDuSol(){
    	return etage(niveauDuSol);
    }
    public int getEtageMin() {
	return etageMin;
    }
    
    public int getEtageMax() {
	return etageMin + tableauDesEtages.length - 1;
    }
	//Fonction d'appel de la cabine
    public void appelCabine(int numEtage){
		Passager tmp;
		Passager prio = null;
		for(int j=0; j<7; j++){
		//pour 7 etages
			if(!tableauDesEtages[j].isVide()){
			//VErification du vide de la cabine
				tmp=tableauDesEtages[j].getPassager();
				//Gestion de la priorite
				if(prio==null){
					prio=tmp;
				}else{
					if(tmp.dateDepart()<prio.dateDepart()){
						prio=tmp;
					}
				}
			}
		}
		//Changement de status de la cabine
		if(numEtage>prio.etageDepart().numero()){
			cabine.status='v';
		}else{
			cabine.status='^';
		} 
    }
}
