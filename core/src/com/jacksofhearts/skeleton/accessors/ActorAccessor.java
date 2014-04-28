package com.jacksofhearts.skeleton.accessors;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorAccessor implements TweenAccessor<Actor> {
	
	public static final int X = 0, Y = 1, RGB = 2, ALPHA = 3;

	@Override
	public int getValues(Actor target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case X:
			returnValues[0] = target.getX();
			return 1;
		case Y:
			returnValues[0] = target.getY();
			return 1;
		case RGB:
			returnValues[0] = target.getColor().r;
			returnValues[1] = target.getColor().g;
			returnValues[2] = target.getColor().b;
			return 3;
		case ALPHA:
			returnValues[2] = target.getColor().a;
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Actor target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case X:
			target.setX(newValues[0]);
		case Y:
			target.setY(newValues[0]);
		case RGB:
			target.getColor().set(newValues[0], newValues[1], newValues[2], target.getColor().a);
		case ALPHA:
			target.getColor().a = newValues[0];
		default:
			assert false;
		}
	}

}
