import java.util.ArrayList;

/*
 * Noeud de l'arbre
 */
public class Noeud {    
    
    
     
    /**
     * la lettre est l'étiquette du noeud, elle ne change pas donc elle est final
     */
    private final char lettre;
    
    /**
     * estUnMot indique si, quand on parcourt l'arbre depuis la tête jusqu'au noeud en question , on obtient un mot existant dans 
     le dictionnaire
     */
    private boolean estUnMot;    
     
    /**
     * frequence du mot (a une valeur si estUnmot et true, vaut 0 si estUnMot est false)
     */
    private int frequence;
    
    /**
     * Liste de Noeuds fils
     */
    private ArrayList<Noeud> fils;
    
    /**
     * l'unique Noeud père
     */
    private final Noeud pere;
  
    /**
     * variable statique qui compte le nombre de noeuds en tout
     */
    public static int nombreDeNoeudsCrees = 0;
      
    /**
     * identifiant du Noeud
     */
    private final int id;

        
    
    /**
     * Constructeur de Noeud qui prend en argument la lettre, le Noeud père, met le booleen
     * "estUnMot" à false et la fréquence à 0 par défaut
     * @param lettre
     * @param pere 
     */
    public Noeud(char lettre, Noeud pere) {
        this.lettre = lettre;
        this.estUnMot = false;
        this.frequence = 0;        
        this.fils = new ArrayList<>();
        this.pere = pere;
        // On a crée un nouveau Noeud donc on incrémente la variable statique nbrDeNoeuds
        nombreDeNoeudsCrees++;
        // on attribue l'identifiant du Noeud
        this.id = nombreDeNoeudsCrees;
    }
    
    

    /**
     * Constructeur de Noeud qui prend en argument la lettre, booléen qui
     * indique si c'est une fin de mot, le Noeud père, et la fréquence
     * @param lettre
     * @param bool
     * @param frequence
     * @param pere 
     */
    public Noeud(char lettre, boolean bool, int frequence, Noeud pere) {
        this.lettre = lettre;
        this.estUnMot = bool;
        this.frequence = frequence;
        this.fils = new ArrayList<Noeud>();
        this.pere = pere;
        nombreDeNoeudsCrees++;
        this.id = nombreDeNoeudsCrees;
    }    
       

    /**
     * Met le boolean "estUnMot" à true
     */
    public void setEstUnMotTrue() {
        this.estUnMot = true;
    }

    /**
     * Accesseur de estUnMot
     * @return TRUE OR FALSE
     */
    public boolean estCeUnMot() {
        return this.estUnMot;
    }

    /**
     * Accesseur de la lettre
     * @return lettre
     */
    public char getLettre() {
        return this.lettre;
    }  
    
    
    /**
     * Accesseur du noeud fils à l'indice i dans la liste de fils
     * @param i indice
     * @return un Noeud fils
     */
    public Noeud getNoeudFils(int i) {
        return fils.get(i);
    }
    
    /**
     * ajouter un fils au noeud courant
     * @param son Noeud fils à ajouter
     */
    public void ajouterFils(Noeud son){
        fils.add(son);
    }
    
    /**
     * Accesseur de fréquence
     * @return frequence
     */
    public int getFrequence(){
        return frequence;
    }
    
    /**
     * Accesseur de père
     * @return le Noeud pere
     */
    public Noeud getPere(){
        return pere;
    }    
    /**
     * Dit si le noeud a au moins un fils (c'est à dire n'est pas une feuille)
     * @return true s'il a au moins un fils
     */
    public boolean aAuMoinsUnFils(){  
        return !fils.isEmpty();        
    }

