/**
 * Arbre n-aire
 */
public class Arbre {

   
    /**
     * Noeud inutile, sans label, qui sert juste à faire office de père à la première couche de l'arbre
     */
    public Noeud tete;

    /**
     * Constructeur de l'arbre, initialise la tête
     */
    public Arbre() {
        this.tete = new Noeud('.', null);
    }

    /**
     * Fonction d'ajout d'un mot dans l'arbre, elle ne fait que transmettre les
     * arguments à la première feuille de l'arbre
     *
     * @param mot le mot à ajouter (une instance de Mot est: un String(le mot) +
     * un float(la frequence))
     */
    public void ajouteMot(Mot mot) {

        // le mot doit être non vide        
        if (mot.getMot().length() > 0) {

            if (!this.tete.aAuMoinsUnFils()) {
                // si jamais l'arbe est vide et n'a pas de première feuille ,on crée une première feuille 
                tete.ajouterFils(new Noeud(mot.getMot().charAt(0), tete));
            }
            
            // dans tous les cas, on passe les informations nécessaires à la fonction ajouteMot de la premiere feuille de l'arbre
            tete.getNoeudFils(0).ajouteMot(mot, mot.getMot().length(), 0);            
        }

    }

    /**
     * Fonction de complétion qui transmet les informations au premier fils de la tête
     *
     * @param motACompleter la chaîne de caractère qui correspond au début de
     * mot qu'on veut compléter
     * @return liste de mots (mot + fréquence)
     */
    public ListeMots completion(String motACompleter) {

        ListeMots listeMotsComplets = new ListeMots();

        //la chaine rentrée doit être non vide
        if (motACompleter.length() > 0) {
            // l'arbre doit avoir au moins un noeud (autre que tête)
            if (tete.aAuMoinsUnFils()) {
                listeMotsComplets = tete.getNoeudFils(0).completion(motACompleter, 0, listeMotsComplets);
            } else {
                System.out.println("Vôtre arbre est vide");
            }
        } else {
            System.out.println("Vous avez entré une chaîne vide, il n'y a rien à compléter");
        }
        return listeMotsComplets;
    }

}
