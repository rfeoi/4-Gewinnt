package de.robmroi.fourWins;

import java.util.Random;

//Its an computer that plays against you!

/**
 * Checken ob der Computer 3 Felder hat
 * Checken ob der Gegenspieler 3 Felder hat
 * Checken ob der Spieler 2 Felder hat
 * Checken ob der Computer 2 Felder hat
 * An vorhandenes Feld vom Computer setzen
 * 1. Computer Zug: in Reihe 2, 3 oder 4 setzen
 */
class computer {
    private int[][] fields;
    private int count;
    void preStart(){
        fields = new int[7][6];
        System.out.print("computer preStart    ");
    }
    void start(){ count = 0; }

    int computerTurn(){
        fields = Service.places;
        if (count == 0){
            count = 1;
            if (fields[3][5] == 0){
                return 3;
            } else {
                return createRandom(2) + 2;
            }
        }

        int field = threeField(2);
        if (field == 0){                           //when computer does not have three in a row
            field = threeField(1);          //check if player has three in a row
            if (field == 0){                       //when player does not have three in a row
                field = twoField(1);        //check if player has two in a row
                if (field == 0){                   //when player does not have two in a row
                    field = twoField(2);    //check if computer has two in a row
                    if (field == 0){               //when computer does not have two in a row
                    // set to random Field (that is next to a computer field)
                    }
                }
            }
        }
        return field;
    }

    int threeField(int player){
        fields = Service.places;
        for(int y = 0; y<6; y++){
            for(int x = 0; x<5; x++){
                if(fields[x][y] == player && fields[x+1][y] == player && fields[x+2][y] == player) {
                    try{
                        if (fields[x+3][y] == 0) return x+3;
                    } catch (Exception e) {System.out.println("Not in array");}
                    try{
                        if (fields[x-1][y] == 0) return x-1;
                    } catch (Exception e) {System.out.println("Not in array");}
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

    int twoField(int player){
        //TODO
        return 0;
    }

    int createRandom(int range){
            double randomDouble = new Random().nextDouble() * range;
            int randomInt = (int) randomDouble;
            if (randomDouble - randomInt >= 0.5 && randomInt <(range+1)) randomInt +=1;
            return randomInt;
    }
}
