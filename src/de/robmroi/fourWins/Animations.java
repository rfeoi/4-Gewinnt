package de.robmroi.fourWins;

import java.awt.*;


public class Animations implements Runnable {
    int x;
    int y;
    int player;

    public Animations(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    @Override
    public void run() {
        for (int i = 0; i <= y; i++) {
            if (player == 1) Startup.service.fields.fields[x][i].setBackground(Color.BLUE);
            if (player == 2) Startup.service.fields.fields[x][i].setBackground(Color.RED);
            Startup.service.fields.fields[x][i].setOpaque(true);
            Startup.service.fields.fields[x][i].setBorderPainted(false);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i != 0) {
                Startup.service.fields.fields[x][i - 1].setBackground(Color.WHITE);
                Startup.service.fields.fields[x][i - 1].setOpaque(true);
                Startup.service.fields.fields[x][i - 1].setBorderPainted(false);
            }

        }
    }
}
