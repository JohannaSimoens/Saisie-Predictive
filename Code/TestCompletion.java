import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Tests de la fonction de complétion
 */
public class TestCompletion {
    
    public static void main (String args[]) throws FileNotFoundException, IOException{     
        
        HashMap tableHasMotsFreq = new HashMap();
        
        // On lit le corpus et on en fait une chaîne de caractère sans balises avec le bon nombre d'espace grâce à la classe LectureTexte
        LectureTexte lectureTexte = new LectureTexte("corpusSMS.txt");
        
        // on récupère les mots et on les stocke dans un tableau
        String[] tabMots = lectureTexte.getTexteNettoye().split(" ");
        
        
        for (int i=0;i<tabMots.length;i++){
            
            // On regarde quels sont les mots avec, à la fin, une virgule,un point d'interrogation ou d'exclamation et on le/la retire
            if(tabMots[i].matches("[0-9a-zA-Zéèàûôêùâ/'/-]+[,/!/?]")){                 
                tabMots[i]=tabMots[i].substring(0,tabMots[i].length()-1);                
            }
            
            // On retire les parenthèses ouvrantes
            if(tabMots[i].matches("[(][0-9a-zA-Zéèàûôêùâ/'/-]+")){                
                 tabMots[i] = tabMots[i].substring(1);                
            }
            
            // On retire les points, deux petits points, trois petits points, etc à la fin des mots
            if(tabMots[i].matches("[0-9a-zA-Zéèàûôêùâ/'/-]+[.]+")){                
                char c = tabMots[i].charAt(tabMots[i].length()-1);
                while (c=='.'){
                    tabMots[i] = tabMots[i].substring(0,tabMots[i].length()-1);
                    c = tabMots[i].charAt(tabMots[i].length()-1);
                }              
            } 
        }
       
        
        // On compte les occurences des mots on on associe à une clef (le mot) l'occurrence (valeur) dans la table de haschage
        for(int i=0;i<tabMots.length;i++){            
            if (tableHasMotsFreq.containsKey(tabMots[i])){
                int freq = (int)tableHasMotsFreq.get(tabMots[i])+1;
                tableHasMotsFreq.put(tabMots[i], freq);                
            }else{
                tableHasMotsFreq.put(tabMots[i], 1);
            }
            
        }
        
        
        // On crée l'arbre et lui ajoute tous les mots qui sont dans la table de haschage
        Arbre arbre = new Arbre();
        
        Set cles = tableHasMotsFreq.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            Object cle = it.next(); 
            Object valeur = tableHasMotsFreq.get(cle);
            Mot mot = new Mot((String)cle,(int)valeur);
            arbre.ajouteMot(mot);
            System.out.println("mot ajouté: ["  + mot.getMot()+"]");
        }
        
               
        // Tests de la fonction de complétion:
        ListeMots liste = new ListeMots();
        liste = arbre.completion("ga");
        // On trie la liste de mots du plus fréquent au moins fréquent:
        liste.trier();
        
        System.out.println("\n");
        
        for(Mot mot : liste){
            System.out.println("mot: "+ mot.getMot()+"   freq: "+ mot.getFrequence());
        }
         
        System.out.println("nombres de noeuds " + Noeud.nombreDeNoeudsCrees);
    }
    
    
}
