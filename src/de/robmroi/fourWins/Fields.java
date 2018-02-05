package de.robmroi.fourWins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static de.robmroi.fourWins.Startup.service;

public class Fields extends JPanel {


    JButton[] oldFields = new JButton[43];
    JButton[][] fields = new JButton[7][6];
    JButton[] buttons = new JButton[7];
    int[] count = new int[7];

    public void preStart(){
        System.out.print("Fields preStart;   ");
        this.setLayout(new GridLayout(7,7, 10, 10));
        //this.setLayout(new FlowLayout());
    }

    public void start(){
        System.out.println("Fields Start;   ");
        for(int x = 0; x<7; x++){
            for(int y = 0; y<6; y++){
                fields[x][y] = new JButton();
                this.add(fields[x][y]);
                fields[x][y].setBackground(Color.WHITE);
                fields[x][y].setOpaque(true);
                fields[x][y].setBorderPainted(false);
            }
        }
        for(int i = 0; i<7; i++){
            count[i] = 0;
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.GRAY);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            this.add(buttons[i]);
            buttons[i].addActionListener(actionListener);
        }
    }


    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(e.getSource() instanceof JButton)) return;
            JButton button = (JButton) e.getSource();

            if (button == buttons[0]) {
                if (count[0] != 6) {
                    service.game(1);
                    count[0] += 1;
                }
            }

            if (button == buttons[1]) {
                if (count[1] != 6) {
                    service.game(2);
                    count[1] += 1;
                }
            }

            if (button == buttons[2]) {
                if (count[2] != 6) {
                    service.game(3);
                    count[2] += 1;
                }
            }

            if (button == buttons[3]) {
                if (count[3] != 6) {
                    service.game(4);
                    count[3] += 1;
                }
            }

            if (button == buttons[4]) {
                if (count[4] != 6) {
                    service.game(5);
                    count[4] += 1;
                }
            }

            if (button == buttons[5]) {
                if (count[5] != 6) {
                    service.game(6);
                    count[5] += 1;
                }
            }

            if (button == buttons[6]) {
                if (count[6] != 6) {
                    service.game(7);
                    count[6] += 1;
                }
            }
        }
    };


    public void setColor(int x, int y, int field, int player){
        int xx = 0;
        int yy = 0;
        if (field <= 7){
            xx = field - 1;
        } else if (field <= 14){
            yy = 1;
            xx = field - 7- 1;
        } else if (field <= 21){
            yy = 2;
            xx = field - 14- 1;
        } else if (field <= 28){
            yy = 3;
            xx = field - 21- 1;
        } else if (field <= 35){
            xx = field - 28- 1;
            yy = 4;
        } else if (field <= 42){
            xx = field - 35- 1;
            yy = 5;
        }
        if (player == 1) fields[xx][yy].setBackground(Color.BLUE);
        if (player == 2) fields[xx][yy].setBackground(Color.RED);
        fields[xx][yy].setOpaque(true);
        fields[xx][yy].setBorderPainted(false);
    }

}
