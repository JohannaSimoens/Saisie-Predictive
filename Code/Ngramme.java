import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Classe representant les Ngrammes
 */
import java.util.HashMap;

/**
 * 
 * classe abstraite qui permet de construire des listes de ngrammes
 *
 */
public abstract class Ngramme {

	//prend en clé les n-1 mots et en valeur une autre HashMap qui prend le dernier
	//mot et en valeur le nombre d'occurence du ngramme dans le corpus
	 /**
     * La HashMap du ngramme.
     * prend en clé les n-1 mots et en valeur une autre HashMap qui prend le dernier
	 * mot et en valeur le nombre d'occurence du ngramme dans le corpus
     * @see Ngramme#getcorpus()
     * @see Ngramme#setcorpus(String)
     */
    private HashMap<String, HashMap<String, Integer>> ngramme;
    
    //corpus sur lequel on se base pour construire les liste de ngrammes
	 /**
     * Le corpus du ngramme.
     * @see Ngramme#getNgramme()
     * @see Ngramme#setNgramme(HashMap<String, HashMap<String, Integer>>)
     */
    private String corpus;
    
    //valeur arbitraire qui correspond au nombre de mot suivant que l'on affichera
	 /**
     * Valeur statique correspondant au nombre de mots suivants que l'on affichera
     */
    protected static int nombreDeMotsSuivants;
    
    /**
     * fonction abstraite qui prédit les mot suivant
     * @param texte
     * @return
     */
    public abstract ArrayList<String> motsSuivants(String[] texte);
    
    /**
     * Constructeur du Ngramme
     * @param corpus
     */
    public Ngramme(String corpus) {
        this.corpus = corpus;
        ngramme = new HashMap<String, HashMap<String, Integer>>();
        this.hit();
    }
    
    /**
     * 
     * @return tableau de String représentant le corpus (un mot par case du tableau)
     */
    public String[] rangeTableau() {
        return corpus.split(" ");
    }
    
    
    /**
     * 
     * @return une liste de Ngramme 
     */
    public abstract ArrayList<String[]> suiteNMots();

    /**
     * 
     * @param a
     * @return le nombre d'occurence de tableau de String a dans corpus
     */
    public int nbOccurencesSuiteNMots(String[] a) {// Nombre de fois où il y a la suite de mots a dans le corpus
    	assert a.length==2;
        int compteur = 0;
        ArrayList<String[]> liste = suiteNMots();

        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i)[0].equals(a[0]) && liste.get(i)[1].equals(a[1])) {
            	//on incremente le compteur dès que la liste et a sont égaux
                compteur = compteur + 1;
            }
        }
        return compteur;
    }
    /**
     * Fonction qui sert à ajouter des élèments dans la HashMap
     */
    public void hit() {
        ArrayList<String[]> NMots = suiteNMots();
        System.out.println(NMots.size()+" Nmot size");
    	//on parcourt la suite de N mots (tous les Ngrammes)
        for (int i = 0; i < NMots.size(); i++) {
        	HashMap<String, Integer> temp = null;
        	//c'est la premiere fois qu'on rencontre le n-1gramme
        	if(ngramme.get(NMots.get(i)[0])==null){
            	// on crée une HashMap temporaire
        		temp = new HashMap<String, Integer>();
                temp.put(NMots.get(i)[1], 1);
        	}
        	//n-1gramme deja rencontré
        	else if (ngramme.get(NMots.get(i)[0])!=null){
        		temp = ngramme.get(NMots.get(i)[0]);
        		//c'est la premiere fois qu'on rencontre le suivant
        		if(temp.get(NMots.get(i)[1])==null){
                    temp.put(NMots.get(i)[1], 1);
            	}
        		//ce n'est pas la premiere fois qu'on rencontre le suivant
            	else{
            		temp.put(NMots.get(i)[1],temp.get(NMots.get(i)[1])+1);
            	}
        	}
        	
            // on ajoute au Ngramme en String le "début" du Ngramme et en HashMap temp
            ngramme.put(NMots.get(i)[0], temp);
        }
        System.out.println("le Ngramme est construit");

    }
    
    /**
     * @return la représentation graphique d'un Ngramme
     */

    public String toString() {
        String total = "";
        for (String a : ngramme.keySet()) {
            String deuxiemeMot = "";
            String premierMot = "";
            for (String b : ngramme.get(a).keySet()) {
                premierMot = a + " : " + "\n";
                deuxiemeMot = deuxiemeMot + " " + b + ": " + ngramme.get(a).get(b);
            }
            deuxiemeMot = deuxiemeMot + "\n";
            total = total + premierMot + deuxiemeMot;
        }
        return total;
    }
    /**
     * accesseur de ngramme
     * @return ngramme
     */
    public HashMap<String, HashMap<String, Integer>> getNgramme() {
		return ngramme;
	}
    /**
     * modifieur de ngramme
     * @param ngramme
     */
	public void setNgramme(HashMap<String, HashMap<String, Integer>> ngramme) {
		this.ngramme = ngramme;
	}
	/**
	 * accesseur de corpus
	 * @return corpus
	 */
	public String getcorpus() {
		return corpus;
	}
	/**
	 * modifieur de corpus
	 * @param corpus
	 */
	public void setcorpus(String corpus) {
		this.corpus = corpus;
	}
        /**
         * foncion main pour tester la classe 
         * @param args
         * @throws FileNotFoundException
         * @throws IOException
         */
		public static void main(String[] args) throws FileNotFoundException, IOException {
			LectureTexte lT = new LectureTexte("corpusSMS.txt");
			String texteNettoye = lT.getTexteNettoye();
			Bigramme bigramme = new Bigramme(texteNettoye);
			Trigramme trigramme = new Trigramme(texteNettoye);
			System.out.println(trigramme);
			String[] tab = {"suis"};
			ArrayList<String> l = bigramme.motsSuivants(tab);
			System.out.println(l.size());
			for(int i=0; i<l.size(); i++){
			 System.out.println(l.get(i));
			}
    }
}
    
