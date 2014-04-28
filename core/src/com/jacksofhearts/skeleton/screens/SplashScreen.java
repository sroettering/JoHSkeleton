package com.jacksofhearts.skeleton.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.jacksofhearts.skeleton.JoHSkeleton;
import com.jacksofhearts.skeleton.Util;

public class SplashScreen implements Screen {
	
	private JoHSkeleton game; //replace type
	
	private SpriteBatch batch;
	private Stage stage;
	private Texture logoColor;
	private Texture logoSw;
	
	private boolean firstTime;
	
	public SplashScreen(JoHSkeleton game) { //replace type
		this.game = game;
		this.firstTime = true;
		this.batch = new SpriteBatch();
		this.stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);
		
		MyResolutionFileResolver resolver = new MyResolutionFileResolver(new InternalFileHandleResolver(), game.resolutions);
		game.assetManager.setLoader(Texture.class, new TextureLoader(resolver));
		game.assetManager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(game.assetManager.update() && firstTime) {
			 // Die render-Methode wird noch einige Male aufgerufen, bevor der Wechsel zum nächsten Screen geschieht.
			 // Diese Boolean verhindert, dass loadScreen  und der Fade zu den Screen mehrmals aufgerufen werden.
			firstTime = false;

			Image blackOverlay = Util.generateBlackImage(854, 600);
	    	blackOverlay.getColor().a = 0.0f;
	    	stage.addActor(blackOverlay);
			blackOverlay.addAction(fadeIn(game.transDuration, game.transEaseIn));

			stage.addAction(sequence(delay(game.transDuration), run(new Runnable() {
				public void run() {
					//change screen
				}
			})));

		} else {
			batch.begin();
			batch.draw(logoSw, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();

			Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
			// 0.31 = prozentuale Höhe, ab der die rote Farbe beginnt (vom unteren Rand aus gesehen)
			// 0.45 = Bereich in Prozent, der den Ladebalken darstellt (= 0,41 + 0,04 = Bereich in Prozent, welcher rote Farbe enthält + kleine Zugabe, damit das Logo nicht sofort weg ist, wenn es komplett in Farbe dargestellt wird (sieht geschickter aus)) 
			Gdx.gl.glScissor(0, (int) (0.31 * Gdx.graphics.getHeight()), Gdx.graphics.getWidth(), (int) (0.45 * Gdx.graphics.getHeight() * game.assetManager.getProgress()));

			batch.begin();
			batch.draw(logoColor, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();

			Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
		}

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		//JacksOfHearts Logo
		game.assetManager.load("ui/logo_jacksofhearts_color.png", Texture.class);
		game.assetManager.load("ui/logo_jacksofhearts_sw.png", Texture.class);

		game.assetManager.finishLoading(); //damit das Logo direkt gezeigt werden kann

		logoColor = game.assetManager.get("ui/logo_jacksofhearts_color.png", Texture.class);
		logoSw = game.assetManager.get("ui/logo_jacksofhearts_sw.png", Texture.class);
		logoColor.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		logoSw.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//do texture loading here
		game.assetManager.load("ui/blackoverlay.png", Texture.class);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
	
	
	/**
	 * Private Klasse zum Mapping der Dateinamen anhand der nächst-passenden Auflösung
	 *
	 */
	private class MyResolutionFileResolver extends ResolutionFileResolver {

		public MyResolutionFileResolver(FileHandleResolver baseResolver, Resolution[] descriptors) {
			super(baseResolver, descriptors);
		}

		@Override
		protected String resolve (FileHandle originalHandle, String suffix) { 
			String handleString = originalHandle.parent().toString();
			String tmp[] = handleString.split("/", 2);
			if (tmp.length > 1) {				
				return  tmp[0] + suffix + "/" + tmp[1] + "/" + originalHandle.name();
			} else {
				return  tmp[0] + suffix + "/" + originalHandle.name();
			}
		}
	}

}
