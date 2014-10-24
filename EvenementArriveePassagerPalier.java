public class EvenementArriveePassagerPalier extends Evenement {

	private Etage etageDeDepart;

	public EvenementArriveePassagerPalier(long d, Etage edd, Echeancier echeancier) {
		super(d + edd.arriveeSuivant());
		etageDeDepart = edd;
		echeancier.ajouter(this);
	}

	public void afficheDetails(Immeuble immeuble) {
		System.out.print("APP ");
		System.out.print(etageDeDepart.numero());
	}

	//Traitement de l'arrivee d'un passage sur le palier
	public void traiter(Immeuble immeuble, Echeancier echeancier) {
		
		
		immeuble.etage(immeuble.numeroDe(etageDeDepart)).ajouter(new Passager(this.date, this.etageDeDepart,immeuble));
		//verification du statut de la cabine si la cabine n'est pas en deplacement.
		if(immeuble.cabine.status == '-'){
			int e;
			//Verification du numero de la cabine
			if(immeuble.cabine.etage.numero()>etageDeDepart.numero()){
				immeuble.cabine.status='v';
				e=immeuble.cabine.etage.numero()-1;
			}else{
				immeuble.cabine.status='^';
				e=immeuble.cabine.etage.numero()+1;
			}
			
			if(immeuble.cabine.getNbOccupants() <= nombreDePlacesDansLaCabine){
				immeuble.etage(immeuble.numeroDe(etageDeDepart)).ajouter(new Passager(this.date, this.etageDeDepart,immeuble));
        		date = date + Constantes.tempsPourEntrerOuSortirDeLaCabine;
        	}
			
			new EvenementPassageCabinePalier(this.date,immeuble.etage(e), echeancier);
		}
		new EvenementArriveePassagerPalier(this.date + etageDeDepart.arriveeSuivant(), this.etageDeDepart, echeancier);
	}
}
