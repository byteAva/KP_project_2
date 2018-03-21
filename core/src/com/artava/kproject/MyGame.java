package com.artava.kproject;

import com.artava.kproject.Screens.PlayScreen;
import com.artava.kproject.Screens.World1;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
	public SpriteBatch batch;
	public static final int WIDTH =800 ;
	public static final int HEIGHT = 480;

    public static final float PPM =50f;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
