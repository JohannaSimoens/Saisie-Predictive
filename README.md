# Saisie-Predictive

Système de Saisie Predictive d'un texte avec interface graphique (à l'image d'un prédicteur de saisie de téléphone portable) dans le cadre d'un projet de L3 de Linguistique Informatique.
Etant donné le début d'un texte tapé par l'utilisateur, 
- si un mot est en cours d'écriture, des propositions de mots complets sont affichées et peuvent être selectionnées
- si un mot vient d'être entré, des propositions de mots suivants sont affichées en fonction des mots précédents et des habitudes de l'utilisateur

Les propositions de mots sont faîtes en fonction de leurs fréquences dans un corpus de SMS, 88milSMS:

"Panckhurst R., Détrie C., Lopez C., Moïse C., Roche M., Verine B. (2016a). 88milSMS. A corpus of authentic text messages in French. In Chanier T. (ed) Banque de corpus CoMeRe. Ortolang : Nancy. https://hdl.handle.net/11403/comere/cmr-88milsms"


# Pré-requis

- Un JDK

# Mode d'emploi:

- Executer le fichier JFrame.java
- Une fenêtre avec un champ de texte s'affiche
- Taper le début d'un mot, les 8 propositions de mot les plus cohérentes s'affichent
- Taper un espace pour voir les propositions du mot suivant qui s'affichent

Auteur:
Johanna Simoens et
Alix Bourrée
