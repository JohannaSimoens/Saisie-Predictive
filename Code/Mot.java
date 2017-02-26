
/**
 * Une instance de la classe mot est constituée de la chaîne de caractères (le mot) et de sa fréquence
 * 
 */
public class Mot implements Comparable {
    
     
    /**
     * La chaîne de caractères est final car elle ne change jamais 
     */
    private final String mot;
    
    /**
     * la frequence du mot, elle peut changer  
     */
    private int frequence;
       
    
    /**
     * Constructeur de Mot
     * @param mot
     * @param frequence 
     */   
    public Mot(String mot, int frequence){
        this.mot= mot;
        this.frequence = frequence;
    } 
    
    /**
     * Modifieur de fréquence
     * @param frequence 
     */
    public void setFrequence(int frequence){
        this.frequence = frequence;
    }
    
    /**
     * Accesseur de mot
     * @return mot
     */
    public String getMot(){
       return mot; 
    }
    
    /**
     * Accesseur de fréquence
     * @return frequence
     */
    public int getFrequence(){
       return frequence; 
    }

    /**
     * Comparer deux mots selon leur fréquence, permet d'utiliser la fonction "sort" sur une liste 
     * de Mots
     * @param m le mot qu'on compare avec le mot courant
     * @return 
     */
    public int compareTo(Object m) {
        Mot leMot = (Mot)m; 
        if(this.getFrequence() < leMot.getFrequence()){
            return 1 ;            
        }
        if(this.getFrequence() > leMot.getFrequence()){
            return -1;            
        }
        if(this.getFrequence() == leMot.getFrequence()){
            return 0;            
        }
        return 0;                
    }
    
 
}
