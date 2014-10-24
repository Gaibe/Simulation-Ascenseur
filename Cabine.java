
public class Cabine {

	public Etage etage;

	public boolean porteOuverte;

	public char status = '-'; 

	private Passager[] tableauPassager = new Passager[8];

	public Cabine(Etage e){
		etage = e;
		porteOuverte = false;
	}

	public void afficheLaSituation(){
		System.out.print("Contenu de la cabine:");
		for(int i = 0; i < tableauPassager.length; i++) {
			Passager p = tableauPassager[i];
			if ( p != null ) {
				p.afficher();
			}
		}
		System.out.println();
	}

	public boolean ajouterPassager (Passager passager) {
		assert passager!=null;
		if(tableauPassager.length < Constantes.nombreDePlacesDansLaCabine){
			for (int i = 0; i < tableauPassager.length; i ++) {
				if(tableauPassager[i] == null){
					tableauPassager[i] = passager;
					return true;
				}
			}
		}
		return false;
	}


	public boolean porteOuverte(){
		return porteOuverte;
	}

	public int numeroEtage () {
		return etage.numero();
	}

	public Passager[] getTableauPassager() {
		return tableauPassager;
	}
	
	public boolean etageArrive(){
		for(int j=0; j<tableauPassager.length; j++){
			if(tableauPassager[j] != null){
				if(tableauPassager[j].etageDestination() == etage){
					return true;
				}
			}
		}
		return false;
	}

	//remplissage du tableau
	public int Remplissage(Passager[]p){
		assert p != null;
		int i;
		int compteur = 0;
		for(i=0; i<p.length; i++){
			if(p[i]!=null){
				compteur++;
			}
		}
		return compteur;
	}

	public boolean isVide(){
		if(Remplissage(tableauPassager)==0){
			return true;
		}else{
			return false;
		}
	}
	//Sortie des passagers
	public int sortieDesPassagers(long date,Immeuble im){
		int tempsDeSortie=0;
		//pour tous les passagers dans la cabine
		for(int i = 0; i < tableauPassager.length; i++) {
			Passager p = tableauPassager[i];
			//Si le passager existe
			if ( p != null ) {
				//Verification de la destination
				if(p.etageDestination()==etage){
					//ajout des temps de transports
					im.cumulDesTempsDeTransport=im.cumulDesTempsDeTransport+Constantes.tempsPourEntrerOuSortirDeLaCabine+(date-p.dateDepart());
					tableauPassager[i]=null;
					tempsDeSortie++;
					//incrementation du nombre total de passagers
					im.nombreTotalDesPassagersSortis ++;
				}
			}
		}
		return tempsDeSortie;
	}
	
	
	public int getNbOccupants() {
		int i = 0;
		for(Passager p : tableauPassager){
			if(p!=null){
				i++;
			}
		}
		return i;
	}
	
	
}
