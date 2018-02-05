package de.robmroi.fourWins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static de.robmroi.fourWins.Startup.service;

public class Fields extends JPanel {


    JButton[] fields = new JButton[43];
    JButton[] buttons = new JButton[8];
    int[] count = new int[8];

    Image WhiteImage = new ImageIcon("src/images/White.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    Image XImage     = new ImageIcon("src/images/X.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    Image OImage     = new ImageIcon("src/images/O.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

    public void preStart(){
        System.out.print("Fields preStart;   ");
        this.setLayout(new GridLayout(7,7, 10, 10));
        //this.setLayout(new FlowLayout());
    }

    public void start(){
        System.out.println("Fields Start;   ");
        for (int i = 1; i <= 7; i++){
            count[i] = 0;
        }
        for (int i = 1; i <= 42; i++){
            fields[i] = new JButton();
            this.add(fields[i]);
            fields[i].setBackground(Color.WHITE);
            fields[i].setOpaque(true);
            fields[i].setBorderPainted(false);
        }
        for(int i = 1; i <= 7; i++){
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.GRAY);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            this.add(buttons[i]);
            buttons[i].addActionListener(actionListener);
        }
    }


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(e.getSource() instanceof JButton)) return;
            JButton button = (JButton) e.getSource();

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

            if (button == buttons[7]) {
                if (count[7] != 6) {
                    service.game(7);
                    count[7] += 1;
                }
            }
        }
    };


    public void setColor(int field, int player){
        if (player == 1) fields[field].setBackground(Color.BLUE);
        if (player == 2) fields[field].setBackground(Color.RED);
        fields[field].setOpaque(true);
        fields[field].setBorderPainted(false);
    }
    /*
    public void setImage(int field){

        if (service.count%2 == 0){
            switch (field){
                case 1:
                    fieldUL.setIcon(new ImageIcon(OImage));
                    one = true;
                    break;
                case 2:
                    fieldUM.setIcon(new ImageIcon(OImage));
                    two = true;
                    break;
                case 3:
                    fieldUR.setIcon(new ImageIcon(OImage));
                    three = true;
                    break;
                case 4:
                    fieldML.setIcon(new ImageIcon(OImage));
                    four = true;
                    break;
                case 5:
                    fieldMM.setIcon(new ImageIcon(OImage));
                    five = true;
                    break;
                case 6:
                    fieldMR.setIcon(new ImageIcon(OImage));
                    six = true;
                    break;
                case 7:
                    fieldDL.setIcon(new ImageIcon(OImage));
                    seven = true;
                    break;
                case 8:
                    fieldDM.setIcon(new ImageIcon(OImage));
                    eight = true;
                    break;
                case 9:
                    fieldDR.setIcon(new ImageIcon(OImage));
                    nine = true;
                    break;
                default:
                    System.out.println("Fehler bei Spieler 2");
            }
        } else {
            switch (field){
                case 1:
                    fieldUL.setIcon(new ImageIcon(XImage));
                    one = true;
                    break;
                case 2:
                    fieldUM.setIcon(new ImageIcon(XImage));
                    two = true;
                    break;
                case 3:
                    fieldUR.setIcon(new ImageIcon(XImage));
                    three= true;
                    break;
                case 4:
                    fieldML.setIcon(new ImageIcon(XImage));
                    four = true;
                    break;
                case 5:
                    fieldMM.setIcon(new ImageIcon(XImage));
                    five = true;
                    break;
                case 6:
                    fieldMR.setIcon(new ImageIcon(XImage));
                    six = true;
                    break;
                case 7:
                    fieldDL.setIcon(new ImageIcon(XImage));
                    seven = true;
                    break;
                case 8:
                    fieldDM.setIcon(new ImageIcon(XImage));
                    eight = true;
                    break;
                case 9:
                    fieldDR.setIcon(new ImageIcon(XImage));
                    nine = true;
                    break;
                default:
                    System.out.println("Fehler bei Spieler 1");
            }
        }

    }*/

}
