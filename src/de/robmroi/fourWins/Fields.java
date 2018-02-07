package de.robmroi.fourWins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static de.robmroi.fourWins.Startup.service;

public class Fields extends JPanel {


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
        for(int y = 0; y<6; y++){
            for(int x = 0; x<7; x++){
                fields[x][y] = new JButton();
                this.add(fields[x][y]);
                fields[x][y].setBackground(Color.WHITE);
                fields[x][y].setOpaque(true);
                fields[x][y].setBorderPainted(false);
            }
        }
        for(int i = 0; i<7; i++){
            count[i] = 0;
            buttons[i] = new JButton("Reihe " + (i+1));
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
                    service.game(0);
                    count[0] += 1;
                }
            }

            if (button == buttons[1]) {
                if (count[1] != 6) {
                    service.game(1);
                    count[1] += 1;
                }
            }

            if (button == buttons[2]) {
                if (count[2] != 6) {
                    service.game(2);
                    count[2] += 1;
                }
            }

            if (button == buttons[3]) {
                if (count[3] != 6) {
                    service.game(3);
                    count[3] += 1;
                }
            }

            if (button == buttons[4]) {
                if (count[4] != 6) {
                    service.game(4);
                    count[4] += 1;
                }
            }

            if (button == buttons[5]) {
                if (count[5] != 6) {
                    service.game(5);
                    count[5] += 1;
                }
            }

            if (button == buttons[6]) {
                if (count[6] != 6) {
                    service.game(6);
                    count[6] += 1;
                }
            }
        }
    };


    public void setColor(int x, int y, int player){
        if (player == 1) fields[x][y].setBackground(Color.BLUE);
        if (player == 2) fields[x][y].setBackground(Color.RED);
        fields[x][y].setOpaque(true);
        fields[x][y].setBorderPainted(false);
    }

}
