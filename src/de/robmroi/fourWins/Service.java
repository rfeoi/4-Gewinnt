package de.robmroi.fourWins;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static de.robmroi.fourWins.Startup.computer;

/*
The service class is the main class.
90% of the actions are made in here (except the computer).
*/
/* TODO
* Code optimisation
* code documentation
* fix the computer
* fix the AI
*/
public class Service implements AWTEventListener {
    boolean testMode = true;
    //creates all variables
    boolean label;
    private int activePlayer, count;
    private boolean win, tie, winOutput, isStarted;
    static int[][] places;
    int waitMillis;
    private Fields fields;
    private JFrame frame;
    private Color winBlue = new Color(0,150,255);
    private Color winRed = new Color(255,80,0);
    boolean animation = false, computerTurn, withComputer, withAI;
    //Resolution
    private int maxWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int width, height;
    static int columns, rows;

    Service() {
        //detects if a key is pressed
        long eventMask = AWTEvent.KEY_EVENT_MASK;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.addAWTEventListener(this, eventMask);
    }

    void preStart() {
        //preparates the game for beeing started, only used once
        System.out.print("preStart;   ");
        waitMillis = 1;
        isStarted = false;
        label = false;

        //if the testMode is activated you will not be asked about anything.
        if (testMode) {
            itsATest();
            setFrame();
            start();
            isStarted = true;
            return;
        }
        //sets the amount of columns and rows used
        if (!rAndC()) {
            columns = 6;  // top to bottom (vertically) (6 is default)
            rows = 7; // left to right (horizontally) (7 is default)
            //rows run vertically while columns run horizontally
        }
        //creates the Frame
        setFrame();
        start();
        // it asks you whether you want to play against the computer or not
        int panel = JOptionPane.showOptionDialog(null, "Gegen wen wollen Sie spielen?", "Spielmodus",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Gegen den Computer", "Gegen einen Spieler", "Gegen die AI"}, "Gegen einen Spieler");
        if(panel == 0) withComputer = true;
        else if (panel == 2) withAI = true;
        //withComputer = true; // Only when you want to test the Computer
        isStarted = true;
    }

    private void itsATest(){
        columns = 6; //6
        rows = 7; //7
        withComputer = false;
        withAI = true;
        label = true;
    }

    private void setFrame(){
        //sets the size of the field, depending on the resolution of the screen
        double size = 20;
        double maxWidthDouble = Math.sqrt(maxWidth);
        double widthDouble = (maxWidthDouble / columns) * size * Math.sqrt(columns * rows);
        double heightDouble = (maxWidthDouble / rows) * size * Math.sqrt(columns * rows);
        width = (int) widthDouble;
        height = (int) heightDouble;
        //frame settings
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(width/3,height/3));
        frame.setBackground(new Color(255, 255, 255));
    }
    private boolean rAndC() {
        String eingabe = JOptionPane.showInputDialog("Geben Sie bitte die Zeilen (Horizontal) ein.\n" +
                "Wenn Sie mit den normalen Einstellungen Spielen wollen (6*7), drücken Sie Enter.\n" +
                "Minimum: 5*5, Maximum: 20*20");
        try {
            rows = Integer.parseInt(eingabe);
        } catch (Exception ignored) {
            return false;
        }
        if (rows < 5 || rows > 20) return false;
        eingabe = JOptionPane.showInputDialog("Geben Sie bitte die Spalten (Vertikal) ein.\n" +
                "Wenn Sie mit den normalen Einstellungen Spielen wollen (6*7), drücken Sie Enter.\n" +
                "Minimum: 5*5, Maximum: 20*20");
        try {
            columns = Integer.parseInt(eingabe);
        } catch (Exception ignored) {
            return false;
        }
        return columns >= 5 && columns <= 20;
    }

