package de.robmroi.fourWins;
//Its an computer that plays against you!
public class computer {
        // WENN DER COMPUTER CHECKEN SOLL, OB ER GEWINNEN KANN, DANN MUSS int player 2 sein.
        // WENN DER COMPUTER CHECKEN SOLL, OB ER DEN SPIELZUG DES GEGENSPIELERS VEREITELN KANN, MUSS int player 1 sein.

                    System.out.println("< Diagonal >");
                }
            }
        }

        for(int y = 5; y>2; y--){
            for(int x = 0; x<4; x++){
                if(places[x][y] == player && places[x+1][y-1] == player && places[x+2][y-2] == player) {

                    out places[x+3][y-3] = computerState;

                    System.out.println("< Diagonale (andersherum) >");
                }
            }
        }
        return 0;
    }




}
