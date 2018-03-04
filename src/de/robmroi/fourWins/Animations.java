package de.robmroi.fourWins;


public class Animations implements Runnable {
    Service service = Startup.service;
    private int x, y, player;
    private Boolean isRunning = false;

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
                try {
                    Thread.sleep(service.waitMilis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (player == 3) service.setColorForField(x, y, 3);
                if (player == 4) service.setColorForField(x, y, 4);
            } else {
                service.waitMilis = y * 100 + 100;
                for (int i = 0; i <= y; i++) {
                    if (player == 1) service.setColorForField(x, i, 1);
                    if (player == 2) service.setColorForField(x, i, 2);

                    if (i != 0) {
                        service.setColorForField(x, i - 1, 0);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            service.waitMilis = 1;
            isRunning = false;

    }
}
