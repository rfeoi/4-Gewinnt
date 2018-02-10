package de.robmroi.fourWins;

import java.util.Random;

//Its an computer that plays against you!
public class computer {
    private double randomDouble;
    private int randomInt, field;
    public void preStart(){

    }
    public int computerTurn(/*int fields[][]*/){
        if (false) {
            //Hier deinen Check einfügen
        } else {
            randomDouble = new Random().nextDouble()*6;
            randomInt = (int)randomDouble;
            if (randomDouble-randomInt >= 0.5 && randomInt<7){
                randomInt +=1;
            }
            field = randomInt;
        }
       return field;
    }

    public int fieldCheck(int fields[][], int player){
        // WENN DER COMPUTER CHECKEN SOLL, OB ER GEWINNEN KANN, DANN MUSS int player 2 sein.
        // WENN DER COMPUTER CHECKEN SOLL, OB ER DEN SPIELZUG DES GEGENSPIELERS VEREITELN KANN, MUSS int player 1 sein.
        // Das hier ist och nicht gut aber es wird noch verbessert

        for(int y = 5; y>2; y--){
            for(int x = 0; x<4; x++){
                if(fields[x][y] == player && fields[x+1][y-1] == player && fields[x+2][y-2] == player) {
                    field = x+3;
                }
            }
        }
        return field;
    }




}
