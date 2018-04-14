package de.robmroi.fourWins;

public class Startup {
    static Service service;
    static computer computer;
    public static void main(String[] args){
        service = new Service();
        computer = new computer();
        service.preStart();
    }
}
