package com.jacksofhearts.skeleton.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jacksofhearts.skeleton.JoHSkeleton;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "JoHSkeleton"; //replace
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new JoHSkeleton(), config);
	}
}
