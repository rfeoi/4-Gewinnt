package de.robmroi.fourWins.computerP;


public class AI_Player {
    Computer computer;
    boolean gameFinished = false;
    boolean won = false;
    boolean isBest = false;//true if this dot is the best dot from the previous generation, only nessesary with multiple AIs
    boolean dead = false;
    int turns = 0;
    int fitness = 1;
    int steps;

    AI_Player(int rows, int columns){
        steps = (rows*columns);
        createRandomMoves(steps);
    }


    void createRandomMoves(int size){

    }

    void turn(){
        if (steps>turns){
            //A random Move from the randomMoves
        } else dead = true;
    }

    void calculateFitness() {
        //won, less turns, fields near each other
        if (won) {
            fitness = 500 / turns;
        }
        fitness += 10*computer.calcTwo(2);
        fitness += 30*computer.calcThree(2);
    }


    void recreate(){
        //This player is chosen for the next gen, so it is cloned
    }
}
