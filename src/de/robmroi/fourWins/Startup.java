package de.robmroi.fourWins;

public class Startup {
    public static Service service;
    public static computer computer;
    public static Fields fields;
    public static Animations animations;
    public static void main(String[] args){
        service = new Service();
        computer = new computer();
        fields = new Fields();
        animations = new Animations(0,0,0);
        service.preStart();
    }
}
