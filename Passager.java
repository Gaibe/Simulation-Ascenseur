public class Passager {

    private long numeroDeCreation;

    private long dateDepart;

    private Etage etageDepart;

    private Etage etageDestination;

    public long dateDepart() {
        return this.dateDepart;
    }

    public Etage etageDepart() {
        return this.etageDepart;
    }

    public int numeroDepart() {
        return this.etageDepart.numero();
    }

    public Etage etageDestination() {
        return this.etageDestination;
    }

    public int numeroDestination() {
        return this.etageDestination.numero();
    }

    public Passager(long dateDeDepart, Etage etageDeDepart, Immeuble immeuble) {
        Etage niveauDuSol = immeuble.niveauDuSol();
        int nbEtages = immeuble.nbEtages();
        etageDepart = etageDeDepart;
        dateDepart = dateDeDepart;
        compteurGlobalDeCreationDesPassagers++;
        numeroDeCreation = compteurGlobalDeCreationDesPassagers;
        if (etageDepart == niveauDuSol) {
            etageDestination = niveauDuSol;
            while (etageDestination == niveauDuSol) {
                int auPif = randomGenerator.intSuivant(nbEtages);
                etageDestination = immeuble.etage(auPif + immeuble.etageLePlusBas().numero() - 1);
            }
        } else {
            etageDestination = niveauDuSol;
        }
    }

    public String toString() {
        return String.format("#%d:%d%c%d:%d",
                numeroDeCreation, etageDepart.numero(), sens(),
                etageDestination.numero(), dateDepart);
    }

    private static int compteurGlobalDeCreationDesPassagers = 0;

    private static final PressRandomNumberGenerator
            randomGenerator = new PressRandomNumberGenerator(34);

    public char sens() {
        if (etageDepart.numero() > etageDestination.numero()) {
            return 'v';
        } else {
            return '^';
        }
    }
}
