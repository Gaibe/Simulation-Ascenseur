public class EvenementPassageCabinePalier extends Evenement {

    private Etage etage;

    public EvenementPassageCabinePalier(long d, Etage e) {
        super(d);
        etage = e;
    }

    public void afficheDetails(Immeuble immeuble) {
        System.out.print("PCP ");
        System.out.print(etage.numero());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;

        assert cabine.status() != '-';
        assert !cabine.porteOuverte;
        if (cabine.passagerDessent() == true){
        	if(cabine.regardePassager()){
        		EvenementOuverturePorteCabine opc = new EvenementOuverturePorteCabine(date+Constantes.tempsPourOuvrirOuFermerLesPortes);
        		echeancier.ajouter(opc);
        	}else{	
        	EvenementPassageCabinePalier pcp = new EvenementPassageCabinePalier(date+Constantes.tempsPourBougerLaCabineDUnEtage,etage.moins_un);
        	echeancier.ajouter(pcp);
        	cabine.etage=etage;
        	}
        }

        assert !cabine.porteOuverte;
        
    }
}
