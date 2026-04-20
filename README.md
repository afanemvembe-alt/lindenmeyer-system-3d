# Installation du projet

Cloner le projet, puis faire `./gradle installDist` ou `./gradlew installDist`
sur Unix/MacOS et Windows respectivement. Les fichiers ainsi crées se situent
dans build/install/projet-l2-lindenmeyer-3.

# Utilisation
Une fois installé, exécuter dans le dosssier d'installation
projet-l2-lindenmeyer-3. Il est aussi possible d'utiliser le build system
pour faire tourner l'application avec `ant run` et `./gradle(w) run`.

## Spécification des règles
Les règles de remplacement simple doivent être sous la forme A>B+B,B>A.
Les règles stochastiques sont de la forme A\[probabilitè de 0 à 1\]>B+B.
## L'axiome
L'axiome peut être un enchaînement quelconque de caractères valides.
## Déplacement de la tortue
- En 2D:
  - +: tourner à droite
  - -: tourner à gauche
  - \[: stocker la position actuelle
  - \]: restaurer la dernière position sauvegardée
- En 3D:
  - &: monter
  - ^: descendre
  - \\: roulis à droite
  - /: roulis à gauche

# Idees

- laisser l'utilisateur choisir l'action des constantes sur la tortue
- historique des generations du systeme

# Liens annees
https://www.jmdoudoux.fr/java/dej/chap-graphisme.htm
https://www.kevs3d.co.uk/dev/lsystems/
