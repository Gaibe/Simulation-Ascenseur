import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main { // Programme de simulation d'un ascensseur
    
    public static void main(String args[]) {
	
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Quel mode de simulation voulez-vous ?\n(1) parfait \n(2) infernal");

        boolean mode_parfait = true;
        try {
            mode_parfait = !input.readLine().equals("2");
        } catch (Exception e) {
        }
        Constantes.setModeParfait(mode_parfait);

        Echeancier echeancier = new Echeancier();
        Immeuble immeuble = new Immeuble(echeancier);
        int loop = 1;
        int simulationStepCounter = 0;
        // Boucle principale du simulateur:
        while ( !echeancier.estVide() ) {
            if (loop == 1) {
                System.out.print("----- Etat actuel du simulateur (nombre total de pas = ");
                System.out.print(simulationStepCounter);
                System.out.println(") -----");
                immeuble.afficheLaSituation();
                echeancier.afficheLaSituation(immeuble);
                System.out.println("Taper \"Enter\" ou le nombre de pas de simulation que vous voulez faire");
                try {
		    String line = input.readLine();
		    if ( line.equals("q") ) {
			return;
		    } else {
			loop = Integer.parseInt(line);
			if (loop < 0) {
			    return;
			}
		    }
		} catch (Exception e) {
                    loop = 1;
                }
            } else {
                loop--;
            }
            Evenement evenement = echeancier.retourneEtEnlevePremier();
            assert pasDeRetourDansLeFutur(evenement.date) : "Retour dans le passé:" + memoDate + "/" + evenement.date;
            evenement.traiter(immeuble, echeancier);
            simulationStepCounter++;
        }
        System.out.println("********** SIMULATION TERMINEE **********");
    }

    private static long memoDate = -1;

    private static boolean pasDeRetourDansLeFutur(long nouvelleDate) {
        if (nouvelleDate >= memoDate) {
            memoDate = nouvelleDate;
            return true;
        } else {
            return false;
        }
    }


}
