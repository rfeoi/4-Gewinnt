package de.robmroi.fourWins;


import javax.swing.*;
import java.awt.*;

/**
 * To Do:
 * Computer Gegenspieler
 * Animation
 * Variable größe
 */
public class Service {
    private String winText, panelText;
    private int activePlayer;
    private boolean win, tie;
    private int[][] places;
    public int count, winner;
    private Fields fields;
    JFrame frame;

    public void preStart() {
        System.out.print("preStart;   ");
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setMinimumSize(new Dimension(540,300));
        frame.setMinimumSize(new Dimension(700,600));
        frame.setBackground(new Color(255, 255, 255));
        start();
    }


    public void start() {
        System.out.print("Start;   ");
        fields = new Fields();
        places = new int[7][6];
        for(int x = 0; x<7; x++){
            for(int y = 0; y<6; y++){
                places[x][y] = 0;
            }
        }
        winner = 0;
        activePlayer = 1;
        count = 0;
        win = false;

        fields.preStart();
        fields.start();
        frame.setContentPane(fields);
        frame.setVisible(false);
        frame.setVisible(true);
        frame.setTitle("4-Gewinnt   -   Spieler 1 ist am Zug!");
    }

    public void game(int rowX){
        for (int y = 5; y>=0; y--){
            if (places[rowX][y] == 0){
                places[rowX][y] = activePlayer;
                fields.setColor(rowX,y, activePlayer);
                break;
            }
        }

        for(int y = 0; y<6; y++){
            for(int x = 0; x<7; x++){
                if (places[x][y] == 0) tie = false;
            }
        }
        playerCheck();
        panelText = "4-Gewinnt   -   Spieler " + activePlayer + " ist am Zug!";
        frame.setTitle(panelText);
        if (winCheck(1)) {
            winOutput(1);
        } else if(winCheck(2)){
            winOutput(2);
        } else if(!winCheck(1) && !winCheck(2) && tie){
            winOutput(0);
        }
        tie = true;
    }

    public void playerCheck(){

        count++;
        if (count%2 == 0){
            activePlayer = 1;
        } else {
            activePlayer = 2;
        }
    }

    public boolean winCheck(int player) {

        for(int y = 0; y<6; y++){
            for(int x = 0; x<4; x++){
                if(places[x][y] == player && places[x+1][y] == player && places[x+2][y] == player && places[x+3][y] == player) {
                    win = true;
                    System.out.println("Horizontaler win");
                }
            }
        }

        for(int y = 0; y<3; y++){
            for(int x = 0; x<7; x++){
                if(places[x][y] == player && places[x][y+1] == player && places[x][y+2] == player && places[x][y+3] == player) {
                    win = true;
                    System.out.println("Vertikaler win");
                }
            }
        }

        for(int y = 0; y<3; y++){
            for(int x = 0; x<4; x++){
                if(places[x][y] == player && places[x+1][y+1] == player && places[x+2][y+2] == player && places[x+3][y+3] == player) {
                    win = true;
                    System.out.println("Diagonaler win");
                }
            }
        }

        for(int y = 5; y>2; y--){
            for(int x = 0; x<4; x++){
                if(places[x][y] == player && places[x+1][y-1] == player && places[x+2][y-2] == player && places[x+3][y-3] == player) {
                    win = true;
                    System.out.println("Diagonale (andersrum) win");
                }
            }
        }
        return win;
    }

    public void winOutput(int player){
        if (player == 0){
            winText = "Es ist unentschieden!";

        } else {
            winText = "Spieler " + player + " hat gewonnen!";
        }
        frame.setTitle("4-Gewinnt   -   " + winText);
        if (JOptionPane.showConfirmDialog(null, winText + "\nWollen Sie noch eine Runde spielen?") == 0){
            System.out.println("Neustart");
            start();
        } else {
            System.out.println("Ende");
            System.exit(0);
        }

    }
}
