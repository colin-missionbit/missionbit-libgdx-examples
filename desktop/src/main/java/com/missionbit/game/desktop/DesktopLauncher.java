package com.missionbit.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.missionbit.game.LibGDXExamples;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
    public static void main(String[] args) {
        createApplication();
    }

    private static LwjglApplication createApplication() {
        return new LwjglApplication(new LibGDXExamples(), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "missionbit-libgdx-examples";
        configuration.width = 960;
        configuration.height = 540;

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 512;
        settings.maxHeight = 512;
        TexturePacker.process(settings, "../assets/images/animation", "../assets/images/animationSheets", "animation");

        for (int size : new int[] { 128, 64, 32, 16 }) {
            configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
        }
        return configuration;
    }
}