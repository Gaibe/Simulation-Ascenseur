public class Immeuble extends Constantes {

    private Etage[] tableauDesEtages;

    public Cabine cabine; // de l'ascenseur.

    private Etage niveauDuSol; // le niveau 0.

    public long cumulDesTempsDeTransport = 0;

    public long nombreTotalDesPassagersSortis = 0;

    public Etage etageLePlusBas() {
	Etage res = tableauDesEtages[0];
        assert res != null;
	assert res.moins_un == null;
        return res ;
    }

    public Etage etageLePlusHaut() {
	Etage res = tableauDesEtages[tableauDesEtages.length - 1];
        assert res != null;
	assert res.plus_un == null;
        return res;
    }

    public Etage niveauDuSol() {
        assert etageLePlusHaut().numero() >= niveauDuSol.numero();
        assert etageLePlusBas().numero() <= niveauDuSol.numero();
        return niveauDuSol;
    }

    public Immeuble(Echeancier echeancier) {
	Etage e = null;
	Etage e_moins_un = null;
        tableauDesEtages = new Etage[8];
        int n = -1;
        for (int i = 0; i < tableauDesEtages.length; i++) {
            // Une personnne toutes les 3 secondes:
            int fa = 30;
            if (n != 0) {
                fa = fa * (tableauDesEtages.length - 1);
            }
	    e_moins_un = e;
	    e = new Etage(n, fa, this);
	    tableauDesEtages[i] = e;
	    e.moins_un = e_moins_un;
            if (n == 0) {
                niveauDuSol = e;
            }
            n++;
        }
        for (int i = tableauDesEtages.length - 1; i > 0; i--) {
	    tableauDesEtages[i - 1].plus_un = tableauDesEtages[i];
	}
        for (int i = 0; i < tableauDesEtages.length; i++) {
            Etage etage = tableauDesEtages[i];
            long date = etage.arriveeSuivant();
            echeancier.ajouter(new EvenementArriveePassagerPalier(date, etage));
        }
	Etage ed = etageLePlusHaut();
        cabine = new Cabine(ed);
	echeancier.ajouter(new EvenementPassageCabinePalier(tempsPourBougerLaCabineDUnEtage*2, etage(ed.numero() - 1)));
    }

    public void afficheLaSituation() {
        System.out.print("Immeuble (mode ");
        if (isModeParfait()) {
            System.out.print("parfait");
        } else {
            System.out.print("infernal");
        }
        System.out.println("):");
        int i = etageLePlusHaut().numero();
        while (i >= etageLePlusBas().numero()) {
            etage(i).afficheLaSituation();
            i--;
        }
        cabine.afficheLaSituation();
        System.out.println("Cumul des temps de transport: " + cumulDesTempsDeTransport);
        System.out.println("Nombre total des passagers sortis: " + nombreTotalDesPassagersSortis);
        System.out.println("Ratio cumul temps / nb passagers : " +
                (nombreTotalDesPassagersSortis == 0 ? 0 : (cumulDesTempsDeTransport / nombreTotalDesPassagersSortis)));
    }

    public Etage etage(int i) {
        // Retrouve par calcul (assez lent) un Etage avec son numero.
        assert etageLePlusBas().numero() <= i : "ERREUR trop haut";
        assert etageLePlusHaut().numero() >= i : "ERREUR trop bas";
        Etage res = tableauDesEtages[i - etageLePlusBas().numero()];
        assert res.numero() == i;
        return res;
    }

    public int nbEtages() {
        int res = tableauDesEtages.length;
        assert res == (etageLePlusHaut().numero() - etageLePlusBas().numero() + 1);
        return res;
    }
}
