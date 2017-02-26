
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe qui lit un fichier texte (dont le nom est entré en argument du
 * constructeur) et qui attribue à sa variable texteNettoye le texte lu sous
 * forme de String, nettoyé de ses balises d'anonymisation et des espaces en trop,
 * le but étant de faciliter la récupération des mots
 */
public class LectureTexte {

    
    /**
     * nom du fichier texte 
     */
    private String nomFichier;
    
    /**
     * texte propre
     */
    private String texteNettoye;

    public LectureTexte(String nom) throws FileNotFoundException, IOException {

        this.nomFichier = nom;

        FileReader fR = new FileReader(this.nomFichier);
        BufferedReader bR = new BufferedReader(fR);

        String line;
        String texte = "";

        // On parcourt ligne par ligne le fichier texte      
        while ((line = bR.readLine()) != null) {

            // boolean qui va indiquer si on garde cette ligne ou non
            boolean ligneAcceptee = true;

            // On parcourt la ligne caractère par caractère
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                // Si la ligne contient une balise ouvrante sans qu'elle soit suivie d'un 3 (<3) on ne prend pas la ligne
                if (i != line.length() - 1 && c == '<' && line.charAt(i + 1) != '3') {
                    ligneAcceptee = false;
                }
            }
            if (ligneAcceptee == true) {
                texte += line + " ";
            }

        }
        // On convertit le String en StringBuilder pour pouvoir supprimer des espaces
        StringBuilder strBuilder = new StringBuilder(texte);

        // On parcourt toute la chaîne. Si on a deux espaces d'affilé, on en supprime un
        for (int i = 0; i < strBuilder.length(); i++) {
            if (i != strBuilder.length() - 1 && strBuilder.charAt(i) == ' ' && strBuilder.charAt(i + 1) == ' ') {
                strBuilder.deleteCharAt(i);
            }
        }
        // On réitère l'opération (pour les cas où il y a 3 espaces d'affilé)
        for (int i = 0; i < strBuilder.length(); i++) {
            if (i != strBuilder.length() - 1 && strBuilder.charAt(i) == ' ' && strBuilder.charAt(i + 1) == ' ') {
                strBuilder.deleteCharAt(i);
            }
        }

        // Enfin, on attirbue à la variable texteNettoye la chaîne de caractères ainsi obtenue
        this.texteNettoye = strBuilder.substring(0);
        fR.close();

    }

    /**
     * Accesseur de texteNettoye
     *
     * @return String
     */
    public String getTexteNettoye() {
        return this.texteNettoye;
    }

    /**
     * Test de LectureTexte
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String args[]) throws FileNotFoundException, IOException {
        LectureTexte lectureTexte = new LectureTexte("corpusSMSsur2.txt");
        System.out.println("texte nettoyé: " + lectureTexte.getTexteNettoye());

    }

}
