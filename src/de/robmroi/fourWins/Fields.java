package de.robmroi.fourWins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static de.robmroi.fourWins.Startup.service;

public class Fields extends JPanel {


    JButton[][] fields = new JButton[7][6];
    int[] count = new int[7];

    public void preStart(){
        System.out.print("Fields preStart;   ");
        this.setLayout(new GridLayout(6,7, 10, 10));
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
                fields[x][y].addActionListener(actionListener);
            }
        }
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(e.getSource() instanceof JButton)) return;
            JButton button = (JButton) e.getSource();

            for(int y = 0; y<6; y++){
                for(int x = 0; x<7; x++){
                    if (button == fields[x][y]){
                        if (count[x] != 6) {
                            service.game(x);
                            count[x] += 1;
                        }
                    }
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

    public void computer(){
        //Hier kommt der Computer hin
    }

}
