package de.robmroi.fourWins;


import javax.swing.*;
import java.awt.*;
import static de.robmroi.fourWins.Startup.computer;


/**
 * To Do:
 * Computer Gegenspieler
 * Animation
 */
public class Service {
    private String winText, panelText;
    private int activePlayer, computerRow, restart;
    private boolean win, tie, withComputer;
    public static int[][] places;
    public int count, winner;
    Fields fields;
    public JFrame frame;
    //Resolution
    public int maxWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int maxHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    private double size,widthDouble,heightDouble;
    private int width, height;

    public void preStart() {
        System.out.print("preStart;   ");
        size = 1.6;
        widthDouble = maxWidth/6;
        widthDouble = widthDouble*size;
        heightDouble = maxWidth/7;
        heightDouble = heightDouble*size;
        width = (int) widthDouble;
        height = (int) heightDouble;
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setMinimumSize(new Dimension(540,300));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setBackground(new Color(255, 255, 255));
        frame.setLocationRelativeTo(null);
        start();
        if(JOptionPane.showOptionDialog(null, "Gegen wen wollen Sie spielen?","Spielmodus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Gegen den Computer", "Gegen einen Spieler"}, "Gegen einen Spieler") == 0) withComputer = true;
        //withComputer = true; // Only when you want to test the Computer
    }


    public void start() {
        frame.setLocationRelativeTo(null);
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

        computer.preStart();
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
        if (winCheck(1)) {
            winOutput(1);
        } else if(winCheck(2)){
            winOutput(2);
        } else if(tie){
            winOutput(0);
        }
        tie = true;
        if (withComputer &&  activePlayer == 2) {
            panelText = "4-Gewinnt   -   Computer ist am Zug!";
            computer();
        }
        frame.setTitle(panelText);
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
        win = false;
        for(int y = 0; y<6; y++){
            for(int x = 0; x<4; x++){
                if(places[x][y] == player && places[x+1][y] == player && places[x+2][y] == player && places[x+3][y] == player) {
                    win = true;
                    fields.setColor(x,y,3);
                    fields.setColor(x+1,y,3);
                    fields.setColor(x+2,y,3);
                    fields.setColor(x+3,y,3);
                    System.out.println("Horizontaler win");
                    frame.setLocation((maxWidth/2)-(width/2), 0);
                }
            }
        }

        for(int y = 0; y<3; y++){
            for(int x = 0; x<7; x++){
                if(places[x][y] == player && places[x][y+1] == player && places[x][y+2] == player && places[x][y+3] == player) {
                    win = true;
                    fields.setColor(x,y,player+2);
                    fields.setColor(x,y+1,player+2);
                    fields.setColor(x,y+2,player+2);
                    fields.setColor(x,y+3,player+2);
                    System.out.println("Vertikaler win");
                    frame.setLocation((maxWidth/2)-(width/2), 0);
                }
            }
        }

        for(int y = 0; y<3; y++){
            for(int x = 0; x<4; x++){
                if(places[x][y] == player && places[x+1][y+1] == player && places[x+2][y+2] == player && places[x+3][y+3] == player) {
                    win = true;
                    fields.setColor(x,y,player+2);
                    fields.setColor(x+1,y+1,player+2);
                    fields.setColor(x+2,y+2,player+2);
                    fields.setColor(x+3,y+3,player+2);
                    System.out.println("Diagonaler win");
                    frame.setLocation((maxWidth/2)-(width/2), 0);
                }
            }
        }

        for(int y = 5; y>2; y--){
            for(int x = 0; x<4; x++){
                if(places[x][y] == player && places[x+1][y-1] == player && places[x+2][y-2] == player && places[x+3][y-3] == player) {
                    win = true;
                    fields.setColor(x,y,player+2);
                    fields.setColor(x+1,y-1,player+2);
                    fields.setColor(x+2,y-2,player+2);
                    fields.setColor(x+3,y-3,player+2);
                    System.out.println("Diagonale (andersrum) win");
                    frame.setLocation((maxWidth/2)-(width/2), 0);
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
        restart = JOptionPane.showOptionDialog(null, winText + " Wollen Sie noch eine Runde spielen?","Neustart",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Gegen den Computer", "Gegen einen Spieler", "Nein"}, "");
        if (restart == 0){
            withComputer = true;
            System.out.println("Neustart");
            start();
        } else if(restart == 1) {
            withComputer = false;
            System.out.println("Neustart");
            start();
        } else {
            System.out.println("Ende");
            System.exit(0);
        }

    }

    public void computer(){
        computerRow = computer.computerTurn();
        if (!fields.setField(computerRow)){
            computer();
        }
    }

}
