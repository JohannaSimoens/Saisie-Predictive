import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Classe qui va créer un arbre de complétion à partir d'un corpus
 * 
 */
public class MiseEnPlaceArbre {
    
    /**
     * l'arbre
     */
    private Arbre arbre;
    
    /**
     * Constructeur de MiseEnPlaceArbre qui va créer l'arbre de completion
     * @param nomCorpus
     * @throws IOException 
     */
    public MiseEnPlaceArbre(String nomCorpus) throws IOException {
        
        HashMap tableHasMotsFreq = new HashMap();

        // On lit le corpus et on en fait une chaîne de caractères, sans balises, avec le bon nombre d'espace grâce à la classe LectureTexte
        LectureTexte lectureTexte = new LectureTexte(nomCorpus);

        // on récupère les mots et on les stocke dans un tableau
        String[] tabMots = lectureTexte.getTexteNettoye().split(" ");

        for (int i = 0; i < tabMots.length; i++) {

            // On regarde quels sont les mots avec, à la fin, une virgule,un point d'interrogation ou d'exclamation et on le/la retire
            if (tabMots[i].matches("[0-9a-zA-Zéèàûôêùâ/'/-]+[,/!/?]")) {
                tabMots[i] = tabMots[i].substring(0, tabMots[i].length() - 1);
            }

            // On retire les parenthèses ouvrantes
            if (tabMots[i].matches("[(][0-9a-zA-Zéèàûôêùâ/'/-]+")) {
                tabMots[i] = tabMots[i].substring(1);
            }

            // On retire les points, deux petits points, trois petits points, etc à la fin des mots
            if (tabMots[i].matches("[0-9a-zA-Zéèàûôêùâ/'/-]+[.]+")) {
                char c = tabMots[i].charAt(tabMots[i].length() - 1);
                while (c == '.') {
                    tabMots[i] = tabMots[i].substring(0, tabMots[i].length() - 1);
                    c = tabMots[i].charAt(tabMots[i].length() - 1);
                }
            }
        }

        // On compte les occurences des mots on on associe à une clef (le mot) l'occurrence (valeur) dans la table de haschage
        for (int i = 0; i < tabMots.length; i++) {
            if (tableHasMotsFreq.containsKey(tabMots[i])) {
                int freq = (int) tableHasMotsFreq.get(tabMots[i]) + 1;
                tableHasMotsFreq.put(tabMots[i], freq);
            } else {
                tableHasMotsFreq.put(tabMots[i], 1);
            }

        }

        // On crée l'arbre et on lui ajoute tous les mots qui sont dans la table de haschage
        arbre = new Arbre();

        Set cles = tableHasMotsFreq.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()) {
            Object cle = it.next();
            Object valeur = tableHasMotsFreq.get(cle);
            Mot mot = new Mot((String) cle, (int) valeur);
            arbre.ajouteMot(mot);
            
        }

    }
    
    /**
     * Renvoit une liste de propositions de mots complets triées par fréquence en fonction du début de mot entré en argument
     * @param debut
     * @return liste
     */
    public ListeMots listeTriéesDeMotsApartirDunDebutDeMot(String debut){
        ListeMots liste = new ListeMots();
        liste = this.arbre.completion(debut);
        liste.trier();        
        return liste;
    }
    
    /**
     * Accesseur du champ privé arbre
     * @return arbre
     */
    public Arbre getArbre(){
        return this.arbre;
    }
}
