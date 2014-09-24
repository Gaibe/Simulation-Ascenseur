import java.util.ArrayList;

public class Etage extends Constantes {
    private int numero; // de l'Etage pour l'usager
    
    private Immeuble immeuble; // de l'Etage
    
    private LoiDePoisson poissonFrequenceArrivee; // dans l'Etage
    
    private ArrayList<Passager> listePassagersEtage = new ArrayList<Passager>();

    public Etage plus_un;

    public Etage moins_un;

    public Etage(int n, int fa, Immeuble im) {
        numero = n;
        immeuble = im;
        int germe = n << 2;
        if (germe <= 0) {
            germe = -germe + 1;
        }
        poissonFrequenceArrivee = new LoiDePoisson(germe, fa);
    }
    
    public void afficheLaSituation() {
        if (numero() >= 0) {
            System.out.print(' ');
        }
        System.out.print(numero());
        if (this == immeuble.cabine.etage) {
            System.out.print(" C ");
            if (immeuble.cabine.porteOuverte) {
                System.out.print("[  ]: ");
            } else {
                System.out.print(" [] : ");
            }
        } else {
            System.out.print("   ");
            System.out.print(" [] : ");
        }
        int i = 0;
        boolean stop = listePassagersEtage.size() == 0;
        while (!stop) {
            if (i >= listePassagersEtage.size()) {
                stop = true;
            } else if (i > 6) {
                stop = true;
                System.out.print("...(");
                System.out.print(listePassagersEtage.size());
                System.out.print(')');
            } else { 
                System.out.print(listePassagersEtage.get(i));
                i++;
                if (i < listePassagersEtage.size()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.print('\n');
    }

    public int numero() {
        // En Eiffel il est possible de mettre celle qui suit:
        // assert immeuble.recalcule_numero(this) == this.numero;
        return this.numero;
    }

    public void ajouter(Passager passager) {
        listePassagersEtage.add(passager);
    }

    public long arriveeSuivant() {
        return poissonFrequenceArrivee.suivant();
    }
    public boolean veutEntrer (char x){
    	boolean veutEntrer = false;
    	if (listePassagersEtage.isEmpty()){
    		veutEntrer=false;
    	}else{
    		
    		
    		for (Passager lpe : this.listePassagersEtage) {
				
			
    			if (lpe.sens() == x){
    				veutEntrer = true;
    			}
    		}
    	}
    	return veutEntrer;
    	
    }
    

}