    private void start() {
        //is called every round, locates the frame and sets the variables to their defaults
        frame.setLocationRelativeTo(null);
        System.out.print("Start;   ");
        fields = new Fields();
        places = new int[rows][columns];
        for(int x = 0; x< rows; x++){
            for(int y = 0; y< columns; y++){
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
        //Displays which player has to play
        if (player != 0){
            frame.setTitle("4-Gewinnt   -   Computer ist am Zug!");
            return;
        }
        if (activePlayer == 1){
            frame.setTitle("4-Gewinnt   -   Spieler 1 ist am Zug!");
        }else{
           if (withComputer){
               frame.setTitle("4-Gewinnt   -   Computer ist am Zug!");
           } else if (withAI) {
               frame.setTitle("4-Gewinnt   -   AI ist am Zug!");
           } else {
                   frame.setTitle("4-Gewinnt   -   Spieler 2 ist am Zug!");
               }
           }
    }

    void game(int rowX){
        for (int y = columns -1; y>=0; y--){
            if (places[rowX][y] == 0){
                places[rowX][y] = activePlayer;
                setColor(rowX,y, activePlayer);
                break;
            }
        }

        for(int y = 0; y< columns; y++){
            for(int x = 0; x< rows; x++){
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

        if (withAI &&  activePlayer == 2) {
            computerTurn = false;
            aiTurn();
        } else if (withAI && activePlayer == 1){
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
        for(int y = 0; y< columns; y++){
            for(int x = 0; x< rows -3; x++){
                if(places[x][y] == player && places[x+1][y] == player && places[x+2][y] == player && places[x+3][y] == player) {
                    win = true;
                    setColor(x,y,player+2);
                    setColor(x+1,y,player+2);
                    setColor(x+2,y,player+2);
                    setColor(x+3,y,player+2);
                    System.out.println("Horizontaler win");
                }
            }
        }

        for(int y = 0; y< columns -3; y++){
            for(int x = 0; x< rows; x++){
                if(places[x][y] == player && places[x][y+1] == player && places[x][y+2] == player && places[x][y+3] == player) {
                    win = true;
                    setColor(x,y,player+2);
                    setColor(x,y+1,player+2);
                    setColor(x,y+2,player+2);
                    setColor(x,y+3,player+2);
                    System.out.println("Vertikaler win");
                }
            }
        }

        for(int y = 0; y< columns -3; y++){
            for(int x = 0; x< rows -3; x++){
                if(places[x][y] == player && places[x+1][y+1] == player && places[x+2][y+2] == player && places[x+3][y+3] == player) {
                    win = true;
                    setColor(x,y,player+2);
                    setColor(x+1,y+1,player+2);
                    setColor(x+2,y+2,player+2);
                    setColor(x+3,y+3,player+2);
                    System.out.println("Diagonaler win");
                }
            }
        }

        for(int y = columns -1; y>2; y--){
            for(int x = 0; x< rows -3; x++){
                if(places[x][y] == player && places[x+1][y-1] == player && places[x+2][y-2] == player && places[x+3][y-3] == player) {
                    win = true;
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
                new String[]{"Gegen den Computer/ die AI", "Gegen einen Spieler", "Nein"}, "");
        if (restart == 0){
            if (withAI) withAI = true;
            else withComputer = true;
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

    void computer(){
        int computerRow = computer.computerTurn();
        if (!fields.setField(computerRow,true)){
            computer();
        }
    }

    void aiTurn(){
        int aiRow = computer.test();
        fields.setField(aiRow,true);
    }

    private void setColor(int x, int y, int player){
        new Thread(new Animations(x, y,player)).start();
    }

    void setColorForField(int x, int y, int color){
        if (color == 3 || color == 4) places[x][y] = color;
        if (color == 0) fields.fields[x][y].setBackground(Color.WHITE);
        if (color == 1) fields.fields[x][y].setBackground(Color.BLUE);
        if (color == 2) fields.fields[x][y].setBackground(Color.RED);
        if (color == 3) fields.fields[x][y].setBackground(winBlue);
        if (color == 4) fields.fields[x][y].setBackground(winRed);
        fields.fields[x][y].setOpaque(true);
        fields.fields[x][y].setBorderPainted(false);
    }

    void checkFields(){
        for (int x = 0; x< rows; x++) {
            for (int y = 0; y < columns; y++) {
                for (int i = 1; i<3; i++){
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
            if (event.paramString().contains("keyCode=75")) keyPlaying();
            if (event.paramString().contains("keyCode=32")) fields.isSpacebar = true;
        }
    }

    private void keyPlaying(){
        if (!label) for (int x = 0; x< rows; x++) fields.fields[x][0].setText("" + (x+1));
        String eingabe = JOptionPane.showInputDialog("In welche Reihe wollen Sie setzen?");
        int column;
        try {
             column = Integer.parseInt(eingabe);
        } catch (Exception ignored) {
            if (!label) for (int x = 0; x< rows; x++) fields.fields[x][0].setText("");
            return;
        }
        if (column > rows || column <1) return;
        fields.setField(column-1,true);
        if (!label) for (int x = 0; x< rows; x++) fields.fields[x][0].setText("");
    }
}
