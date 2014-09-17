public class EvenementFermeturePorteCabine extends Evenement {
    
    public EvenementFermeturePorteCabine(long d) {
        super(d);
    }
    
    public void afficheDetails(Immeuble immeuble) {
        System.out.print("FPC");
    }
    
    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;

        assert cabine.porteOuverte;
	notYetImplemented();
        assert ! cabine.porteOuverte;
    }
}
