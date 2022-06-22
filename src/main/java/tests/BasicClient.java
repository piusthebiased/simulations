package tests;

import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.util.JniLoader;

import java.io.IOException;

public class BasicClient {
    public static void main(String[] args) {
        JniLoader.load();
        try (JImGui imGui = new JImGui()) {
            while (!imGui.windowShouldClose()) {
                //initialization stuff for each frame.
                imGui.initNewFrame();

                //code here
                imGui.text("hello");

                //end
                imGui.render();
            }
        }
    }
}
