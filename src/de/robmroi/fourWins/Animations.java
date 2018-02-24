package de.robmroi.fourWins;

import java.awt.*;
import static de.robmroi.fourWins.Startup.service;


public class Animations implements Runnable {
    Fields fields = new Fields();
    private int x, y, player;
    private Boolean isRunning = false;
    public int wait = 1;

    public Animations(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            isRunning = true;
            if (player > 2) {
                if (player == 3) fields.setColorForField(x, y, 3);
                if (player == 4) fields.setColorForField(x, y, 4);
            } else {
                wait = y * 100;
                for (int i = 0; i <= y; i++) {
                    if (player == 1) fields.setColorForField(x, i, 1);
                    if (player == 2) fields.setColorForField(x, i, 2);

                    if (i != 0) {
                        fields.setColorForField(x, i - 1, 0);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            isRunning = false;
    }
}
