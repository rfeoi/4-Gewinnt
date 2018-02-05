package de.robmroi.fourWins;


import javax.swing.*;
import java.awt.*;

/**
 * To Do:
 * 2Dimensionales Array
 * Computer Gegenspieler
 * Animation
 */
public class Service {
    private String winText;
    private int activePlayer, colorSet;
    private boolean colorBool, win, tie;
    private int[] oldPlaces;
    private int[][] places;
    public int count, winner;
    private Fields fields;
    JFrame frame;

    public void preStart() {
        System.out.print("preStart;   ");
        frame = new JFrame("4-Gewinnt");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setMinimumSize(new Dimension(540,300));
        frame.setMinimumSize(new Dimension(720,720));
        frame.setBackground(new Color(255, 255, 255));
        start();
    }


    public void start() {
        System.out.print("Start;   ");
        fields = new Fields();
        oldPlaces = new int[43];
        places = new int[7][6];
        for (int i = 1; i < 43; i++){
            oldPlaces[i] = 0;
        }
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
    }

    public void game(int row){
        for (int i = row + 35; i >= row; i -= 7) {
                if (oldPlaces[i] == 0) {
                    oldPlaces[i] = activePlayer;
                    colorSet = i;
                    colorBool = true;
                    break;
                }
        }
        if (colorBool) {
            fields.setColor(1,1, colorSet, activePlayer);
            colorBool = false;
        }
        for (int i = 1; i < 43; i++){
            if(oldPlaces[i] == 0) tie = false;
        }
        if (winCheck(1)) {
            win(1);
        } else if(winCheck(2)){
            win(2);
        } else if(!winCheck(1) && !winCheck(2) && tie){
            win(0);
        }
        playerCheck();
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

        for (int i = 1; i <= 21; i+=7) {
            for (int o = 0; o <= 7; o++){
                if (oldPlaces[i + o] == player && oldPlaces[i + o + 7] == player && oldPlaces[i + o + 14] == player && oldPlaces[i + o + 21] == player) {
                    win = true;
                }
            }
        }

        for (int i = 0; i <= 35; i += 7){
            for (int o = 1; o <= 4; o++){
                if (oldPlaces[i + o] == player && oldPlaces[i + o + 1] == player && oldPlaces[i + o + 2] == player && oldPlaces[i + o + 3] == player) {
                    win = true;
                }
            }
        }

        for (int i = 1; i <= 4; i++){
            for (int o = 0; o <= 14; o += 7){
                if (oldPlaces[i + o] == player && oldPlaces[i + o + 8] == player && oldPlaces[i + o + 16] == player && oldPlaces[i + o + 24] == player) {
                    win = true;
                }
            }
        }
        for (int i = 7; i >= 4; i--){
            for (int o = 0; o <= 14; o += 7){
                if (oldPlaces[i + o] == player && oldPlaces[i + o + 6] == player && oldPlaces[i + o + 12] == player && oldPlaces[i + o + 18] == player) {
                    win = true;
                }
            }
        }
        return win;
    }
    public void win (int player){
        if (player == 0){
            winText = "Es ist unentschieden!";
        } else {
            winText = "Spieler " + player + " hat gewonnen!";
        }
        if (JOptionPane.showConfirmDialog(null, winText + "\nWollen Sie noch eine Runde spielen?") == 0){
            System.out.println("Neustart");
            start();
        } else {
            System.exit(0);
        }

    }
}
