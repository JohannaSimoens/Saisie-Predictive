import java.util.Comparator;
import java.util.HashMap;

/**
 * Class Comparateur de valeur qui implémente Comparator pour pouvoir trier une HashMap par valeurs
 */
public class ComparateurValeur implements Comparator<String> {   
    
    HashMap<String, Integer> temp;
    
    public ComparateurValeur(HashMap<String, Integer> temp) {
        this.temp = temp;
    }

    public int compare(String a, String b) {
        if (temp.get(a) >= temp.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }

}
