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
class Computer {
    private int[][] fields, testFields;
    private int count, columns, rows;

    void preStart(){
        columns = Service.columns; // top to bottom (vertically)
        rows = Service.rows; // left to right (horizontally)
        fields = new int[rows][columns];
        testFields = new int[rows][columns];
        System.out.print("computer preStart    ");
    }
    void start(){ count = 0; }

    int computerTurn(){
        fields = Service.places;

        if (count == 0){
            count = 1;
            if ( (rows & 1) == 0) {

                return (rows/2 + createRandom(2)-1);
            }
            else return (rows/2);
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

    private int threeField(int player){
        fields = Service.places; // only for testing
        int test;
        // horizontal
        for(int y = 0; y< rows; y++){
            //System.out.println();
            for(int x = 0; x< columns -2; x++){
                //System.out.println("[" + x + "," + y + "][" + (x+1) + "," + y + "][" + (x+2) + "," + y + "]");
                if(fields[x][y] == player && fields[x+1][y] == player && fields[x+2][y] == player) {
                    if (y== columns -1) {
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
        for(int y = 0; y< rows -3; y++){
            for(int x = 0; x< columns; x++){//rows+1
                if(fields[x][y] == player && fields[x][y+1] == player && fields[x][y+2] == player) {
                    test = tryThis(x,y-1,false,0);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal up left -> bottom right
        for(int y = 0; y< rows -3; y++){
            System.out.println();
            for(int x = 0; x< columns -1; x++){
                //System.out.println("[" + x + "," + y + "][" + (x+1) + "," + (y+1) + "][" + (x+2) + "," + (y+2) + "]");
                if(fields[x][y] == player && fields[x+1][y+1] == player && fields[x+2][y+2] == player) {
                    test = tryThis(x+3,y+3,false,0);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y-1,true,y);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal bottom left -> up right
        for(int y = rows -2; y>1; y--){
            for(int x = 0; x< columns -1; x++){
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

    private int twoField(int player){
        fields = Service.places; // only for testing
        int test;
        // horizontal
        for(int y = 0; y< rows -1; y++){
            for(int x = 0; x< columns -1; x++){
                //System.out.println("[" + x + "," + y + "][" + (x+1) + "," + y + "]");
                if(fields[x][y] == player && fields[x+1][y] == player) {
                    if (y== columns -1) {
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
        for(int y = 0; y< rows -2; y++){
            for(int x = 0; x< columns; x++){ //rows+1
                //System.out.println("[" + x + "," + y + "][" + x + "," + (y+1) + "]");
                if(fields[x][y] == player && fields[x][y+1] == player) {
                    test = tryThis(x,y-1,false,0);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal up left -> bottom right
        for(int y = 0; y< rows -2; y++){
            for(int x = 0; x< columns; x++){
                if(fields[x][y] == player && fields[x+1][y+1] == player) {
                    test = tryThis(x+2,y+2,false,0);
                    if (test != -1)  return test;
                    test = tryThis(x-1,y-1,true,y);
                    if (test != -1)  return test;
                }
            }
        }

        //diagonal bottom left -> up right
        for(int y = rows -2; y>0; y--){
            for(int x = 0; x< columns -1; x++){
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

    private int tryThis(int x, int y, boolean protection, int pY){
        if (protection){
            try{
                if (fields[x][pY] != 0){
                    try{
                        if (fields[x][y] == 0) return x;
                    } catch (Exception ignored) {}
                }
            } catch (Exception ignored) {}
        } else {
            try{
                if (fields[x][y] == 0) return x;
            } catch (Exception ignored) {}
        }
        return -1;
    }

    private int createRandom(int range){
            double randomDouble = new Random().nextDouble() * range;
            int randomInt = (int) randomDouble;
            if (randomDouble - randomInt >= 0.5 && randomInt <(range+1)) randomInt +=1;
            return randomInt;
    }

    private int randomToField(){
        int random = 0, count = 0, range = 0, field;
        boolean first, second, third;
        first = false;
        second = false;
        third = false;
        for (int x = 0; x< rows; x++){
            for (int y = 0; y< columns; y++){
                if (fields[x][y] == 2) random ++;
            }
        }
        random = createRandom(random-1) + 1;
        for (int x = 0; x< rows; x++){
            for (int y = 0; y< columns; y++){
                if (fields[x][y] == 2) count ++;
                if (count == random){
                    if (x!= 0){
                        if (fields[x-1][y] == 0) {
                            if (y == columns -1) {
                                range += 1;
                                first = true;
                            } else if (fields[x-1][y+1] != 0) {
                                range += 1;
                                first = true;
                            }
                        }
                    }
                    if (x!= rows){
                        if (fields[x+1][y] == 0) {
                            if (y == columns -1) {
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

    //FOR THE AI

    /*
    Different Approach:
    It calculates all possibilities for the next X rounds.
    - Harder to make
    - Works way better, because it is updated every move

    2 Fields = 1 Point; 3 Fields = 10 Points. 4 Fields = 40 Points.
     */

    int ai(){
        fields = Service.places;
        testFields = fields;

        int difference;
        int maxX = 0;
        int maxD = 0;
        int player = 2;
        for (int x=0; x<rows;x++){
            testFields = fields;
            int y = aiTurnY(x);
            if (y!= -1) {
                testFields[x][y] = player;
                difference = playersPoints(2, testFields) - playersPoints(1, testFields);
                if (x==0) {
                    maxX = x;
                    maxD = difference;
                } else if (difference > maxD) maxX = x;
            }
        }
        return maxX;
    }

    int aiTurnY(int x){
        for (int y = columns -1; y>=0; y--){
            if (fields[x][y] == 0){
                return y;
            }
        }
        return -1;
    }

    private int playersPoints(int player, int[][] test) {
        int p = countTwoFields(player, test);
        p += countThreeFields(player, test)*10;
        p += countFourFields(player, test)*40;
        return p;
    }

    private int countTwoFields(int player, int[][] test){
        /* Default Print:
            System.out.println("[" + x + "," + y + "][" + (x+0) + "," + (y+0) + "]");
         */
        int count = 0;
        //----------------------------------------        Horizontal      ----------------------------------------
        for (int x=0; x<rows-1;x++){
            for (int y=0; y<columns;y++){
                if (test[x][y] == player && test[x+1][y] == player) count +=1;
            }
        }
        //----------------------------------------        Vertical      ----------------------------------------
        for (int x=0; x<rows;x++){
            for (int y=0; y<columns-1;y++){
                if (test[x][y] == player && test[x][y+1] == player) count +=1;
            }
        }
        //----------------------------------------        Top_Left->Bottom_Right      ----------------------------------------
        for (int x=0; x<rows-1;x++){
            for (int y=0; y<columns-1;y++){
                if (test[x][y] == player && test[x+1][y+1] == player) count +=1;
            }
        }
        //----------------------------------------        Bottom_Left->Top_Right      ----------------------------------------
        for (int x=rows-1; x>0;x--){
            for (int y=0; y<columns-1;y++){
                if (test[x][y] == player && test[x-1][y+1] == player) count +=1;
            }
        }
        return count;
    }

    private int countThreeFields(int player, int[][] test){
        /* Default Print:
            System.out.println("[" + x + "," + y + "][" + (x+0) + "," + (y+0) + "][" + (x+0) + "," + (y+0) + "]");
         */
        int count = 0;
        //----------------------------------------        Horizontal      ----------------------------------------
        for (int x=0; x<rows-2;x++){
            for (int y=0; y<columns;y++){
                if (test[x][y] == player && test[x+1][y] == player && test[x+2][y] == player) count +=1;
            }
        }
        //----------------------------------------        Vertical      ----------------------------------------
        for (int x=0; x<rows;x++){
            for (int y=0; y<columns-2;y++){
                if (test[x][y] == player && test[x][y+1] == player && test[x][y+2] == player) count +=1;
            }
        }
        //----------------------------------------        Top_Left->Bottom_Right      ----------------------------------------
        for (int x=0; x<rows-2;x++){
            for (int y=0; y<columns-2;y++){
                if (test[x][y] == player && test[x+1][y+1] == player && test[x+2][y+2] == player) count +=1;
            }
        }
        //----------------------------------------        Bottom_Left->Top_Right      ----------------------------------------
        for (int x=rows-1; x>1;x--){
            for (int y=0; y<columns-2;y++){
                if (test[x][y] == player && test[x-1][y+1] == player && test[x-2][y+2] == player) count +=1;
            }
        }
        return count;
    }

    private int countFourFields(int player, int[][] test){
        /* Default Print:
            System.out.println("[" + x + "," + y + "][" + (x+0) + "," + (y+0) + "][" + (x+0) + "," + (y+0) + "][" + (x+0) + "," + (y+0) + "]");
         */
        int count = 0;
        //----------------------------------------        Horizontal      ----------------------------------------
        for (int x=0; x<rows-3;x++){
            for (int y=0; y<columns;y++){
                if (test[x][y] == player && test[x+1][y] == player && test[x+2][y] == player && test[x+3][y] == player) count +=1;
            }
        }
        //----------------------------------------        Vertical      ----------------------------------------
        for (int x=0; x<rows;x++){
            for (int y=0; y<columns-3;y++){
                if (test[x][y] == player && test[x][y+1] == player && test[x][y+2] == player && test[x][y+3] == player) count +=1;
            }
        }
        //----------------------------------------        Top_Left->Bottom_Right      ----------------------------------------
        for (int x=0; x<rows-3;x++){
            for (int y=0; y<columns-3;y++){
                if (test[x][y] == player && test[x+1][y+1] == player && test[x+2][y+2] == player && test[x+3][y+3] == player) count +=1;
            }
        }
        //----------------------------------------        Bottom_Left->Top_Right      ----------------------------------------
        for (int x=rows-1; x>2;x--){
            for (int y=0; y<columns-3;y++){
                if (test[x][y] == player && test[x-1][y+1] == player && test[x-2][y+2] == player && test[x-3][y+3] == player) count +=1;
            }
        }
        return count;
    }

}