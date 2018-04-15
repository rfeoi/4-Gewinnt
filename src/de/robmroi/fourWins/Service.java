package de.robmroi.fourWins;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static de.robmroi.fourWins.Startup.computer;

public class Service implements AWTEventListener {
    private int activePlayer, count;
    private boolean win, tie, winOutput, isStarted;
    static int[][] places;
    int waitMilis;
    private Fields fields;
    private JFrame frame;
    private Color winBlue = new Color(0,100,255);
    private Color winRed = new Color(255,80,0);
    boolean animation = false, computerTurn, withComputer;
    //Resolution
    private int maxWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int width, height;
    static int rows, columns;

    Service() {
        long eventMask = AWTEvent.KEY_EVENT_MASK;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.addAWTEventListener(this, eventMask);
    }

    void preStart() {
        System.out.print("preStart;   ");
        rows = 10;  // von oben nach unten
        columns = 10; // von links nach rechts
        waitMilis = 1;
        double size = 100;
        double maxWidthDouble = Math.sqrt(maxWidth);
        double widthDouble = (maxWidthDouble / rows) * size;
        double heightDouble = (maxWidthDouble / columns) * size;
        width = (int) widthDouble;
        height = (int) heightDouble;
        isStarted = false;
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(width/3,height/3));
        frame.setBackground(new Color(255, 255, 255));
        start();
        if(JOptionPane.showOptionDialog(null, "Gegen wen wollen Sie spielen?","Spielmodus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Gegen den Computer", "Gegen einen Spieler"}, "Gegen einen Spieler") == 0) withComputer = true;
        //withComputer = true; // Only when you want to test the Computer
        isStarted = true;
    }


    private void start() {
        frame.setLocationRelativeTo(null);
        System.out.print("Start;   ");
        fields = new Fields();
        places = new int[columns][rows];
        for(int x = 0; x< columns; x++){
            for(int y = 0; y<rows; y++){
                places[x][y] = 0;
            }
        }
        activePlayer = 1;
        count = 0;
        win = false;

        computer.preStart();
        fields.preStart();
        fields.start();
        computer.start();
        frame.setSize(new Dimension(width,height));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(fields);
        frame.setVisible(false);
        frame.setVisible(true);
        refreshTitle(0);
    }

    void refreshTitle( int player){
        if (player != 0){
            frame.setTitle("4-Gewinnt   -   Computer ist am Zug!");
            return;
        }
        if (activePlayer == 1){
            frame.setTitle("4-Gewinnt   -   Spieler 1 ist am Zug!");
        }else{
           if (withComputer){
               frame.setTitle("4-Gewinnt   -   Computer ist am Zug!");
           }else{
               frame.setTitle("4-Gewinnt   -   Spieler 2 ist am Zug!");
           }
        }
    }
    void game(int rowX){
        for (int y = rows-1; y>=0; y--){
            if (places[rowX][y] == 0){
                places[rowX][y] = activePlayer;
                setColor(rowX,y, activePlayer);
                break;
            }
        }

        for(int y = 0; y<rows; y++){
            for(int x = 0; x< columns; x++){
                if (places[x][y] == 0) tie = false;
            }
        }
        if (winCheck(1)) {
            winOutput(1);
            return;
        } else if(winCheck(2)){
            winOutput(2);
            return;
        } else if(tie){
            winOutput(0);
            return;
        }

        playerCheck();
        tie = true;
        if (withComputer &&  activePlayer == 2) {
            computerTurn = false;
            computer();
        } else if (withComputer && activePlayer == 1){
            computerTurn = true;
        }
        //if (activePlayer == 2) System.out.println(computer.computerTurn()+1);//only for testing
    }

    private void playerCheck(){
        count++;
        if (count%2 == 0){
            activePlayer = 1;
        } else {
            activePlayer = 2;
        }
    }

    private boolean winCheck(int player) {
        win = false;
        for(int y = 0; y<rows; y++){
            for(int x = 0; x< columns -3; x++){
                if(places[x][y] == player && places[x+1][y] == player && places[x+2][y] == player && places[x+3][y] == player) {
                    win = true;
                    places[x][y] = player+2;
                    places[x+1][y] = player+2;
                    places[x+2][y] = player+2;
                    places[x+3][y] = player+2;
                    setColor(x,y,player+2);
                    setColor(x+1,y,player+2);
                    setColor(x+2,y,player+2);
                    setColor(x+3,y,player+2);
                    System.out.println("Horizontaler win");
                }
            }
        }

        for(int y = 0; y<rows-3; y++){
            for(int x = 0; x< columns; x++){
                if(places[x][y] == player && places[x][y+1] == player && places[x][y+2] == player && places[x][y+3] == player) {
                    win = true;
                    places[x][y] = player+2;
                    places[x][y+1] = player+2;
                    places[x][y+2] = player+2;
                    places[x][y+3] = player+2;
                    setColor(x,y,player+2);
                    setColor(x,y+1,player+2);
                    setColor(x,y+2,player+2);
                    setColor(x,y+3,player+2);
                    System.out.println("Vertikaler win");
                }
            }
        }

        for(int y = 0; y<rows-3; y++){
            for(int x = 0; x< columns -3; x++){
                if(places[x][y] == player && places[x+1][y+1] == player && places[x+2][y+2] == player && places[x+3][y+3] == player) {
                    win = true;
                    places[x][y] = player+2;
                    places[x+1][y+1] = player+2;
                    places[x+2][y+2] = player+2;
                    places[x+3][y+3] = player+2;
                    setColor(x,y,player+2);
                    setColor(x+1,y+1,player+2);
                    setColor(x+2,y+2,player+2);
                    setColor(x+3,y+3,player+2);
                    System.out.println("Diagonaler win");
                }
            }
        }

        for(int y = rows-1; y>2; y--){
            for(int x = 0; x< columns -3; x++){
                if(places[x][y] == player && places[x+1][y-1] == player && places[x+2][y-2] == player && places[x+3][y-3] == player) {
                    win = true;
                    places[x][y] = player+2;
                    places[x+1][y-1] = player+2;
                    places[x+2][y-2] = player+2;
                    places[x+3][y-3] = player+2;
                    setColor(x,y,player+2);
                    setColor(x+1,y-1,player+2);
                    setColor(x+2,y-2,player+2);
                    setColor(x+3,y-3,player+2);
                    System.out.println("Diagonaler (andersrum) win");
                }
            }
        }
        return win;
    }

    private void winOutput(int player){
        winOutput = true;
        isStarted = false;
        frame.setSize(new Dimension((int)(width/1.7), (int) (height/1.7)));
        frame.setLocation((int) ((maxWidth/2)-(width/2/1.7)), 0);
        String winText;
        if (player == 0){
            winText = "Es ist Unentschieden!";

        } else {
            winText = "Spieler " + player + " hat gewonnen!";
        }
        frame.setTitle("4-Gewinnt   -   " + winText);
        int restart = JOptionPane.showOptionDialog(null, winText + " Wollen Sie noch eine Runde spielen?", "Neustart",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Gegen den Computer", "Gegen einen Spieler", "Nein"}, "");
        if (restart == 0){
            withComputer = true;
            winOutput = false;
            System.out.println("Neustart");
            start();
        } else if(restart == 1) {
            withComputer = false;
            winOutput = false;
            System.out.println("Neustart");
            start();
        } else {
            System.out.println("Ende");
            System.exit(0);
        }
        isStarted = true;
    }

    private void computer(){
        int computerRow = computer.computerTurn();
        if (!fields.setField(computerRow,true)){
            computer();
        }
    }

    private void setColor(int x, int y, int player){
        new Thread(new Animations(x, y,player)).start();
    }

    void setColorForField(int x, int y, int color){
        if (color == 0) fields.fields[x][y].setBackground(Color.WHITE);
        if (color == 1) fields.fields[x][y].setBackground(Color.BLUE);
        if (color == 2) fields.fields[x][y].setBackground(Color.RED);
        if (color == 3) fields.fields[x][y].setBackground(winBlue);
        if (color == 4) fields.fields[x][y].setBackground(winRed);
        fields.fields[x][y].setOpaque(true);
        fields.fields[x][y].setBorderPainted(false);
    }

    void checkFields(){
        for (int x = 0; x< columns; x++) {
            for (int y = 0; y < rows; y++) {
                for (int i = 1; i<=4; i++){
                    if (places[x][y] == i) setColorForField(x,y,i);
                }
            }
        }
    }


    @Override
    public void eventDispatched(AWTEvent event) {
        if (winOutput) return;
        int ID = event.getID();
        if (ID == KeyEvent.KEY_PRESSED) {
            if (!isStarted) return;
                if (event.paramString().contains("keyCode=49")) fields.setField(0,true);
                if (event.paramString().contains("keyCode=50")) fields.setField(1,true);
                if (event.paramString().contains("keyCode=51")) fields.setField(2,true);
                if (event.paramString().contains("keyCode=52")) fields.setField(3,true);
                if (event.paramString().contains("keyCode=53")) fields.setField(4,true);
                if (event.paramString().contains("keyCode=54")) fields.setField(5,true);
                if (event.paramString().contains("keyCode=55")) fields.setField(6,true);
                if (event.paramString().contains("keyCode=32")) fields.isSpacebar = true;
        }
    }
}
