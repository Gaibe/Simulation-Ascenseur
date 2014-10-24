
import java.util.ArrayList;

public class Etage {
    
    private Immeuble immeuble;
    
    private int frequenceArrivee;
    
    private LoiDePoisson poissonFrequenceArrivee;
    
    private ArrayList<Passager> listePassagersEtage = new ArrayList<Passager>();
    
    public Etage plus_un;

	public Etage moins_un;
    
    public Etage (int fa, Immeuble im) {
	immeuble = im;
	frequenceArrivee = fa;
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
                listePassagersEtage.get(i).afficher();
                i++;
                if (i < listePassagersEtage.size()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.print('\n');
    }
	
    public int numero () {
	return immeuble.numeroDe(this);
    }
    
    public void ajouter (Passager passager) {
    	assert(passager!=null);
	listePassagersEtage.add(passager);
    }
    
    public boolean isVide () {
	return (listePassagersEtage.size() == 0);
    }
    
    //Fonction qui renvoi le prochain arrive
    public long arriveeSuivant () {
	int germe = numero() << 2;
	if (germe <= 0) {
		germe = -germe + 1;
	}
    poissonFrequenceArrivee = new LoiDePoisson(germe,frequenceArrivee);
	
	
	return poissonFrequenceArrivee.suivant();
    }
    
    public Passager getPassager(){
    	return listePassagersEtage.get(0);
    }
    
    public void suppPassager(){
    	listePassagersEtage.remove(0);
    }
    
    public boolean recupPassager(){
    	for(int i=0; i<listePassagersEtage.size(); i++){
    		if(listePassagersEtage.get(i).sens()==immeuble.cabine.status || immeuble.cabine.isVide()){
    			return true;
    		}
    	}
    	return false;
    }
   
   //Fonction de remplissage de la cabine
	public void remplissageDeLaCabine(long date,Immeuble im){
    	int i;
    	ArrayList<Passager> vider = new ArrayList <Passager>();
    	for(i=0; i<listePassagersEtage.size(); i++){
		//Test du sens de l'ascenseur
    		if(Constantes.isModeParfait()){
	    		if (((listePassagersEtage.get(i).numeroDestination() < this.numero()) && (immeuble.cabine.status == 'v')) || ((listePassagersEtage.get(i).numeroDestination() > this.numero()) && (immeuble.cabine.status == '^')) || (immeuble.cabine.isVide())){
	    			if(immeuble.cabine.isVide()){ //Verification de la composition de la cabine.
	    				immeuble.cabine.ajouterPassager(listePassagersEtage.get(i)); 
	    				vider.add(this.listePassagersEtage.get(i));
						//Changement de status de la cabine
	    				if(listePassagersEtage.get(i).numeroDestination() < this.numero()){
	    					immeuble.cabine.status = 'v';
	    				}else if(listePassagersEtage.get(i).numeroDestination() > this.numero()){
	    					immeuble.cabine.status = '^';
	    				}
					//Sinon, ajouter un passager
					//Vider la liste des passagers
	    			}else{
	    				immeuble.cabine.ajouterPassager(listePassagersEtage.get(i));
	    				vider.add(this.listePassagersEtage.get(i));
	    			}
	    		}
    		}
    		else{
    			if (listePassagersEtage.get(i).numeroDestination() < this.numero() || listePassagersEtage.get(i).numeroDestination() > this.numero() || (immeuble.cabine.isVide())){
	    			if(immeuble.cabine.isVide()){ //Verification de la composition de la cabine.
	    				immeuble.cabine.ajouterPassager(listePassagersEtage.get(i)); 
	    				vider.add(this.listePassagersEtage.get(i));
						//Changement de status de la cabine
	    				if(listePassagersEtage.get(i).numeroDestination() < this.numero()){
	    					immeuble.cabine.status = 'v';
	    				}else if(listePassagersEtage.get(i).numeroDestination() > this.numero()){
	    					immeuble.cabine.status = '^';
	    				}
					//Sinon, ajouter un passager
					//Vider la liste des passagers
	    			}else{
	    				immeuble.cabine.ajouterPassager(listePassagersEtage.get(i));
	    				vider.add(this.listePassagersEtage.get(i));
	    			}
    			}
    		}
    	}
    	System.out.println(vider);
    	if(vider!=null){
    		int j;
    		int k;
    		for(j=0; j<vider.size(); j++){
    			for(k=0; k<this.listePassagersEtage.size(); k++){
    				if(vider.get(j)== this.listePassagersEtage.get(k)){
    					this.listePassagersEtage.remove(k);
    				}
    			}
    		}
    	}
    }
}
