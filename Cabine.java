public class Cabine extends Constantes {

    public Etage etage;

    public boolean porteOuverte;
    
    private char status; // '-' ou 'v' ou '^'

    private Passager[] tableauPassager;
    
    public Cabine(Etage e) {
        etage = e;
	tableauPassager = new Passager[nombreDePlacesDansLaCabine];
	porteOuverte = false;
	status = 'v';
    }
    
    public void setStatus(char s){
    	this.status = s;
    }
    
    public void afficheLaSituation() {
        System.out.print("Contenu de la cabine: ");
        for (int i = 0; i < tableauPassager.length; i++) {
            Passager p = tableauPassager[i];
            if (p != null) {
                System.out.print(p + " ");
            }
        }
	assert (status == 'v') || (status == '^') || (status == '-');
        System.out.println("\nPriorité de la cabine: " + status);
    }
    
    public boolean transporte(Passager p) {
        // Pour savoir si le passager p est dans la Cabine. Pour utilisation dans 
	// les assert.
	assert p != null;
	for (int i = 0 ; i < tableauPassager.length ; i ++) {
            if (tableauPassager[i] == p) {
                return true;
            }
	    }
        return false;
    }

    public char status() {
	assert (status == 'v') || (status == '^') || (status == '-');
        return status;
    }
    
    public boolean passagerArriveeCabine(){
    	notYetImplemented();
    	return true;
    }
    public boolean passagerDessent(){
    	if (status =='v'){
    		return true;
 
    	}else return false;
    }
    
    public boolean estArrete(){
    	boolean ret=false;
    	if(status=='-'){
    		ret=true;
    	}
    	return ret;
    }
    
    public boolean regardePassager(){
    	
    	boolean entrer = etage.veutEntrer(status);
    	if(entrer && !estArrete()){
    		setStatus('-');
    	}
    	return entrer;
    }
    public void MonterCabine(){
    	
    }
}
