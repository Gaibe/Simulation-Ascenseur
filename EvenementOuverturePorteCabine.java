public class EvenementOuverturePorteCabine extends Evenement {

    public EvenementOuverturePorteCabine(long d) {
        super(d);
    }

    public void afficheDetails(Immeuble immeuble) {
        System.out.print("OPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;
        Etage etage = cabine.etage;

        assert cabine.status() != '-';
        assert !cabine.porteOuverte;
        cabine.arreterCabine();
        assert cabine.porteOuverte;
    }

}
