/**
 * Classe pour tester la classe arbre
 */
public class TestArbre {
    
    public static void main(String[] args){
        
        ListeMots lexiqueFrequence = new ListeMots();

        lexiqueFrequence.add(new Mot("toit", 34));
        lexiqueFrequence.add(new Mot("tour", 50));
        lexiqueFrequence.add(new Mot("table", 14));
        lexiqueFrequence.add(new Mot("carre",55));

        Arbre arbre = new Arbre();
        
        // tests d'ajouts de mots
        for (Mot mot : lexiqueFrequence) {
            arbre.ajouteMot(mot);
        }
        
        // On regarde si ça a marché:        
        System.out.println("premier mot lu: "+arbre.tete.getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).motLu().getMot());

        System.out.println(" ");        
        System.out.println("deuxieme mot lu: "+arbre.tete.getNoeudFils(0).getNoeudFils(0).getNoeudFils(1).getNoeudFils(0).motLu().getMot());
        System.out.println("Frequence: "+arbre.tete.getNoeudFils(0).getNoeudFils(0).getNoeudFils(1).getNoeudFils(0).getFrequence());
        
        System.out.println(" ");
        System.out.println("frequence du mot: " + arbre.tete.getNoeudFils(0).getNoeudFils(1).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).getFrequence());
        System.out.println("Est-ce un mot? " + arbre.tete.getNoeudFils(0).getNoeudFils(1).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).estCeUnMot());

        System.out.println(" ");
        System.out.println("mot lu: "+ arbre.tete.getNoeudFils(1).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).motLu().getMot());
        System.out.println("Est-ce un mot? " + arbre.tete.getNoeudFils(1).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).estCeUnMot());
        System.out.println("frequence du mot: " + arbre.tete.getNoeudFils(1).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).getNoeudFils(0).getFrequence());
        System.out.println(" ");
        
        // test de complétion        
        ListeMots listeMotsComplets = new ListeMots();
        
        // la fonction de complétion prend en argument un début de chaîne et renvoit une liste de Mots complets 
        listeMotsComplets = arbre.completion("to");
        
        System.out.println("\n");
        
        //On affiche la liste de mots complets:
        for(Mot mot : listeMotsComplets){
            System.out.println("mot: "+mot.getMot() + ", frequence: "+ mot.getFrequence());    
            
        }
        
        System.out.println("test la virgule");
        String c = "czde-zd,";
        
        
        if(c.matches("[a-zA-Z/-]*[,]")==true){
            System.out.println("oui!");
        }
        
    }    
}
