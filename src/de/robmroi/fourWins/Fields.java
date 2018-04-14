package de.robmroi.fourWins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static de.robmroi.fourWins.Startup.service;

class Fields extends JPanel {


    JButton[][] fields = new JButton[7][6];
    private int[] count = new int[7];
    boolean isSpacebar = false;
    void preStart(){
        System.out.print("Fields preStart;   ");
        this.setLayout(new GridLayout(6,7, 10, 10));
        //this.setLayout(new FlowLayout());
    }

    void start(){
        System.out.println("Fields Start;   ");
        for(int y = 0; y<6; y++){
            for(int x = 0; x<7; x++){
                fields[x][y] = new JButton(/*x + "," + y*/);
                this.add(fields[x][y]);
                fields[x][y].setBackground(Color.WHITE);
                fields[x][y].setOpaque(true);
                fields[x][y].setBorderPainted(false);
                fields[x][y].addActionListener(actionListener);
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
        for(int y = 0; y<6; y++){
            for(int x = 0; x<7; x++){
                if (button == fields[x][y]){
                    setField(x, true);
                }
            }
        }

    };



    boolean setField(int row, boolean correctButton){
        if (!correctButton) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isSpacebar){
                isSpacebar = false;
                return false;
            }
        }

        boolean returnBool = false;
        if (count[row] != 6) {
            count[row] += 1;
            service.game(row);
            returnBool = true;
        }
        return returnBool;
    }

}