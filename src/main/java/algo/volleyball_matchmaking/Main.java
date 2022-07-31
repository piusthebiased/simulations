package algo.volleyball_matchmaking;

import algo.volleyball_matchmaking.impl.Matchmaking;
import algo.volleyball_matchmaking.impl.Round;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeInt;
import org.ice1000.jimgui.flag.JImTableFlags;
import org.ice1000.jimgui.util.JniLoader;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JniLoader.load();
        ArrayList<Round> rounds = new ArrayList<Round>();
        try (JImGui imGui = new JImGui()) {
            while (!imGui.windowShouldClose()) {
                //initialization stuff for each frame.
                imGui.initNewFrame();

                //code here
                Matchmaking mm = new Matchmaking();
                if(imGui.button("generate teams")) {
                    rounds.clear();
                    for(int i = 0; i < 6; i++) {
                        rounds.add(mm.randomRound(12, 3, 2));
                    }
                }

                if(!rounds.isEmpty()) {
                    int c = 1;
                    for(Round r : rounds) {
                        imGui.text("\nSet #" + c);
                        int m = 1;
                        for(int[] x : r.playing){
                            imGui.beginTable("S" + c + "M" + (m-1)/2 + "T" + m, x.length, JImTableFlags.Borders);
                            for (int j : x) {
                                JImGui.tableNextColumn();
                                imGui.text(j + "");
                            }

                            JImGui.endTable();
                            m++;
                        }

                        c++;
                    }
                }

                //end
                imGui.render();
            }
        }
    }
}
