package de.robmroi.fourWins;


public class Animations implements Runnable {
    private Service service = Startup.service;
    private int x, y, player;

    Animations(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    @Override
    public void run() {
        if (player > 2) {
            try {
                Thread.sleep(service.waitMilis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (player == 3) {
                System.out.println("Calls Player 3 at (" + x + "/" + y + ")");
                service.setColorForField(x, y, 3);
            }
            if (player == 4) service.setColorForField(x, y, 4);
        } else {
            while (service.animation) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            service.animation = true;
            service.waitMilis = y * 400;
            for (int i = 0; i <= y; i++) {
                if (player == 1) service.setColorForField(x, i, 1);
                if (player == 2) service.setColorForField(x, i, 2);

                if (i != 0) {
                    service.setColorForField(x, i - 1, 0);
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!service.computerTurn) service.checkFields();
        if (service.withComputer && player == 1) service.refreshTitle(1);
        else service.refreshTitle(0);
        service.waitMilis = 1;
        service.animation = false;

    }
}
