import java.util.ArrayList;
import java.util.Collections;

/**
 * Liste de Mots 
 */
public class ListeMots extends ArrayList<Mot> { 
    
    /**
     * trie la liste de mots du plus fréquent au moins fréquent
     */
    public void trier(){
        Collections.sort(this);        
    }
    
}
