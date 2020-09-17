# Saisie-Predictive

Application de Saisie Predictive d'un texte avec interface graphique (à l'image d'un prédicteur de saisie de téléphone portable) dans le cadre d'un projet de L3 de Linguistique Informatique.
Etant donné le début d'un texte tapé par l'utilisateur: 
- si un mot est en cours d'écriture, des propositions de mots complets sont affichées et peuvent être selectionnées en cliquant dessus avec la souris
- si un mot vient d'être entré et qu'un espace a été entré, des propositions de mots suivants sont affichées en fonction des mots précédents et des habitudes de l'utilisateur

Les propositions de mots sont faîtes en fonction de leurs fréquence dans un corpus de SMS, 88milSMS:
>Panckhurst R., Détrie C., Lopez C., Moïse C., Roche M., Verine B. (2016a). 88milSMS. A corpus of authentic text messages in French. In Chanier T. (ed) Banque de corpus CoMeRe. Ortolang : Nancy. https://hdl.handle.net/11403/comere/cmr-88milsms


# Pré-requis

- JDK

# Mode d'emploi:

- Executer le fichier JFrame.java
- Une fenêtre avec un champ de texte s'affiche
- Taper le début d'un mot, les 8 propositions de mot les plus fréquents s'affichent
- Taper un espace pour voir les propositions du mot suivant s'afficher
- En cliquant sur les boutons avec des propositions de mots, le texte est complété automatiquement
- Le bouton "Sauvegarder" permet de mettre à jour les fréquences de mots en fonction de l'historique de l'utilisateur, le bouton "Effacer" permet d'entrer un nouveau message

Auteurs:
Johanna Simoens et
Alix Bourrée
