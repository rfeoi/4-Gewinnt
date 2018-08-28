package de.robmroi.fourWins.computerP;

import java.util.Random;

public class Brain {
    Computer computer;
    int[] turns;
    int rows;

    Brain(int size, int rows){
        turns = new int[size];
        this.rows = rows;
        createRandoms(rows);
    }
    void createRandoms(int rows){
        for (int i=0;i<turns.length;i++){
            turns[i]=computer.createRandom(rows);
        }
    }

    //returns a perfect copy of this brain object
    protected Brain clone() {
        Brain clone = new Brain(turns.length,rows);
        for (int i = 0; i < turns.length; i++) {
            clone.turns[i] = turns[i];
        }
        return clone;
    }

    //mutates the brain by setting some of the directions to random vectors
    void mutate() {
        double mutationRate = 0.01;//chance that any vector in directions gets changed
        for (int i =0; i< turns.length; i++) {
            double rand = new Random().nextDouble();
            if (rand < mutationRate) {
                //set this direction as a random direction
                turns[i] = computer.createRandom(rows);
            }
        }
    }
}
