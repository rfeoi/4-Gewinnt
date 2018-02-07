package de.robmroi.fourWins;

public class Startup {
    public static Service service;
    public static computer computer;
    public static void main(String[] args){
        service = new Service();
        computer = new computer();
        service.preStart();
    }
}
