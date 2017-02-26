import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * Classe représentant des liste de Bigramme elle hérite de Ngramme
 */
public class Bigramme extends Ngramme{
	
	/**
	 * Constructeur de Bigramme
	 * @param corpus
	 */
	public Bigramme(String corpus){
			super(corpus);
		}
	
	/**
	 *@return une liste de tout les Bigramme de corpus
	 */	
	public ArrayList<String[]> suiteNMots(){
		String[] tab = rangeTableau();
		String[] deux = new String[2];
		ArrayList<String[]> listeDeunMot = new ArrayList<String[]>();
		for(int i=0; i<tab.length-1; i++){
			//on range dans la première case la premier mot de Bigramme
			deux[0] = tab[i];
			//dans la seconde case, on range le deucième mot du Bigramme
			deux[1] = tab[i+1];
			listeDeunMot.add(deux);
			deux = new String[2];

		}
		return listeDeunMot;
	}
	/**
	 * @return la prédiction des mots suivants de texte
	 */
	public ArrayList<String> motsSuivants(String[] unMot) {
		//on transforme le texte en tableau
		if(unMot.length!=1) throw new IllegalArgumentException("Pour un bigramme, l'utilsateur doit rentrer 1 mot");
		ArrayList<String> listeDeMotsSuivants = new ArrayList<String>();
		int compteur = 0;
		if(getNgramme().get(unMot[0])==null) return listeDeMotsSuivants;
		Couple[] couple = Couple.convertirHashMapEnTab(getNgramme().get(unMot[0]));
		// On tri le tableau de Couple de façon décroissante
		Tri.quickSort(couple, 0, couple.length - 1);
		for(int i=0; i<couple.length; i++){
			if(compteur<nombreDeMotsSuivants){
				listeDeMotsSuivants.add(couple[i].getMot());
				compteur ++;
			}
		}

		return listeDeMotsSuivants;

	}
  
	public static void main(String[] args) throws FileNotFoundException, IOException {
		LectureTexte lT = new LectureTexte("corpusSMS.txt");
		String texteNettoye = lT.getTexteNettoye();
		Bigramme bigramme = new Bigramme(texteNettoye);
		bigramme.nombreDeMotsSuivants = 8;
		Trigramme trigramme = new Trigramme(texteNettoye);
		String[] tab = {"ce"};
		ArrayList<String> l = bigramme.motsSuivants(tab);
		System.out.println(l.size());
		for(int i=0; i<l.size(); i++){
		 System.out.println(l.get(i));
		}
}


}
