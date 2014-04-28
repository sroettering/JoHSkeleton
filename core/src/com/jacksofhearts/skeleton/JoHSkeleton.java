package com.jacksofhearts.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Interpolation.PowOut;
import com.jacksofhearts.skeleton.screens.SplashScreen;

public class JoHSkeleton extends Game {
	
	public static AssetManager assetManager;
	public Preferences preferences;
	
	private SplashScreen splashScreen;
	
	//verbreitete Auflösungen (Stand 04.11.2013) siehe: http://developer.android.com/about/dashboards/index.html
	//<			= 09.3%
	//320 x 480	= 15,1%
	//480 x 800	= 37,6%
	//>			= 38,0%
	public final Resolution[] resolutions = {
		new Resolution(320, 480, "_low"),
		new Resolution(480, 800, "_mid"),
		new Resolution(720, 1280, "_mid"),
		new Resolution(1080, 1920, "_high")};
	
	//für einheitliche Transitions zwischen den Screens
	public final Interpolation transEaseIn = PowOut.pow2In;
	public final Interpolation transEaseOut = PowOut.pow2Out;
	public final float transDuration = 0.5f;
	
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		Texture.setAssetManager(assetManager);
		
		preferences = Gdx.app.getPreferences("JoHSkeleton"); //replace
		
		splashScreen = new SplashScreen(this);
		
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		Gdx.graphics.setVSync(true);
		
		this.setScreen(splashScreen);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}
}
