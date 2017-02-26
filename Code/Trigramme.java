
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * Classe représentant une liste de Trigramme elle hérite de Ngramme
 *
 */

public class Trigramme extends Ngramme {

	/**
	 * Constructeur du Trigramme
	 * @param corpus
	 */
	public Trigramme(String corpus) {
		super(corpus);
	}

	/**
	 * @return une liste de tout les Trigramme de corpus
	 */
	public ArrayList<String[]> suiteNMots() {

		String[] tab = rangeTableau();
		String[] trois = new String[2];
		ArrayList<String[]> listeDeTroisMots = new ArrayList<String[]>();

		for (int i = 0; i < tab.length - 2; i++) {
			//on range dans la première case du tableau les deux premiers mots du Trigramme
			trois[0] = tab[i] + tab[i + 1];
			// dans la seconde case le troisième mot du Trigramme
			trois[1] = tab[i + 2];
			listeDeTroisMots.add(trois);
			trois = new String[2];

		}
		return listeDeTroisMots;
	}
	/**
	 * @return la prédiction des mots suivants de texte
	 */
	public ArrayList<String> motsSuivants(String[] deuxMots) {
		//on transforme le texte en tableau
		if(deuxMots.length!=2) throw new IllegalArgumentException("Pour un trigramme, l'utilsateur doit rentrer 2 mots");
		ArrayList<String> listeDeMotsSuivants = new ArrayList<String>();
		int compteur = 0;
		if(getNgramme().get(deuxMots[0]+deuxMots[1])==null) return listeDeMotsSuivants;
		Couple[] couple = Couple.convertirHashMapEnTab(getNgramme().get(deuxMots[0]+deuxMots[1]));
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

	public static void main(String[] args) {
		String corpus = "Coucou comment vas tu je vais bien je mange beaucoup de courgette et je mange très de poisson je mange encore je mange beaucoup à la plage bisous";
		Trigramme b = new Trigramme(corpus);
		System.out.println("bigramme " + b);
		nombreDeMotsSuivants = 10;
                String[] tab = {"je","mange"};
		ArrayList<String> test = b.motsSuivants(tab);
		System.out.println("le for");
		for(int i=0; i<test.size(); i++){
			System.out.println(test.get(i));
		}

	}


}
