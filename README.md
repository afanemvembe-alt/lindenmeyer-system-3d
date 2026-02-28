# Commandes de ANT

ant:
- init
- compile
- clean
- doc
- run

# Idees

- laisser l'utilisateur choisir l'action des constantes sur la tortue
- historique des generations du systeme

# Lien
https://www.jmdoudoux.fr/java/dej/chap-graphisme.htm
# Lien Utile 
https://www.kevs3d.co.uk/dev/lsystems/

# Notes

## Benchmark flocons de Koch - Samuel

En faisant des tests sur les simulations de flocons de Koch, on se cogne très vite contre un problème de mémoire.
On pourrait, afin d'économiser la mémoire, utiliser un algorithme de compression, car nos LSystèmes s'y prêtent
probablement assez bien... au dépens de dépenser des cycles pour de/compresser. Avec un calcul rapide, j'estime
qu'on peut naïvement stocker au plus 5e8 pointeurs.
