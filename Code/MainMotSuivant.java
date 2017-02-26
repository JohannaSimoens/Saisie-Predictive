
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe pour tester la prédiction du mot suivant
 */
public class MainMotSuivant {

    public static void main(String[] args) {

        //on construit le trigramme et le bigramme à partir du corpus
        String corpus = "Non chez mon arrière-grand-mère juste à coté de chez mes grands-parents ;)\n";
        Bigramme b = new Bigramme(corpus);
        Trigramme t = new Trigramme(corpus);
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un mot ou deux :");
        b.nombreDeMotsSuivants = 5;
        t.nombreDeMotsSuivants = 5;
        String str = sc.nextLine();
        String[] tab = str.split(" ");
        if (tab.length == 1) {
            //si le texte entré fait un mot on affiche le bigramme
            ArrayList<String> bigramme = b.motsSuivants(tab);
            for (int i = 0; i < bigramme.size(); i++) {
                System.out.println(bigramme.get(i));
            }
        } else if (str.split(" ").length == 2) {
            //si le texte entré fait un mot on affiche le bigramme
            ArrayList<String> trigramme = t.motsSuivants(tab);
            for (int i = 0; i < trigramme.size(); i++) {
                System.out.println(trigramme.get(i));
            }
            if (trigramme.size() < t.nombreDeMotsSuivants) {
                String[] tab2 = {tab[1]};
                ArrayList<String> bigramme = b.motsSuivants(tab2);
                for (int i = 0; i < bigramme.size(); i++) {
                    System.out.println(bigramme.get(i));
                }

            }
        } else {
            System.out.println("entrez un ou deux mots");
        }
    }
}
