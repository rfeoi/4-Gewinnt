package de.robmroi.fourWins;

import de.robmroi.fourWins.computerP.Computer;

public class Startup {
    static Service service;
    static Computer computer;
    public static void main(String[] args){
        service = new Service();
        computer = new Computer();
        service.preStart();
    }
}
