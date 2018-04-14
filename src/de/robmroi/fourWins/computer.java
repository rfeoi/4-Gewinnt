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
        if (field == -1){                           //when computer does not have three in a row
            field = threeField(1);          //check if player has three in a row
            if (field == -1){                       //when player does not have three in a row
                field = twoField(1);        //check if player has two in a row
                if (field == -1){                   //when player does not have two in a row
                    field = twoField(2);    //check if computer has two in a row
                    if (field == -1){               //when computer does not have two in a row
                        field = randomToField();
                        if (field == -1){
                            field = createRandom(6);
                        }
                    }
                }
            }
        }
        return field;
    }

    int threeField(int player){
        fields = Service.places; // only for testing
        int test;
        // horizontal
        for(int y = 0; y<6; y++){
            for(int x = 0; x<5; x++){
                if(fields[x][y] == player && fields[x+1][y] == player && fields[x+2][y] == player) {
                    if (y== 5) {
                        test = tryThis(x+3,y,false,0);
                        if (test != -1)  return test;
                        test = tryThis(x-1,y,false,0);
                        if (test != -1)  return test;
                    }
                    test = tryThis(x+3,y,true,y+1);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y,true,y+1);
                    if (test != -1)  return test;
                }
            }
        }

        // vertical
        for(int y = 0; y<4; y++){
            for(int x = 0; x<7; x++){
                if(fields[x][y] == player && fields[x][y+1] == player && fields[x][y+2] == player) {
                    test = tryThis(x,y-1,false,0);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal up left -> bottom right
        for(int y = 0; y<4; y++){
            for(int x = 0; x<5; x++){
                //System.out.println("[" + x + "," + y + "][" + (x+1) + "," + (y+1) + "][" + (x+2) + "," + (y+2) + "][");
                if(fields[x][y] == player && fields[x+1][y+1] == player && fields[x+2][y+2] == player) {
                    test = tryThis(x+3,y+3,false,0);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y-1,true,y);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal bottom left -> up right
        for(int y = 5; y>1; y--){
            for(int x = 0; x<5; x++){
                if(fields[x][y] == player && fields[x+1][y-1] == player && fields[x+2][y-2] == player) {
                    test = tryThis(x+3,y-3,true,y-2);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y-1,false,0);
                    if (test != -1)  return test;
                }
            }
        }
        return -1;
    }

    int twoField(int player){
        fields = Service.places; // only for testing
        int test;
        // horizontal
        for(int y = 0; y<6; y++){
            for(int x = 0; x<6; x++){
                //System.out.println("[" + x + "," + y + "][" + (x+1) + "," + y + "]");
                if(fields[x][y] == player && fields[x+1][y] == player) {
                    if (y== 5) {
                        test = tryThis(x+2,y,false,0);
                        if (test != -1)  return test;
                        test = tryThis(x-1,y,false,0);
                        if (test != -1)  return test;
                    }
                    test = tryThis(x+2,y,true,y+1);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y,true,y+1);
                    if (test != -1)  return test;
                }
            }
        }

        // vertical
        for(int y = 0; y<5; y++){
            for(int x = 0; x<7; x++){
                //System.out.println("[" + x + "," + y + "][" + x + "," + (y+1) + "]");
                if(fields[x][y] == player && fields[x][y+1] == player) {
                    test = tryThis(x,y-1,false,0);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal up left -> bottom right
        for(int y = 0; y<5; y++){
            for(int x = 0; x<6; x++){
                if(fields[x][y] == player && fields[x+1][y+1] == player) {
                    test = tryThis(x+2,y+2,false,0);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y-1,true,y);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal bottom left -> up right
        for(int y = 5; y>0; y--){
            for(int x = 0; x<6; x++){
                //System.out.println("[" + x + "," + y + "][" + (x+1) + "," + (y-1) + "]");
                if(fields[x][y] == player && fields[x+1][y-1] == player) {
                    test = tryThis(x+2,y-2,true,y-1);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y-1,false,0);
                    if (test != -1)  return test;
                }
            }
        }
        return -1;
    }

    int tryThis(int x, int y, boolean protection, int pY){
        if (protection){
            try{
                if (fields[x][pY] != 0){
                    try{
                        if (fields[x][y] == 0) return x;
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
        } else {
            try{
                if (fields[x][y] == 0) return x;
            } catch (Exception e) {}
        }
        return -1;
    }

    int createRandom(int range){
            double randomDouble = new Random().nextDouble() * range;
            int randomInt = (int) randomDouble;
            if (randomDouble - randomInt >= 0.5 && randomInt <(range+1)) randomInt +=1;
            return randomInt;
    }

    int randomToField(){
        int random = 0, count = 0, range = 0, field = 0;
        boolean first, second, third;
        first = false;
        second = false;
        third = false;
        for (int x = 0; x<7;x++){
            for (int y = 0; y<6; y++){
                if (fields[x][y] == 2) random ++;
            }
        }
        random = createRandom(random-1) + 1;
        for (int x = 0; x<7;x++){
            for (int y = 0; y<6; y++){
                if (fields[x][y] == 2) count ++;
                if (count == random){
                    if (x!= 0){
                        if (fields[x-1][y] == 0) {
                            if (y == 5) {
                                range += 1;
                                first = true;
                            } else if (fields[x-1][y+1] != 0) {
                                range += 1;
                                first = true;
                            }
                        }
                    }
                    if (x!= 6){
                        if (fields[x+1][y] == 0) {
                            if (y == 5) {
                                range += 1;
                                second = true;
                            } else if (fields[x+1][y+1] != 0) {
                                range += 1;
                                second = true;
                            }
                        }
                    }
                    if (y!= 0){
                        if (fields[x][y-1] == 0) {
                            range += 1;
                            third = true;
                        }
                    }
                    if (range == 0) return -1;
                    field = createRandom(range);
                    if (field == 0 && first) return x-1;
                    else if (field == 1 && second) return x+1;
                    else if (field == 2 && third) return x;
                }
            }
        }
        return -1;
    }

}
