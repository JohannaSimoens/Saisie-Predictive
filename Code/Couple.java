import java.util.HashMap;

/**
 * Classe qui prend deux paramètre un String et un entier, pour pouvoir 
 * implémenter nos algorithmes de tri 
 */
public class Couple implements Triable<Couple> {
	 /**
     * La mot du Couple.
     * @see	Couple#getMot()
     * @see Couple#setMot(String)
     */
	private String mot;
	 /**
     * L'occurence correspondant au bigramme ou trigramme.
     * @see	Couple#getOccurence()
     * @see Couple#setOccurence(int)
     */
	private int occurence;
	
        /**
         * Constructeur de Couple
         * @param mot
         * @param occurence 
         */
	public Couple(String mot, int occurence){
		this.mot = mot;
		this.occurence = occurence;
	}

	/**
	 * @param une HashMap<String, int>
	 * @return un tableau de Couple
	 */
	//fonction qui convertit une HashMap en tableau de Couple
	public static Couple[] convertirHashMapEnTab(HashMap<String, Integer> hm){
		Couple[] retour = new Couple[hm.size()];
		int i =0;
		//on parcours la map, et on met à l'indice i de notre tableau retour, un nouveau Couple 
		// qui prend un paramètre la clé (String) et la valeur(int)
		for(String s : hm.keySet()){
			retour[i] = new Couple(s,hm.get(s));
			i++;
			
		}
		return retour;
		
	}
        
        /**
         * Accesseur de Mot
         * @return mot
         */
	public String getMot() {
		return mot;
	}
        
        /**
         * Modifieur de mot
         * @param mot 
         */
	public void setMot(String mot) {
		this.mot = mot;
	}
        
        /**
         * Accesseur de occurence
         * @return occurence
         */
	public int getOccurence() {
		return occurence;
	}
               
      
        /**
         * Modifieur de occurence
         * @param occurence 
         */
	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}
	
        /**
         * Affichage
         * @return String
         */
	public String toString(){
		return mot+" "+occurence;
	}
	
	/**
	 * implemente CompareTo
	 * @return un entier, 1 si this est plus petit, -1 si this est plus grand 0 si ils sont égaux
	 * @param arg0
	 */
	public int compareTo(Couple arg0) {
		if(arg0.getOccurence()<this.getOccurence()) return 1;
		if(arg0.getOccurence()==this.getOccurence()) return 0;
		else return -1;
	}
	
	/**
	 * renvoie un clone
	 * @return le clone de this 
	 */
	public Couple clone(){
		return new Couple(this.mot, this.occurence);
	}
	
}