    /**
     * Fonction qui lit le mot depuis le noeud jusqu'à la tête et en ressort un Mot (String + Fréquence)
     * @return le mot
     */
    public Mot motLu(){
        // on met dans une variable la fréquence du noeud 
        int freq = this.getFrequence();
        
        StringBuilder s = new StringBuilder(""+ this.getLettre());
        Noeud papa = this.pere;
        // Tant que le père existe on remonte l'arbre
        while(papa != null){
            if(papa.getLettre()!='.'){
                // On ajoute la lettre à la chaîne de caractère
                s.append(papa.getLettre());
            }
            // On passe au rang du dessus
            papa = papa.getPere();
        }
        // On a obtenu le mot depuis le noeud de départ jusqu'à la tête donc il faut inverser les lettres pour avoir le mot 
        s = s.reverse();
        return new Mot(s.substring(0),freq);
    }  
    
    
    /**
     * Fonction d'ajout d'un mot (récursive)
     * @param mot mot à ajouter (mot + fréquence)
     * @param taille taille du mot (nombre de lettre)
     * @param rang indice de la lettre en cours (qui correspond aux étapes de parcours du
     * mot pour ajouter le mot lettre par lettre dans l'arbre)
     */
    public void ajouteMot(Mot mot, int taille, int rang) {
        
        // si la taille dépasse la rang, on a fini        
        if (taille>rang){

        // let est la lettre qu'on observe à l'indice "rang" en question
        char let = mot.getMot().charAt(rang);
        // si fin est true, on a fini l'ajout
        boolean fin = (taille - 1) == rang;
        // booleen qui va indiquer s'il faut continuer le parcours du mot ou non
        boolean encore = false;

        
        // Si la lettre du Noeud Courant n'est PAS la lettre du mot à l'indice "rang"
        if (let != this.lettre) {
            //booleen qui va indiquer si le parcours de frères a été concluant ou pas
            boolean unFrereAbienlabonnelettre = false;
            // indice du dernier frère parcouru pour savoir ou rajouter le prochain frère si aucun n'a la bonne lettre
            // parcours du tableau de frère:
            for (Noeud son : pere.fils) {
                if (son.getLettre() == let) {
                    // on tombe sur un frère avec la bonne lettre, on lui applique la fonction ajouteMot et il prend le relais
                    son.ajouteMot(mot, taille, rang);
                    //Comme on a trouvé un frère avec la bonne lettre, on met le booleen a true
                    unFrereAbienlabonnelettre = true;
                }
            }
            // si aucun frère n'a la bonne lettre, on ajoute un frère avec la lettre en question et on lui aplique ajouteMot
            if (!unFrereAbienlabonnelettre) {
                // AJOUT D'UN NOUVEAU NOEUD FRERE AVEC LA LETTRE EN QUESTION
                pere.fils.add(new Noeud(let, this.pere));
                // On lui applique la fonction ajouteMot
                pere.fils.get(pere.fils.size()-1).ajouteMot(mot, taille, rang);
            }
        } 
        
        
        // Si la lettre du Noeud Courant est BIEN la même que la lettre du mot à l'indice "rang", 
        else {
            //alors si on n'a pas atteint le bout du mot, on met le booleen "encore" à true pour continuer le parcours de l'arbre
            if (!fin) {
                encore = true;
            } // si on a atteint le bout du mot, on met le booleen estUnMot à true et on indique la fréquence du mot. On laisse le booleen "encore" à false car on veut arrêter le parcours de l'arbre
            else {
                this.setEstUnMotTrue();
                this.frequence = mot.getFrequence();
            }
        }
        

        // S'il faut continuer le parcours de l'arbre alors,...
        if (encore) {
            // S'il n'y a pas de noeud fils, on en crée un
            if (!this.aAuMoinsUnFils()) {
                // AJOUT D'UN NOUVEAU NOEUD FILS AVEC LA BONNE LETTRE
                fils.add( new Noeud(mot.getMot().charAt(rang + 1),this) );
                // On lui applique la fonction ajouteMot
                fils.get(0).ajouteMot(mot, taille, rang + 1);
            } else // sinon, on applique ajouterMot au premier noeud fils 
            {
                fils.get(0).ajouteMot(mot, taille, rang + 1);
            }
        }

    }else{
            System.out.println("ERREUR taille<=rang !");
        }
    
    }
    
    
    
    /**
     * Fonction de complétion (récursive)
     * @param motACompleter le début de séquence
     * @param rang rang dans l'arbre
     * @param listeMots le liste de Mots à laquelle on ajoute les mots au fur et a mesure
     * @return Liste de mots complets
     */
    public ListeMots completion(String motACompleter,int rang, ListeMots listeMots){
        
        // CAS OU L'ON N'A PAS ENCORE PARCOURU (et dépassé) TOUT LE DEBUT DE MOT A COMPLETER
        if( rang <= motACompleter.length() - 1 ){
            
            // si l'on n'est PAS sur le noeud avec le bonne lettre, 
            if( this.lettre != motACompleter.charAt(rang) ){
                // Alors on continue a parcourir les frères
                for( Noeud frere : this.pere.fils ){
                    // Si on tombe sur le bon frère, on lui applique la fonction ajouteMot
                    if(  frere.getLettre() == motACompleter.charAt(rang) ){
                        listeMots = frere.completion(motACompleter, rang, listeMots);
                    }
                }
            }
            
            // si l'on est sur le noeud avec la bonne lettre
            if( this.lettre == motACompleter.charAt(rang)){
                if(rang == motACompleter.length()-1 && this.estCeUnMot()){
                    listeMots.add(this.motLu());
                }
                // Alors on passe au au rang d'en dessous
                if(this.aAuMoinsUnFils()){
                    // on applique la fonction de complétion au premier fils
                    listeMots = this.getNoeudFils(0).completion(motACompleter, rang+1, listeMots);
                }
            }
        }  
        
        // CAS OU ON A DEPASSE LA CHAINE COMPLETE ENTREE EN ARGUMENT, ON VA REGARDER TOUTE LES POSSIBILITES
        if( rang > motACompleter.length()-1 ){  
            
                       
            // On parcours la fratrie pour voir si ce sont des mots 
            for( Noeud frere : this.pere.fils){
                if (frere.estCeUnMot()){
                    listeMots.add(frere.motLu());                    
                }
            }
            
            // On applique la fonction complétion a chaque premier fils de chaque Noeud de la fratrie
            for( Noeud frere : this.pere.fils){
                if(frere.aAuMoinsUnFils()){
                    listeMots = frere.getNoeudFils(0).completion(motACompleter, rang+1 , listeMots);
                }
            }       
        }      
        
        return listeMots;
    }

    
}
