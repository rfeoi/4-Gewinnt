package de.robmroi.fourWins.computerP;


public class AI_Player {
    Computer computer;
    Brain brain;
    boolean gameFinished = false;
    boolean won = false;
    boolean isBest = false;//true if this dot is the best dot from the previous generation, only nessesary with multiple AIs
    boolean dead = false;
    int turns = 0;
    int fitness = 1;
    int steps;
    int rows,columns;

    AI_Player(int rows, int columns){
        steps = (rows*columns);
        this.rows = rows;
        this.columns = columns;
        brain = new Brain(steps,rows);
    }



    void turn(){
        if (steps>turns){
            //A random Move from the randomMoves
            turns ++;
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


    AI_Player recreate(){
        AI_Player baby = new AI_Player(rows,columns);
        baby.brain = brain.clone();
        return baby;
    }
}
