public interface Surface {
    /*
    *   Os          VERT
        Soleil      Jaune
        Patte       Bleu
        Rectangle   Violet

        40              2 
        90              3 
        160             4
        250             5
        360             6
        490             7
        640             8
        810             9
        1000            10
        1440            12
        1690            13

        Chaque couleur a le même nombre de point 
        On peut supprimer quand y en a au moins deux à côté l'un de l'autres
        Algorithme pour les points: 
                Une case vaut 10 même si on peut pas la suppriemr c'est sur
                Multiple de la case 2*2 = 4         et on rjaoute un 0 à la fin a chaque fois
                                    3*3 = 9
                                    4*4 = 16
                                    5*5 = 25
                                    ..
                                    13*13= 169
    */
}
