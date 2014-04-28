package com.jacksofhearts.skeleton;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Util {

	// makeImage
	public static Image makeImage(String path, float w, float h) {
		return makeImage(getTextureRegion(path), w, h);
	}

	public static Image makeImage(String path, TextureFilter f1, TextureFilter f2, float w, float h) {
		return makeImage(getTextureRegion(path), f1, f2, w, h);
	}

	public static Image makeImage(String path, String region, float w, float h) {
		return makeImage(getTextureRegion(path, region), w, h);
	}

	public static Image makeImage(TextureRegion textureRegion, float w, float h) {
		return makeImage(textureRegion, TextureFilter.Linear, TextureFilter.Linear, w, h);
	}

	public static Image makeImage(TextureRegion textureRegion, TextureFilter f1, TextureFilter f2, float w, float h) {
		textureRegion.getTexture().setFilter(f1, f2);
		Image img = new Image(textureRegion);
		img.setSize(w, h);
		img.setOrigin(w / 2f, h / 2f);
		return img;
	}

	// makeImageButton
	public static ImageButton makeImageButton(String path, float w, float h) {
		return makeImageButton(getTextureRegion(path), w, h);
	}

	public static ImageButton makeImageButton(String path, String region, float w, float h) {
		return makeImageButton(getTextureRegion(path, region), w, h);
	}

	public static ImageButton makeImageButton(TextureRegion textureRegion, float w, float h) {
		ImageButton imgBtn = new ImageButton(new TextureRegionDrawable(textureRegion));
		imgBtn.setSize(w, h);
		imgBtn.getImageCell().prefSize(w, h);
		Image img = imgBtn.getImage();
		img.setOrigin(w / 2f, h / 2f);
		return imgBtn;
	}
	
	//makeSprite
	public static Sprite makeSprite(String path, float w, float h) {
		TextureRegion texture = getTextureRegion(path);
		return makeSprite(texture, w, h);
	}

	public static Sprite makeSprite(String path, String region, float w, float h) {
		return makeSprite(getTextureRegion(path, region), w, h);
	}

	public static Sprite makeSprite(TextureRegion texture, float w, float h) {
		Sprite sprite = new Sprite(texture);
		sprite.setSize(w, h);
		return sprite;
	}

	public static Sprite makeSprite(AtlasRegion atlasRegion) {
		return new Sprite(atlasRegion);
	}
	
	//getTextureRegion + getTexture
	public static TextureRegion getTextureRegion(String path, String region) {
		TextureAtlas atlas = JoHSkeleton.assetManager.get(path, TextureAtlas.class); //replace
		AtlasRegion atlasRegion = atlas.findRegion(region);
		TextureRegion textureRegion = new TextureRegion(atlasRegion.getTexture(), atlasRegion.getRegionX(), atlasRegion.getRegionY(), atlasRegion.packedWidth, atlasRegion.packedHeight);
		return setTextureFilter(textureRegion);
	}

	private static TextureRegion getTextureRegion(String path) {
		TextureRegion textureRegion = new TextureRegion(getTexture(path));
		setTextureFilter(textureRegion);
		return textureRegion;
	}

	public static Texture getTexture(String path) {
		return JoHSkeleton.assetManager.get(path, Texture.class); // replace
	}
	
	private static TextureRegion setTextureFilter(TextureRegion textureRegion) {
		Texture texture = textureRegion.getTexture();
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		textureRegion.setTexture(texture);
		return textureRegion;
	}
	
	//removeAll Listeners
	public static void removeAllListeners(Actor a) {
		for (EventListener e : a.getListeners()) {
			a.removeListener(e);
		}
		for (EventListener e : a.getCaptureListeners()) {
			a.removeCaptureListener(e);
		}
	}

	public static void removeAllListeners(Stage a) {
		for (EventListener e : a.getRoot().getListeners()) {
			a.removeListener(e);
		}
		for (EventListener e : a.getRoot().getCaptureListeners()) {
			a.removeCaptureListener(e);
		}
	}
	
	//overlay
	public static Image generateBlackImage(float w, float h) {
		Texture t = getTexture("ui/blackoverlay.png");
		t.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		Image black = new Image(t);
		black.setSize(w, h);
		black.setOrigin(w / 2, h / 2);
		return black;
	}

}
