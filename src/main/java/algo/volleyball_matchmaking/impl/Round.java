package algo.volleyball_matchmaking.impl;

import java.util.ArrayList;

public class Round {
    public ArrayList<Integer> notPlaying = new ArrayList<>(); // list integers not playing here
    public ArrayList<int[]> playing = new ArrayList<int[]>(); // SIZE MUST BE EVEN

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        int count = 1;
        for(int[] x : playing){
            if(count % 2 == 1) sb.append("set ").append((count / 2) + 1).append("\n");
            sb.append("team ").append(count).append(": ");
            for(int i : x){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            count++;
        }
        sb.append("not playing: ");
        for(Integer i : notPlaying){
            sb.append(i).append(" ");
        }

        return sb.toString();
    }
}
