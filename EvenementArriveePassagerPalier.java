public class EvenementArriveePassagerPalier extends Evenement {

    private Etage etageDeDepart;//etage de depart du guguss
    
    public EvenementArriveePassagerPalier(long d, Etage edd) {
        super(d);
        etageDeDepart = edd;
    }
    
    public void afficheDetails(Immeuble immeuble) {
        System.out.print("APP ");
        System.out.print(etageDeDepart.numero());
    }
    
    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        assert etageDeDepart != null;
	assert immeuble.etage(etageDeDepart.numero()) == etageDeDepart;
        Passager p = new Passager(date, etageDeDepart, immeuble);
        assert etageDeDepart == p.etageDepart();
	if ( etageDeDepart == immeuble.cabine.etage ) {
	    notYetImplemented();
	} else {
	    etageDeDepart.ajouter(p);
	}
	echeancier.ajouter(
          new EvenementArriveePassagerPalier(date + etageDeDepart.arriveeSuivant(),etageDeDepart));
    }
}
