package de.robmroi.fourWins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static de.robmroi.fourWins.Startup.service;

class Fields extends JPanel {


    JButton[][] fields;
    private int[] count;
    boolean isSpacebar = false;
    private int columns, rows;
    void preStart(){
        System.out.print("Fields preStart;   ");
        columns = Service.columns; //they run vertically
        rows = Service.rows; //they run horizontally
        fields = new JButton[rows][columns];
        count = new int[rows];
        this.setLayout(new GridLayout(columns, rows, 1, 1));
    }

    void start(){
        System.out.println("Fields Start;   ");
        for(int y = 0; y< columns; y++){
            for(int x = 0; x< rows; x++){
                fields[x][y] = new JButton();
                this.add(fields[x][y]);
                fields[x][y].setBackground(Color.WHITE);
                fields[x][y].setOpaque(true);
                fields[x][y].setBorderPainted(false);
                fields[x][y].addActionListener(actionListener);
                fields[x][y].setFont(new Font("Arial", Font.PLAIN, 10));
                if (service.testMode && service.label) fields[x][y].setText(x + "," + y);
            }
        }
    }

    private ActionListener actionListener = e -> {
        if (!(e.getSource() instanceof JButton)) return;
        JButton button = (JButton) e.getSource();
        if (button == fields[0][0]){
            setField(0, false);
            return;
        }
        for(int y = 0; y< columns; y++){
            for(int x = 0; x< rows; x++){
                if (button == fields[x][y]){
                    setField(x, true);
                }
            }
        }

    };



    boolean setField(int row, boolean correctButton){
        if (!correctButton) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isSpacebar){
                isSpacebar = false;
                return false;
            }
        }

        boolean returnBool = false;
        if (count[row] != columns) {
            count[row] += 1;
            service.game(row);
            returnBool = true;
        }
        return returnBool;
    }

}