package algo.volleyball_matchmaking.impl;

import java.util.*;

public class Matchmaking {
    /* definitions:
        x amount of r, optimize for 6 rounds
        y amount of p, optimize for 12-24
        z amount of p/t, optimize for 3-4 p/t (people per team)
        o amount of m/r (matches per round)
            - odd numbers will have a set of people sitting out
        a meaning entropy, ranging from (lowest) 0-1 (highest), engaging randomness
                - encourages different algorithm outputs
     */

    Graph<Integer> playWith = new Graph<>();
    Graph<Integer> playAgainst = new Graph<>();

    //objective is to try to make the graph as even as possible
    // explicit algorithm for a 3v3, 12 person tourney.
    // explicit implies y=12, z=3, o=2
    public ArrayList<Round> explMatch3v3(int r, float a) {
        int p = 12; // 12 players total
        int pt = 3; // 3 people per team
        int mr = (p / (2 * pt)); // 2 matches per roundx
        int ent = (int) (a * r); // entropy is scaled in block-like steps

        //list being returned
        ArrayList<Round> rounds = new ArrayList<>();
        for(int i = 0; i < r; i++){
            Round round = new Round();
            if(i < ent) {
                rounds.add(randomRound(p, pt, mr));
            } else {
                /*
                    so essentially what you can do here is smth cool, given that entropy is high enough
                    you can take advantage of statistical stuff
                 */
            }
        }

        return rounds;
    }

    public Round randomRound(int p, int pt, int mr){
        Round round = new Round();

        //most efficient way to randomly fill array lmao
        ArrayList<Integer> x = new ArrayList<>();
        for(int i = 1; i <= p; i++){
            x.add(i);
        }

        for(int i = 0; i < mr; i++){
            //create first team
            int[] a = new int[pt];
            for(int j = 0; j < pt; j++){
                int idx = (int) (Math.random() * (p));
                a[j] = x.get(idx);
                x.remove(idx);
                p--;
            }
            for(int j = 0; j < pt - 1; j++){ //played with
                for(int k = (j+1); k < pt; k++){
                    playWith.addEdge(a[j], a[k], true);
                }
            }

            //create second team
            int[] b = new int[pt];
            for(int j = 0; j < pt; j++){
                int idx = (int) (Math.random() * (p));
                b[j] = x.get(idx);
                x.remove(idx);
                p--;
            }
            for(int j = 0; j < pt - 1; j++){ //played with
                for(int k = (j+1); k < pt; k++){
                    playWith.addEdge(b[j], b[k], true);
                }
            }


            //played against
            for(int j = 0; j < pt; j++){
                for(int k = 0; k < pt; k++){
                    playAgainst.addEdge(a[j], b[k], true);
                }
            }

            round.playing.add(a);
            round.playing.add(b);
        }
        round.notPlaying.addAll(x);

        return round;
    }
    private class Graph<T> {
        private Map<T, List <T>> map = new HashMap<>();
        public void addVertex(T s) {
            map.put(s, new LinkedList<T>());
        }
        public void addEdge(T source, T destination, boolean bidirectional) {
            if (!map.containsKey(source)) addVertex(source);
            if (!map.containsKey(destination)) addVertex(destination);

            map.get(source).add(destination);
            if (bidirectional) map.get(destination).add(source);

        }
        public int getVertexCount() {
            return map.keySet().size();
        }
        public int getEdgesCount(boolean bidirection) {
            int count = 0;
            for (T v: map.keySet()) {
                count += map.get(v).size();
            }

            if (bidirection) count = count / 2;

            return count;
        }
        public boolean hasVertex(T s) {
            return map.containsKey(s);
        }
        public boolean hasEdge(T s, T d) {
            return map.get(s).contains(d);
        }
        public Map<T, List <T>> getMap() {
            return map;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            for (T v: map.keySet()) {
                builder.append(v.toString() + ": ");
                for (T w: map.get(v)) {
                    builder.append(w.toString() + " ");
                }
                builder.append("\n");
            }

            return (builder.toString());
        }
    }
}