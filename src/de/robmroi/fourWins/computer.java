package de.robmroi.fourWins;

import java.util.Random;

//Its an computer that plays against you!

/**
 * Checken ob der Computer 3 Felder hat
 * Checken ob der Gegenspieler 3 Felder hat
 * Checken ob der Spieler 2 Felder hat
 * Checken ob der Computer 2 Felder hat
 * An vorhandenes Feld vom Computer setzen
 * 1. Computer Zug: in Reihe 3 oder 4 setzen
 */
public class computer {
    private double randomDouble;
    private int randomInt, field;
    public int[][] fields;

    public void preStart(){
        fields = new int[7][6];
        System.out.print("computer preStart    ");
    }
    public int computerTurn(){
        fields = Service.places;
        threeField(1);
        if (false) {

        } else {
            randomDouble = new Random().nextDouble()*6;
            randomInt = (int)randomDouble;
            if (randomDouble-randomInt >= 0.5 && randomInt<7) randomInt +=1;
            field = randomInt;
        }
       return field;
    }

    public int threeField(int player){
        for(int y = 0; y<6; y++){
            for(int x = 0; x<5; x++){
                if(fields[x][y] == player && fields[x+1][y] == player && fields[x+2][y] == player) {
                    if (fields[x+3][y] == 0) {
                    }
                }
            }
        }

        for(int y = 0; y<3; y++){
        for(int x = 0; x<7; x++){
            if(fields[x][y] == player && fields[x][y+1] == player && fields[x][y+2] == player) {

            }
        }
    }

        for(int y = 0; y<3; y++){
        for(int x = 0; x<4; x++){
            if(fields[x][y] == player && fields[x+1][y+1] == player && fields[x+2][y+2] == player) {

            }
        }
    }

        for(int y = 5; y>2; y--){
        for(int x = 0; x<4; x++){
            if(fields[x][y] == player && fields[x+1][y-1] == player && fields[x+2][y-2] == player) {

            }
        }
    }
        return 0;
    }
}
