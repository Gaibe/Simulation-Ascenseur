
public class EvenementPassageCabinePalier extends Evenement {

	private Etage etage;

	public EvenementPassageCabinePalier(long d, Etage edd, Echeancier echeancier) {
		super(d + 2*tempsPourBougerLaCabineDUnEtage);
		etage = edd;
		echeancier.ajouter(this);
	}

	public void afficheDetails(Immeuble immeuble) {
		System.out.print("PCP ");
		System.out.print(etage.numero());
	}

		//Traitement de l'arrivee d'un passager sur le palier
	public void traiter(Immeuble immeuble, Echeancier echeancier) {
		assert immeuble.cabine.status != '-';
		immeuble.cabine.etage = etage;
		boolean arret_cabine = immeuble.cabine.etageArrive();
		//Verifification si l'etage est vide et la cabine non arretee
		if(!etage.recupPassager() && !arret_cabine){
			//Verificaiton du statut du palier et ajout de l'evenement passage cabine
			if(immeuble.cabine.status == 'v'){
				new EvenementPassageCabinePalier(this.date, immeuble.etage(immeuble.cabine.numeroEtage()-1), echeancier);
			}else if(immeuble.cabine.status == '^'){
				new EvenementPassageCabinePalier(this.date, immeuble.etage(immeuble.cabine.numeroEtage()+1), echeancier);
			}
		}else{
			//Sinon ouverture des portes
			new EvenementOuverturePorteCabine(date, etage, echeancier);
		}
		immeuble.cabine.etage = this.etage;
		assert !immeuble.cabine.porteOuverte;
	}
}

	


