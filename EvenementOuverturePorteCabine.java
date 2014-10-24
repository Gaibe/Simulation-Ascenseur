
public class EvenementOuverturePorteCabine extends Evenement {

	private Etage etage;

	public EvenementOuverturePorteCabine(long d,Etage et,Echeancier echeancier) {
		super(d + tempsPourOuvrirOuFermerLesPortes);
		etage = et;
		echeancier.ajouter(this);
	}

	public void afficheDetails(Immeuble immeuble) {
		System.out.print("OPC, ");
		System.out.print(etage.numero());
	}

	public void traiter(Immeuble immeuble, Echeancier echeancier) {
		assert immeuble.cabine.status != '-'; // La cabine est à l'arret

		assert !immeuble.cabine.porteOuverte; // Les portes s'ouvrent

		immeuble.cabine.porteOuverte = true;
		//Verification si la cabine est vide alors, sortie des passagers
		if(!immeuble.cabine.isVide()){
			immeuble.cabine.sortieDesPassagers(date, immeuble);
		}
		//remplissage de passages qui ont appelés la cabine
		etage.remplissageDeLaCabine(this.date,immeuble);
		if(immeuble.cabine.isVide() && etage.isVide()){
		//Non aret de la cabine si la cabine est vide et l'étage est vide
			immeuble.appelCabine(etage.numero());
		}
		new EvenementFermeturePorteCabine(this.date, this.etage, echeancier);
	}
}

