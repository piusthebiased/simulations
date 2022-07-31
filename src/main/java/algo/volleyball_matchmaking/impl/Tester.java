package algo.volleyball_matchmaking.impl;

public class Tester {
    public static void main(String[] args) {
        Matchmaking m = new Matchmaking();
        System.out.println(m.randomRound(12, 3, 2).toString());
    }
}
