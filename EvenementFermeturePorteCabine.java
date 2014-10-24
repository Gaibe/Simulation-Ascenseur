public class EvenementFermeturePorteCabine extends Evenement {

	Etage etage;

	public EvenementFermeturePorteCabine(long d, Etage et, Echeancier echeancier) {
		super(d + tempsPourOuvrirOuFermerLesPortes);
		etage = et;
		echeancier.ajouter(this);		
	}

	public void afficheDetails(Immeuble immeuble) {
		System.out.print("FPC, ");
		System.out.print(etage.numero());
	}

	//Traitement de la fermeture des portes
	public void traiter(Immeuble immeuble, Echeancier echeancier) {
		assert immeuble.cabine.porteOuverte;
		assert immeuble.cabine.status != '-';
		immeuble.cabine.porteOuverte = false;
		assert !immeuble.cabine.porteOuverte;
		if(immeuble.cabine.status == 'v'){//Verification du sens de d�placement de la cabine
		//Ajout dans l'�ch�ancier
			new EvenementPassageCabinePalier(date, immeuble.etage(immeuble.cabine.numeroEtage()-1), echeancier);
		}else{
			new EvenementPassageCabinePalier(date, immeuble.etage(immeuble.cabine.numeroEtage()+1), echeancier);
		}
	}
}

