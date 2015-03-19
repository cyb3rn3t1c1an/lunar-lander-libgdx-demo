package io.github.cyb3rn3t1c1an.libgdx.demo.lunarlander.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.cyb3rn3t1c1an.libgdx.demo.lunarlander.LunarLanderDemo;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 640;
        config.height = 480;
        config.resizable = false;
        new LwjglApplication(new LunarLanderDemo(), config);
    }
}
